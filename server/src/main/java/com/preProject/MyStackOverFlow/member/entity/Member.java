package com.preProject.MyStackOverFlow.member.entity;

import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    public Member(long memberId) {
        this.memberId = memberId;
    }

    @Column(length = 50, nullable = false, unique = true)
    private String memberUserid;

    @Column(length = 200, nullable = false)
    private String memberPassword;

    @Column(length = 50, nullable = false, unique = true, updatable = false)
    private String memberEmail;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 20, nullable = false)
    private String memberRole;

    @Column(length = 20, nullable = false, unique = true)
    private String memberNickname;

    @Column(length = 50)
    private String memberDescription;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Member(String memberUserid, String memberPassword, String memberEmail, String memberName, String memberNickname, String memberDescription) {
        this.memberUserid = memberUserid;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberDescription = memberDescription;
    }

    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
