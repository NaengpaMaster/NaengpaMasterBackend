package com.naengpa.naengpamasterbackend.notification.service;

import com.naengpa.naengpamasterbackend.fridge.entity.FridgeItem;
import com.naengpa.naengpamasterbackend.fridge.repository.FridgeItemRepository;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.notification.entity.Notification;
import com.naengpa.naengpamasterbackend.notification.entity.NotificationTargetType;
import com.naengpa.naengpamasterbackend.notification.entity.NotificationType;
import com.naengpa.naengpamasterbackend.notification.repository.NotificationRepository;
import com.naengpa.naengpamasterbackend.product.entity.Product;
import com.naengpa.naengpamasterbackend.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class NotificationServiceTest {

    private NotificationRepository notificationRepository;
    private MemberRepository memberRepository;
    private FridgeItemRepository fridgeItemRepository;
    private ProductRepository productRepository;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationRepository = mock(NotificationRepository.class);
        memberRepository = mock(MemberRepository.class);
        fridgeItemRepository = mock(FridgeItemRepository.class);
        productRepository = mock(ProductRepository.class);
        notificationService = new NotificationService(
                notificationRepository,
                memberRepository,
                fridgeItemRepository,
                productRepository
        );
    }

    @Test
    void createExpiryNotificationsCreatesExpiringAndExpiredNotifications() {
        Long memberId = 133L;
        LocalDate today = LocalDate.now();
        FridgeItem dDayItem = fridgeItem(memberId, 1L, 101L, today);
        FridgeItem dThreeItem = fridgeItem(memberId, 2L, 102L, today.plusDays(3));
        FridgeItem expiredItem = fridgeItem(memberId, 3L, 103L, today.minusDays(1));

        given(fridgeItemRepository.findByMemberIdAndExpiryDateBetweenAndIsDeletedFalse(
                memberId,
                today,
                today.plusDays(3)
        )).willReturn(List.of(dDayItem, dThreeItem));
        given(fridgeItemRepository.findByMemberIdAndExpiryDateBeforeAndIsDeletedFalse(
                memberId,
                today
        )).willReturn(List.of(expiredItem));
        given(productRepository.findByProductIdInAndIsActiveTrue(List.of(101L, 102L, 103L)))
                .willReturn(List.of(
                        product(101L, "두부"),
                        product(102L, "계란"),
                        product(103L, "연두부")
                ));

        notificationService.createExpiryNotifications(memberId);

        ArgumentCaptor<Notification> captor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository, times(3)).save(captor.capture());

        List<Notification> notifications = captor.getAllValues();
        assertThat(notifications)
                .extracting(Notification::getType)
                .containsExactly(
                        NotificationType.EXPIRY_SOON,
                        NotificationType.EXPIRY_SOON,
                        NotificationType.EXPIRED
                );
        assertThat(notifications)
                .extracting(Notification::getTargetExpiryDate)
                .containsExactly(today, today.plusDays(3), today.minusDays(1));
        assertThat(notifications)
                .extracting(Notification::getTargetType)
                .containsOnly(NotificationTargetType.FRIDGE_ITEM);
        assertThat(notifications)
                .extracting(Notification::getContent)
                .containsExactly(
                        "두부의 소비기한이 3일 이내입니다.",
                        "계란의 소비기한이 3일 이내입니다.",
                        "연두부의 소비기한이 지났습니다."
                );
    }

    @Test
    void createExpiryNotificationsExcludesNoExpiryAndOtherMembersByQueryCondition() {
        Long memberId = 133L;
        LocalDate today = LocalDate.now();

        given(fridgeItemRepository.findByMemberIdAndExpiryDateBetweenAndIsDeletedFalse(
                memberId,
                today,
                today.plusDays(3)
        )).willReturn(List.of());
        given(fridgeItemRepository.findByMemberIdAndExpiryDateBeforeAndIsDeletedFalse(
                memberId,
                today
        )).willReturn(List.of());

        notificationService.createExpiryNotifications(memberId);

        verify(fridgeItemRepository).findByMemberIdAndExpiryDateBetweenAndIsDeletedFalse(
                memberId,
                today,
                today.plusDays(3)
        );
        verify(fridgeItemRepository).findByMemberIdAndExpiryDateBeforeAndIsDeletedFalse(memberId, today);
        verify(productRepository, never()).findByProductIdInAndIsActiveTrue(any());
        verify(notificationRepository, never()).save(any());
        verifyNoMoreInteractions(fridgeItemRepository);
    }

    @Test
    void createExpiryNotificationsDoesNotCreateDuplicateNotificationForSameItemAndExpiryDate() {
        Long memberId = 133L;
        LocalDate today = LocalDate.now();
        FridgeItem item = fridgeItem(memberId, 1L, 101L, today.plusDays(2));

        given(fridgeItemRepository.findByMemberIdAndExpiryDateBetweenAndIsDeletedFalse(
                memberId,
                today,
                today.plusDays(3)
        )).willReturn(List.of(item));
        given(fridgeItemRepository.findByMemberIdAndExpiryDateBeforeAndIsDeletedFalse(memberId, today))
                .willReturn(List.of());
        given(productRepository.findByProductIdInAndIsActiveTrue(List.of(101L)))
                .willReturn(List.of(product(101L, "두부")));
        given(notificationRepository.existsByMemberIdAndTypeAndTargetTypeAndTargetIdAndTargetExpiryDate(
                memberId,
                NotificationType.EXPIRY_SOON,
                NotificationTargetType.FRIDGE_ITEM,
                1L,
                today.plusDays(2)
        )).willReturn(true);

        notificationService.createExpiryNotifications(memberId);

        verify(notificationRepository, never()).save(any());
    }

    @Test
    void markAsReadMarksOnlyOwnedNotification() {
        Long memberId = 133L;
        Long notificationId = 1L;
        Notification notification = Notification.create(
                memberId,
                NotificationType.EXPIRY_SOON,
                "소비기한 임박 알림",
                "두부의 소비기한이 3일 이내입니다.",
                NotificationTargetType.FRIDGE_ITEM,
                1L,
                LocalDate.now().plusDays(1)
        );

        var member = mock(com.naengpa.naengpamasterbackend.member.entity.Member.class);
        given(member.getId()).willReturn(memberId);
        given(memberRepository.findByEmail("user@example.com")).willReturn(java.util.Optional.of(member));
        given(notificationRepository.findByNotificationIdAndMemberId(notificationId, memberId))
                .willReturn(java.util.Optional.of(notification));

        notificationService.markAsRead("user@example.com", notificationId);

        assertThat(notification.getReadAt()).isNotNull();
        verify(notificationRepository).findByNotificationIdAndMemberId(notificationId, memberId);
    }

    private FridgeItem fridgeItem(Long memberId, Long fridgeItemId, Long productId, LocalDate expiryDate) {
        FridgeItem item = mock(FridgeItem.class);
        given(item.getMemberId()).willReturn(memberId);
        given(item.getFridgeItemId()).willReturn(fridgeItemId);
        given(item.getProductId()).willReturn(productId);
        given(item.getExpiryDate()).willReturn(expiryDate);
        return item;
    }

    private Product product(Long productId, String name) {
        Product product = Product.create(1L, name, null);
        ReflectionTestUtils.setField(product, "productId", productId);
        return product;
    }
}
