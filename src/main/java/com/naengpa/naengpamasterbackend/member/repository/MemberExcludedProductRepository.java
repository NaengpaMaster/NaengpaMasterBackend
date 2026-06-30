package com.naengpa.naengpamasterbackend.member.repository;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberExcludedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberExcludedProductRepository extends JpaRepository<MemberExcludedProduct, Long> {

    @Query("""
            select memberExcludedProduct
            from MemberExcludedProduct memberExcludedProduct
            join fetch memberExcludedProduct.product product
            where memberExcludedProduct.member = :member
            """)
    List<MemberExcludedProduct> findAllByMemberWithProduct(Member member);

    void deleteAllByMember(Member member);
}
