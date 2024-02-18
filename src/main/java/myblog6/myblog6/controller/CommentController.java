package myblog6.myblog6.controller;

import myblog6.myblog6.payload.CommentDto;
import myblog6.myblog6.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{postid}")
    public ResponseEntity<CommentDto>saveCommentByPostId(@PathVariable("postid") long postid,@RequestBody CommentDto commentDto){
        CommentDto commentDto1 = commentService.saveComment(postid, commentDto);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    @GetMapping("/{postid}")
    public List<CommentDto> getCommentByPostId(@PathVariable("postid") long postid){

        List<CommentDto> ndto = commentService.getCommentByPostId(postid);

        return ndto;

    }
    //http://localhost:8080/api/comments/comment/
    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentDto>getCommentByCommentId(@PathVariable("id") long id){
        CommentDto dto = commentService.getCommentByCommentId(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping
    public List<CommentDto>getAllComment(){
        List<CommentDto> allComment = commentService.getAllComment();
        return allComment;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
     public ResponseEntity<String>deleteComment(@PathVariable("postId") long postId,@PathVariable("commentId") long commentId){

        commentService.deleteComment(postId,commentId);

        return new ResponseEntity<>("Delete Sucessfully",HttpStatus.OK);
     }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
     @PutMapping("/posts/{postId}/comments/{commentId}")
     public ResponseEntity<CommentDto>updateComment(@PathVariable("postId") long postId,@PathVariable("commentId")long commentId,@RequestBody CommentDto commentDto){
         CommentDto dto = commentService.updateComment(postId, commentId, commentDto);
         return new ResponseEntity<>(dto,HttpStatus.OK);
     }
}
