package myblog6.myblog6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogApiException extends RuntimeException{

    public BlogApiException(String msg){
        super(msg);
    }
}
