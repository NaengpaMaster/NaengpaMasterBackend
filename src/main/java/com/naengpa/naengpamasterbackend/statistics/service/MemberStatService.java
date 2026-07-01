package com.naengpa.naengpamasterbackend.statistics.service;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredProductCategoryResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.ExpiredRecordResponse;
import com.naengpa.naengpamasterbackend.statistics.dto.response.TopIngredientQueryResult;
import com.naengpa.naengpamasterbackend.statistics.dto.response.TopIngredientResponse;
import com.naengpa.naengpamasterbackend.statistics.repository.ExpiredProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberStatService {

    private final ExpiredProductRepository expiredProductRepository;
    private final MemberRepository memberRepository;

    public List<TopIngredientResponse> getTop5Ingredients(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));

        List<TopIngredientQueryResult> topIngredientQueryResults =
                expiredProductRepository.findTop5ExpiredProductByMemberId(member.getId());

        List<TopIngredientResponse> topIngredientResponses = new ArrayList<>();

        for (int i = 0; i < topIngredientQueryResults.size(); i++) {

            TopIngredientQueryResult queryResult = topIngredientQueryResults.get(i);
            topIngredientResponses.add(new TopIngredientResponse(
                    i + 1,
                    queryResult.ingredientName(),
                    queryResult.expiredCount()
            ));

        }
        return topIngredientResponses;
    }


    public List<ExpiredProductCategoryResponse> getExpiredProductCategories(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        return expiredProductRepository.findExpiredCategoriesByMemberId(member.getId());
    }


    public List<ExpiredRecordResponse> getExpiredRecords(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));

        return expiredProductRepository.findExpiredRecordsByMemberId(member.getId());
    }

}
