package myblog6.myblog6.payload;

import lombok.Data;

@Data
public class CommentDto {

    private String name;
    private String email;
    private String body;
}
