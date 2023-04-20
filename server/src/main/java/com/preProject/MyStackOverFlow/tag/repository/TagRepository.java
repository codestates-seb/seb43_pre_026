package com.preProject.MyStackOverFlow.tag.repository;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.tag.dto.TagDto;
import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);
    Page<Tag> findByTagNameContaining(String tagName, Pageable pageable);

}
