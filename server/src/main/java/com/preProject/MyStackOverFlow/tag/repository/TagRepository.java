package com.preProject.MyStackOverFlow.tag.repository;

import com.preProject.MyStackOverFlow.tag.dto.TagDto;
import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTagName(String tagName);
}
