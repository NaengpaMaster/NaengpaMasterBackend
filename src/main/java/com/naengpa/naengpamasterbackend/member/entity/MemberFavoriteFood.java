package com.naengpa.naengpamasterbackend.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "member_favorite_foods",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "food_category_id"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFavoriteFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_favorite_food_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCategory foodCategory;

    private MemberFavoriteFood(Member member, FoodCategory foodCategory) {
        this.member = member;
        this.foodCategory = foodCategory;
    }

    public static MemberFavoriteFood create(Member member, FoodCategory foodCategory) {
        return new MemberFavoriteFood(member, foodCategory);
    }
}
