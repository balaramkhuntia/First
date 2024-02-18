package myblog6.myblog6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
                                        @UniqueConstraint(columnNames={"email"})})
public class User {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String password;
    private String username;

    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
              inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> role;
}
