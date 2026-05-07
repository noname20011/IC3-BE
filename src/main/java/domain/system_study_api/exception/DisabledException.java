package domain.system_study_api.exception;

public class DisabledException extends BaseException{
    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisabledException(String message) {
        super(message);
    }
}
