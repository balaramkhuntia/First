package myblog6.myblog6.service;

import myblog6.myblog6.payload.PostDto;
import myblog6.myblog6.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);

    PostDto getPostById(long id);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    void deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);
}
