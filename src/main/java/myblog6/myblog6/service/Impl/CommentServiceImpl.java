package myblog6.myblog6.service.Impl;

import myblog6.myblog6.entity.Comment;
import myblog6.myblog6.entity.Post;
import myblog6.myblog6.exception.BlogApiException;
import myblog6.myblog6.exception.ResourceNotFound;
import myblog6.myblog6.payload.CommentDto;
import myblog6.myblog6.repository.CommentRepository;
import myblog6.myblog6.repository.PostRepository;
import myblog6.myblog6.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository,CommentRepository commentRepository,ModelMapper modelMapper){

        this.postRepository=postRepository;
        this.commentRepository=commentRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto saveComment(long postid, CommentDto commentDto) {
      Comment comment =  mapToEntity(commentDto);
        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFound("Post not found for this id"));

         comment.setPost(post);
        Comment newcomment = commentRepository.save(comment);
       CommentDto commentDto1= mapToDto(newcomment);
        return commentDto1;
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postid) {
        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFound("PostId not found for this Id:" + postid));
        List<Comment> comment = commentRepository.findByPostId(postid);
        List<CommentDto> ndto = comment.stream().map(s -> mapToDto(s)).collect(Collectors.toList());

        return ndto;
    }

    @Override
    public CommentDto getCommentByCommentId(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Comment not found for this id"));
        CommentDto commentDto = mapToDto(comment);
        return commentDto;
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> collect = comments.stream().map(k -> mapToDto(k)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post Id not found for this id:" + postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFound("Comment not found for this id" + commentId));

        if(comment.getPost().getId()!= post.getId()){
            throw new BlogApiException("Post Does not belongs to this comments");
        }
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId,CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post not found for this id:" + postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFound("Comment not found for thid id:" + commentId));
             if (comment.getPost().getId()!=post.getId()){
                 throw new BlogApiException("Comment not belongs to this postId");
             }

       comment.setBody(commentDto.getBody());
             comment.setName(commentDto.getName());
             comment.setEmail(commentDto.getEmail());
        Comment ncomment = commentRepository.save(comment);
         CommentDto dto =   mapToDto(ncomment);

        return dto;

    }

    private CommentDto mapToDto(Comment newcomment) {
        CommentDto commentDto = modelMapper.map(newcomment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }


}
