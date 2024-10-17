package kg.alatoo.library.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiException extends RuntimeException{

    private final HttpStatusCode statusCode;

    public ApiException(String message, HttpStatusCode code){
        super(message);
        this.statusCode = code;
    }
}
