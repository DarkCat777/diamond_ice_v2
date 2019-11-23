package controller.validator;

public abstract class AbstractValidatorEntity {
    protected String contentValidationMessage;
    protected String titleValidationMessage;

    public String getContentValidationMessage() {
        return contentValidationMessage;
    }

    public String getTitleValidationMessage() {
        return titleValidationMessage;
    }
}
