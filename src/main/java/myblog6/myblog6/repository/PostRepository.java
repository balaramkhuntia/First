package myblog6.myblog6.repository;

import myblog6.myblog6.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
