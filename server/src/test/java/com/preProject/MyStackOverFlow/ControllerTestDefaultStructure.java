package com.preProject.MyStackOverFlow;

import com.preProject.MyStackOverFlow.answer.dto.AnswerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest       // (1)
@AutoConfigureMockMvc  // (2)
public class ControllerTestDefaultStructure {
    // (3)
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;
    }


    // (4)

