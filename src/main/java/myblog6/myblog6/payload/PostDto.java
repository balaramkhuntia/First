package myblog6.myblog6.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    @NotEmpty
    @Size(max = 10,message = "more then 10 not allowed")
    private String title;
    @NotEmpty
    @Size(min = 2,message = "description should be two charector")
    private String description;
    @NotEmpty
    @Size(min=2,message = "content should be above 2 charector")
    private String content;
}
