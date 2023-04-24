package com.preProject.MyStackOverFlow.tag.mapper;

import com.preProject.MyStackOverFlow.tag.dto.TagDto;
import com.preProject.MyStackOverFlow.tag.entitiy.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag tagPostToTag(TagDto.Post post);
    TagDto.Response tagToTagResponse(Tag tag);
    List<TagDto.Response> tagsToTagResponses(List<Tag> tags);
}
