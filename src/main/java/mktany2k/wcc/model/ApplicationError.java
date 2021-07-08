package mktany2k.wcc.model;

import java.util.List;

public class ApplicationError {
    private final List<String> errors;


    public ApplicationError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
