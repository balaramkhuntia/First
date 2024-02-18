package myblog6.myblog6.controller;

import jakarta.validation.Valid;
import myblog6.myblog6.entity.Post;
import myblog6.myblog6.payload.PostDto;
import myblog6.myblog6.payload.PostResponse;
import myblog6.myblog6.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
     @PostMapping()
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result){
         if (result.hasErrors()){
             return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
         PostDto postdto = postService.savePost(postDto);
         return new ResponseEntity<>(postdto, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public PostResponse getAllPost(@RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
                                   @RequestParam(value = "pageSize",defaultValue = "2",required = false)int pageSize,
                                   @RequestParam (value = "sortBy",defaultValue = "id",required = false)String sortBy,
                                   @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
        PostResponse postResponse = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return postResponse;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
     @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePost(@PathVariable ("id") long id){
         postService.deletePost(id);
         return new ResponseEntity<>("Delete Sucessfuly",HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePost(@PathVariable("id") long id,@RequestBody PostDto postDto){
        PostDto dtoo = postService.updatePost(id, postDto);

        return new ResponseEntity<>(dtoo,HttpStatus.OK);
    }
}
