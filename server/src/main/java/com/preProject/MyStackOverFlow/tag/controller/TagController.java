package com.preProject.MyStackOverFlow.tag.controller;

import com.preProject.MyStackOverFlow.tag.mapper.TagMapper;
import com.preProject.MyStackOverFlow.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RequestMapping("/tags")
@RestController
public class TagController {


}
