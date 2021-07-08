package mktany2k.wcc.controller.exception;

public class PostalCodeNotFoundException extends RuntimeException {
    public PostalCodeNotFoundException(String from) {
        super("Post code '" + from + "' not found");
    }
}
