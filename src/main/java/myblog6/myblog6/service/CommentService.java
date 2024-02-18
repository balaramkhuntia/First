package myblog6.myblog6.service;

import myblog6.myblog6.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto saveComment(long postid, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(long postid);

    CommentDto getCommentByCommentId(long id);

    List<CommentDto> getAllComment();

    void deleteComment(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId,CommentDto commentDto);
}
