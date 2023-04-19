package com.preProject.MyStackOverFlow.answer.repository;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
