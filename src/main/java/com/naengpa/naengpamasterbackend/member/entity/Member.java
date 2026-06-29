package com.naengpa.naengpamasterbackend.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private static final String[] NICKNAME_PREFIXES = {
            "트월킹추는",
            "컴퓨터하는",
            "간식사오는",
            "냉장고지키는",
            "레시피찾는",
            "장보러가는",
            "새벽요리하는",
            "소스뿌리는",
            "반찬꺼내는",
            "유통기한보는",
            "도마위에서춤추는",
            "전자레인지돌리는",
            "후라이팬드는",
            "앞치마입은",
            "몰래간식먹는",
            "김치통여는",
            "냉동실뒤지는",
            "국자휘두르는",
            "계량컵든",
            "소금간하는",
            "양념섞는",
            "도시락싸는",
            "식탁닦는",
            "라면물맞추는",
            "배달앱끄는",
            "냄비뚜껑여는",
            "칼질연습하는",
            "치즈늘리는",
            "밥솥기다리는",
            "쿠폰찾는",
            "접시꺼내는",
            "김가루뿌리는",
            "냉파성공한",
            "재료구출하는",
            "마감세일찾는",
            "숟가락드는",
            "프라이팬예열하는",
            "식재료정리하는",
            "반찬통닫는",
            "냉장고노크하는"
    };

    private static final String[] NICKNAME_NOUNS = {
            "샐러리",
            "감자튀김",
            "간장게장",
            "두부",
            "브로콜리",
            "김치전",
            "계란말이",
            "양파링",
            "마늘빵",
            "고등어",
            "오이냉국",
            "깻잎장아찌",
            "버터감자",
            "참치마요",
            "김치볶음밥",
            "된장찌개",
            "계란프라이",
            "마라두부",
            "콩나물",
            "치즈스틱",
            "어묵꼬치",
            "소떡소떡",
            "불고기",
            "주먹밥",
            "새우튀김",
            "떡볶이",
            "라면사리",
            "닭강정",
            "감자조림",
            "멸치볶음",
            "오징어젓갈",
            "김말이",
            "비빔국수",
            "카레라이스",
            "돈가스",
            "삼각김밥",
            "양배추",
            "버섯전골",
            "고구마맛탕",
            "파김치",
            "참기름",
            "꽁치조림",
            "미역국",
            "깍두기"
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 1000)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberRole role = MemberRole.USER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberStatus status = MemberStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "household_type", nullable = false, length = 20)
    private HouseholdType householdType = HouseholdType.ETC;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "maintenance_period", nullable = false)
    private int maintenancePeriod;

    public static Member createUser(String email, String encodedPassword, String nickname, HouseholdType householdType) {
        Member member = new Member();
        member.email = email;
        member.password = encodedPassword;
        member.nickname = nickname;
        member.role = MemberRole.USER;
        member.status = MemberStatus.ACTIVE;
        member.householdType = householdType == null ? HouseholdType.ETC : householdType;
        member.maintenancePeriod = 0;
        return member;
    }

    public boolean isInactive() {
        return status == MemberStatus.INACTIVE || deletedAt != null;
    }

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static String generateRandomNickname() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String prefix = NICKNAME_PREFIXES[random.nextInt(NICKNAME_PREFIXES.length)];
        String noun = NICKNAME_NOUNS[random.nextInt(NICKNAME_NOUNS.length)];
        int suffix = random.nextInt(1000, 10000);
        return prefix + " " + noun + suffix;
    }

    public void updateStatus(MemberStatus status) {
        this.status = status;
    }

    public void updateRole(MemberRole role) {
        this.role = role;
    }
}
