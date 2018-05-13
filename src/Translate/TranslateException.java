package Translate;

public class TranslateException extends RuntimeException {
    private static final long serialVersionUID = -703489719333345831L;

    public TranslateException() {
        super();
    }

    public TranslateException(String message) {
        super(message);
    }

    public TranslateException(Throwable cause) {
        super(cause);
    }

    public TranslateException(String message, Throwable cause) {
        super(message, cause);
    }

    protected TranslateException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
