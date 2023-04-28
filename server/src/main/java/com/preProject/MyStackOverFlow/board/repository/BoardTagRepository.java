package com.preProject.MyStackOverFlow.board.repository;

import com.preProject.MyStackOverFlow.board.entity.BoardTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {
}
