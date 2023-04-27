package com.preProject.MyStackOverFlow.board.repository;

import com.preProject.MyStackOverFlow.board.entity.Board;
import com.preProject.MyStackOverFlow.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    default Specification<Board> findByKeyword(String keyword) {
        return (root, query, cb) -> {
            Predicate titlePredicate = cb.like(root.get("title"), "%" + keyword + "%");
            Predicate contentPredicate = cb.like(root.get("content"), "%" + keyword + "%");
            Predicate memberNicknamePredicate = cb.like(root.get("member").get("memberNickname"), "%" + keyword + "%");
            Predicate tagNamePredicate = cb.like(root.join("boardTags").get("tag").get("tagName"), "%" + keyword + "%");
            return cb.or(titlePredicate, contentPredicate, memberNicknamePredicate, tagNamePredicate);
        };
    }

    Page<Board> findAll(Specification<Board> keyword, Pageable pageable);
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findByContentContaining(String content, Pageable pageable);
    Page<Board> findByMemberMemberNicknameContaining(String memberNickname, Pageable pageable);
    @Query("SELECT b FROM Board b JOIN b.boardTags bt JOIN bt.tag t WHERE t.tagName LIKE %:tagName%")
    Page<Board> findByTagNameContaining(@Param("tagName") String tagName, Pageable pageable);

    List<Board> findByMember(Member member);
}
