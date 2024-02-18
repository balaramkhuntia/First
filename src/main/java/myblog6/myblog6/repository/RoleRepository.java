package myblog6.myblog6.repository;

import myblog6.myblog6.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role,Long> {

       Optional<Role> findByName(String name);
}
