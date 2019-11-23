package controller.validator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidatorEntityByException extends AbstractValidatorEntity {
    private Class entityClass;
    private ConstraintViolationException constraintViolationException;

    public ValidatorEntityByException(Class entityClass, ConstraintViolationException constraintViolationException) {
        this.entityClass = entityClass;
        this.constraintViolationException = constraintViolationException;
        this.constuctMessages();
    }

    private void constuctMessages() {
        this.titleValidationMessage = constuctTitleValidationMessages();
        this.contentValidationMessage = contructContentValidationMessage();
    }

    private String constuctTitleValidationMessages() {
        return "No se ha podido insertar el/la " + entityClass.getSimpleName() + " debido a los siguientes errores.";
    }

    public String contructContentValidationMessage() {
        String contentMessage = "";
        for (ConstraintViolation constraintViolation : constraintViolationException.getConstraintViolations()) {
            contentMessage += constraintViolation.getMessage() + "\n";
        }
        return contentMessage;
    }
}
