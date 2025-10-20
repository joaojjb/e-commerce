package produtos.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {
    
    private final HttpStatus status;
    
    public DomainException(final String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

}

