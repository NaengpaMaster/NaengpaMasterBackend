package com.naengpa.naengpamasterbackend.admin.repository;

import com.naengpa.naengpamasterbackend.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminScoreRepository extends JpaRepository<Score, Long> {

    // 냉파 점수 평균 조회
    @Query("SELECT AVG(s.score) FROM Score s")
    Double findScoreAverage();

}
