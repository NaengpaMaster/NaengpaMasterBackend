package com.naengpa.naengpamasterbackend.score.service;

import com.naengpa.naengpamasterbackend.global.exception.ScoreNotFoundException;
import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.score.dto.response.ScoreResponse;
import com.naengpa.naengpamasterbackend.score.entity.Score;
import com.naengpa.naengpamasterbackend.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService{

    private final ScoreRepository scoreRepository;
    private final MemberRepository memberRepository;


    @Override
    public ScoreResponse getScore(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));

//        if (member.isInactive()) {
//            throw new UsernameNotFoundException("탈퇴한 회원입니다.");
//        }

        Score score = scoreRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new ScoreNotFoundException());

        return new ScoreResponse(score.getScore());

    }
}
