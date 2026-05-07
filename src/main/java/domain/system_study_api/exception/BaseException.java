package domain.system_study_api.exception;

public class BaseException extends RuntimeException {
    BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    BaseException(String message) {
        super(message);
    }
}
