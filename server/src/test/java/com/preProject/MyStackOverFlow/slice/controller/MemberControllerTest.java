package com.preProject.MyStackOverFlow.slice.controller;

import com.google.gson.Gson;
import com.preProject.MyStackOverFlow.helper.MemberControllerTestHelper;
import com.preProject.MyStackOverFlow.helper.StubData;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest implements MemberControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    private ResultActions postResultActions;
    private MemberDto.Post post;

    @BeforeEach
    public void init() throws Exception {
        // given
        this.post = (MemberDto.Post) StubData.MockMember.getRequestBody(HttpMethod.POST);
        String content = gson.toJson(post);
        URI uri = getURI();
        this.postResultActions = mockMvc.perform(postRequestBuilder(uri, content));
    }

    @Test
    public void postMemberTest() throws Exception {
        /// given
        // init() 에서..

        // when
        // init() 에서..

        //then
        this.postResultActions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith(""))));
    }

    @Test
    void putMemberTest() throws Exception {
        // given
        String location = getResourceLocation();

        MemberDto.Put put =
                (MemberDto.Put) StubData.MockMember.getRequestBody(HttpMethod.PUT);
        String content = gson.toJson(put);

        // when
        ResultActions actions =
                mockMvc.perform(putRequestBuilder(location, content));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberUserid").value(put.getMemberUserid()))
                .andExpect(jsonPath("$.data.memberPassword").value(put.getMemberPassword()))
                .andExpect(jsonPath("$.data.memberEmail").value(put.getMemberEmail()))
                .andExpect(jsonPath("$.data.memberName").value(put.getMemberName()))
                .andExpect(jsonPath("$.data.memberNickname").value(put.getMemberNickname()))
                .andExpect(jsonPath("$.data.memberDescription").value(put.getMemberDescription()));
    }

    @Test
    void getMemberTest() throws Exception {
        // given
        // init() 에서..

        // when
        String location = getResourceLocation();

        // then
        mockMvc.perform(getRequestBuilder(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberUserid").value(this.post.getMemberUserid()))
                .andExpect(jsonPath("$.data.memberPassword").value(this.post.getMemberPassword()))
                .andExpect(jsonPath("$.data.memberEmail").value(this.post.getMemberEmail()))
                .andExpect(jsonPath("$.data.memberName").value(this.post.getMemberName()))
                .andExpect(jsonPath("$.data.memberNickname").value(this.post.getMemberNickname()))
                .andExpect(jsonPath("$.data.memberDescription").value(this.post.getMemberDescription()));
    }

    @Test
    void deleteMemberTest() throws Exception {
        // given
        // init() 에서 DB에 넣어준다.

        // when
        String location = getResourceLocation();

        // then
        mockMvc.perform(deleteRequestBuilder(location))
                .andExpect(status().isNoContent());
    }

    private String getResourceLocation() {
        String location = this.postResultActions.andReturn().getResponse().getHeader("Location");

        return location;
    }
}
