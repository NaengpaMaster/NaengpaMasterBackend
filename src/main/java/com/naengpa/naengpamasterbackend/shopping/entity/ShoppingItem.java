package com.naengpa.naengpamasterbackend.shopping.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "shopping_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShoppingItem {

    @Id
    @Column(name = "shopping_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shoppingItemId;

    @Column(name = "member_id", nullable = false)
    Long memberId;

    @Column(name = "product_id", nullable = false)
    Long productId;

    @Column(name = "quantity", length = 100)
    String quantity;

    @Column(name = "is_purchased",  nullable = false)
    Boolean isPurchased;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @Column(name = "is_deleted", nullable = false)
    Boolean isDeleted;

    @Column(name = "deleted_at")
    LocalDate deletedAt;

    //생성
    public static ShoppingItem create(
        Long memberId,
        Long productId,
        String quantity
    ) {
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.memberId = memberId;
        shoppingItem.productId = productId;
        shoppingItem.quantity = quantity;
        shoppingItem.isPurchased = false;
        shoppingItem.createdAt = LocalDateTime.now();
        shoppingItem.isDeleted = false;

        return shoppingItem;
    }


}
