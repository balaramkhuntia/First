package myblog6.myblog6.repository;

import myblog6.myblog6.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

      List<Comment> findByPostId(long postid);
}
