package com.naengpa.naengpamasterbackend.member.repository;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.entity.MemberFavoriteFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberFavoriteFoodRepository extends JpaRepository<MemberFavoriteFood, Long> {

    @Query("""
            select memberFavoriteFood
            from MemberFavoriteFood memberFavoriteFood
            join fetch memberFavoriteFood.foodCategory foodCategory
            where memberFavoriteFood.member = :member
            order by memberFavoriteFood.id asc
            """)
    List<MemberFavoriteFood> findAllByMemberOrderByIdAsc(Member member);

    void deleteAllByMember(Member member);
}
