package com.preProject.MyStackOverFlow.board.repository;

import com.preProject.MyStackOverFlow.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
