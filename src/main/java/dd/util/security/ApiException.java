package dd.util.security;

public class ApiException extends Exception {

    private String errorCode;
    private String errorMessage;


    public ApiException(){
        super();
    }

    public ApiException(String message,Throwable cause){
        super(message,cause);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String errorCode, String errorMessage) {
        super(errorCode+":"+errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
