package com.hrproject.controller;

import com.hrproject.dto.request.CommentRequestDto;
import com.hrproject.dto.response.CommentResponseDto;
import com.hrproject.service.CommentService;
import com.hrproject.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrproject.constant.EndPoints.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {
  private final CommentService commentService;
  private final JwtTokenManager jwtTokenManager;

  @PostMapping(CREATE_COMMENT + "/{userId}/{companyId}")
  @PreAuthorize("hasAuthority('EMPLOYEE') || hasAuthority('COMPANY_MANAGER')")
  public ResponseEntity<CommentResponseDto> createComment(@PathVariable String userId,@PathVariable String companyId,@RequestBody CommentRequestDto dto) {
	return ResponseEntity.ok(commentService.createComment(userId,companyId,dto));
  }

  @GetMapping(FIND_ALL_PENDING_COMMENTS)
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<CommentResponseDto>> findAllPendingComments() {
	return ResponseEntity.ok(commentService.findAllPendingCommentsByCompanyId());
  }

  @GetMapping(FIND_ALL_ACTIVE_COMMENTS_BY_COMPANY_ID + "/{companyId}")
  public ResponseEntity<List<CommentResponseDto>> findAllActiveCommentsByCompanyId(@PathVariable String companyId) {
	return ResponseEntity.ok(commentService.findAllActiveCommentsByCompanyId(companyId));
  }

  @GetMapping(FIND_ALL_ACTIVE_COMMENTS_BY_USER_ID + "/{userId}")
  public ResponseEntity<List<CommentResponseDto>> findAllActiveCommentsByUserId(@PathVariable String userId) {
	return ResponseEntity.ok(commentService.findAllActiveCommentsByUserId(userId));
  }

  @GetMapping(FIND_ALL_PENDING_COMMENTS_BY_USER_ID + "/{userId}")
  public ResponseEntity<List<CommentResponseDto>> findAllPendingCommentsByUserId(@PathVariable String userId) {
	return ResponseEntity.ok(commentService.findAllPendingCommentsByUserId(userId));
  }

  @GetMapping(FIND_ALL_BY_USER_ID + "/{userId}")
  public ResponseEntity<List<CommentResponseDto>> findAllByUserId(@PathVariable String userId) {
	return ResponseEntity.ok(commentService.findAllByUserId(userId));
  }

  @DeleteMapping(DELETE_COMMENT + "/{commentId}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
	return ResponseEntity.ok(commentService.deleteComment(commentId));
  }

  @PutMapping(ACTIVATE_COMMENT + "/{commentId}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<String> activateComment(@PathVariable String commentId) {
	return ResponseEntity.ok(commentService.activateComment(commentId));
  }
}
