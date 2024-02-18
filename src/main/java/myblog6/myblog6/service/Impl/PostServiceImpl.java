package myblog6.myblog6.service.Impl;

import myblog6.myblog6.entity.Post;
import myblog6.myblog6.exception.ResourceNotFound;
import myblog6.myblog6.payload.PostDto;
import myblog6.myblog6.payload.PostResponse;
import myblog6.myblog6.repository.PostRepository;
import myblog6.myblog6.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto savePost(PostDto postDto) {

     Post post =   maptoEntity(postDto);

        Post newpost = postRepository.save(post);
   PostDto postdto =     maptoDto(newpost);

        return postdto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found for the id"));
        PostDto dto = maptoDto(post);
        return dto;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest of = PageRequest.of(pageNo, pageSize,sort);
        Page<Post> postPage = postRepository.findAll(of);
        List<Post> content = postPage.getContent();
        List<PostDto> alldto = content.stream().map(posts -> maptoDto(posts)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(alldto);
        postResponse.setPageNo(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setTotalElement(postPage.getTotalElements());
        postResponse.setLast(postPage.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(long id) {

        postRepository.deleteById(id);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found for this id"));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(post.getContent());
        Post npost = postRepository.save(post);
        PostDto dtoo = maptoDto(npost);
        return dtoo;
    }

    public PostDto maptoDto(Post newpost) {
        PostDto postDto = modelMapper.map(newpost, PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setContent(newpost.getContent());
//        postDto.setDescription(newpost.getDescription());
//        postDto.setTitle(newpost.getTitle());
        return postDto;
    }

    public Post maptoEntity(PostDto postDto) {
      Post post =   modelMapper.map(postDto, Post.class);
//        Post post = new Post();
//
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
//        post.setTitle(postDto.getTitle());
        return post;
    }
}
