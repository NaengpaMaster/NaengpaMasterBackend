package com.naengpa.naengpamasterbackend.score.repository;

import com.naengpa.naengpamasterbackend.score.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
