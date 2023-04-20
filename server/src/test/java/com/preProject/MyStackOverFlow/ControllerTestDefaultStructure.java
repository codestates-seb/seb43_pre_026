package com.preProject.MyStackOverFlow;

import com.preProject.MyStackOverFlow.answer.controller.AnswerController;
import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import com.preProject.MyStackOverFlow.answer.entity.Answer;
import com.preProject.MyStackOverFlow.answer.mapper.AnswerMapper;
import com.preProject.MyStackOverFlow.answer.mapper.Response;
import com.preProject.MyStackOverFlow.answer.service.AnswerService;
import com.preProject.MyStackOverFlow.member.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static utill.ApiDocumentUtils.getRequestPreProcessor;
import static utill.ApiDocumentUtils.getResponsePreProcessor;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)   // (2)
@AutoConfigureRestDocs  // (2)
public class ControllerTestDefaultStructure {
    // (3)
    @Autowired
    private MockMvc mockMvc;

    // (1)
    @MockBean
    private AnswerService answerService;

    @MockBean
    private Response response;

    // (2)
    @MockBean
    private AnswerMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    void postAnswerTest() throws Exception {
        // given  (1)
        AnswerDto.Post post = new AnswerDto.Post("답변 입력");
        String content = gson.toJson(post); // (2)

        //(4)
        given(mapper.answertoToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());

        // (5)
        Answer mockResultAnswer = new Answer();
        mockResultAnswer.setAnswerId(1L);
        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);


        // when
        ResultActions actions =
                mockMvc.perform(                        // (3)
                        post("/answer")  // (4)
                                .accept(MediaType.APPLICATION_JSON) // (5)
                                .contentType(MediaType.APPLICATION_JSON) // (6)
                                .content(content)   // (7)
                );
        // then
        actions.andExpect(status().isCreated())
                .andDo(document(       // (7)
                        "answer",     // (7-1)
                        getRequestPreProcessor(),      // (7-2)
                        getResponsePreProcessor(),     // (7-3)
                        requestFields(             // (7-4)
                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("멤버 Id"),
//                                        fieldWithPath("boardId").type(JsonFieldType.STRING).description("게시글 Id"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답변 입력") // (7-5)
                                )
                        ),
//                        requestFields(             // (7-4)
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.STRING).description("멤버 Id"),
//                                        fieldWithPath("boardId").type(JsonFieldType.STRING).description("게시글 Id"),
//                                        fieldWithPath("boardId").type(JsonFieldType.STRING).description("부모 답변 Id"),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답변 입력") // (7-5)
//                                )
//                        ),
                        responseHeaders(        // (7-6)
                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
                        )
                ));
    }

//    @Test
//    void getAnswerTest() throws Exception {
//        // given  (1)
//        AnswerDto.Post post = new AnswerDto.Post("댓글 입력");
//        String content = gson.toJson(post); // (2)
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(                        // (3)
//                        post("/answer")  // (4)
//                                .accept(MediaType.APPLICATION_JSON) // (5)
//                                .contentType(MediaType.APPLICATION_JSON) // (6)
//                                .content(content)   // (7)
//                );
//
//        String location = actions.andReturn().getResponse().getHeader("Location"); // "/v11/members/1"
//
//        // when / then
//        mockMvc.perform(
//                get(location).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())    // (4)
//                .andExpect(jsonPath("$.data.email").value(post.getContent()))   // (5)
//                .andExpect(jsonPath("$.data.name").value(post.getName()))     // (6)
//                .andExpect(jsonPath("$.data.phone").value(post.getPhone()));  // (7)
//
//    }


}


// (4)

