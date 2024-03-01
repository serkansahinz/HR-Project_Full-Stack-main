package com.hrproject.service;

import com.hrproject.dto.request.CommentRequestDto;
import com.hrproject.dto.response.CommentResponseDto;
import com.hrproject.mapper.ICommentMapper;
import com.hrproject.repository.ICommentRepository;
import com.hrproject.repository.entity.Comment;
import com.hrproject.repository.enums.EStatus;
import com.hrproject.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends ServiceManager<Comment, String> {
  private final ICommentRepository repository;


  public CommentService(ICommentRepository repository) {
	super(repository);
	this.repository = repository;

  }

  public CommentResponseDto createComment(String userId,String companyId,CommentRequestDto dto) {
	Comment comment = Comment.builder().userId(userId).companyId(companyId).fullName(dto.getName() + " " + dto.getSurname()).header(dto.getHeader()).content(dto.getContent()).companyName(dto.getCompanyName()).build();
	comment = repository.save(comment);
	return CommentResponseDto.builder()
							 .id(comment.getId())
							 .userId(comment.getUserId())
							 .fullName(comment.getFullName())
							 .companyId(comment.getCompanyId())
							 .header(comment.getHeader())
							 .content(comment.getContent())
							 .build();
  }

  public List<CommentResponseDto> findAllPendingCommentsByCompanyId() {

	return ICommentMapper.INSTANCE.toCommentResponseDtoList(repository.findAllPendingCommentsByCompanyId());
  }

  public List<CommentResponseDto> findAllActiveCommentsByCompanyId(String companyId) {

	return ICommentMapper.INSTANCE.toCommentResponseDtoList(repository.findAllActiveCommentsByCompanyId(companyId));
  }

  public List<CommentResponseDto> findAllActiveCommentsByUserId(String userId) {

	return ICommentMapper.INSTANCE.toCommentResponseDtoList(repository.findAllActiveCommentsByUserId(userId));
  }

  public List<CommentResponseDto> findAllPendingCommentsByUserId(String userId) {

	return ICommentMapper.INSTANCE.toCommentResponseDtoList(repository.findAllPendingCommentsByUserId(userId));
  }

  public List<CommentResponseDto> findAllByUserId(String userId) {

	return ICommentMapper.INSTANCE.toCommentResponseDtoList(repository.findAllByUserId(userId));
  }

  public String deleteComment(String commentId) {
	Comment comment = repository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
	repository.delete(comment);
	return "Comment deleted";
  }

  public String activateComment(String commentId) {
	Comment comment = repository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
	comment.setStatus(EStatus.ACTIVE);
	repository.save(comment);
	return "Comment activated";
  }
}
