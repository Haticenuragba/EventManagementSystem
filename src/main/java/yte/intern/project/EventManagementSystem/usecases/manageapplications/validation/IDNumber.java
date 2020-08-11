package yte.intern.project.EventManagementSystem.usecases.manageapplications.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IDNumberValidator.class)
public @interface IDNumber {

	String message() default "Id Number is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
