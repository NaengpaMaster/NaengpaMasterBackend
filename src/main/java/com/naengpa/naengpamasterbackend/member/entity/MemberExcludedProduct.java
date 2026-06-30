package com.naengpa.naengpamasterbackend.member.entity;

import com.naengpa.naengpamasterbackend.product.entity.Product;
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
        name = "member_avoid_products",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "product_id"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberExcludedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_avoid_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private MemberExcludedProduct(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

    public static MemberExcludedProduct create(Member member, Product product) {
        return new MemberExcludedProduct(member, product);
    }
}
