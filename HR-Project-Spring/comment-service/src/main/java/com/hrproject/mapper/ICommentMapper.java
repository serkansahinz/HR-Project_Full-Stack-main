package com.hrproject.mapper;

import com.hrproject.dto.response.CommentResponseDto;
import com.hrproject.repository.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentMapper {

  //instance of mapper

  ICommentMapper INSTANCE = Mappers.getMapper(ICommentMapper.class);

  List<CommentResponseDto> toCommentResponseDtoList(List<Comment> allPendingCommentsByCompanyId);
}
