package domain.system_study_api.exception;

public class UploadFileFailException extends BaseException {
    public UploadFileFailException(String message, Throwable cause) {
        super(message,  cause);
    }

    public UploadFileFailException(String message) {
        super(message);
    }
}
