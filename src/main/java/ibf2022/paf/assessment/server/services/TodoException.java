package ibf2022.paf.assessment.server.services;

public class TodoException extends Exception{

    public TodoException() {
        super();
    }

    public TodoException(String message) {
        super(message);
    }

    public TodoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
