package domain.system_study_api.exception;

public class UnAuthorizedException extends BaseException {
    public UnAuthorizedException(String message){
        super(message);
    }
}
