package controller.validator;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidatorEntity<T> extends AbstractValidatorEntity {
    private boolean isError;
    private T entity;
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();


    public ValidatorEntity(T entity) {
        this.entity = entity;
        constructMessage();
        constructTitle();
    }

    private void constructTitle() {
        this.titleValidationMessage = "No se ha podido insertar el/la " + entity.getClass().getSimpleName() + " debido a los siguientes errores.";
    }

    public void constructMessage() {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        this.contentValidationMessage = "";
        int cont = 0;
        for (ConstraintViolation<T> violation : violations) {
            contentValidationMessage += violation.getMessage() + "\n";
            cont++;
        }
        if (cont > 0) {
            this.isError = true;
        }
    }

    public boolean isError() {
        return this.isError;
    }

}
