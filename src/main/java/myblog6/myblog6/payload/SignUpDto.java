package myblog6.myblog6.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignUpDto {
    private String email;
    private String name;
    private String username;
    private String password;
}
