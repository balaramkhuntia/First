package myblog6.myblog6.exception;

import myblog6.myblog6.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHendaler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails>handeledBlogApiException(BlogApiException blogApiException,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), blogApiException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails>handeledResourceNotFoundException(ResourceNotFound resourceNotFound,WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFound.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails>handeledGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }
}
