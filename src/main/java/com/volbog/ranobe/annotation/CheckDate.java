package com.volbog.ranobe.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Date;
import org.apache.commons.validator.routines.DateValidator;

/**
 * china mobile  verification
 *
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code Date}</li>
 *     <li>{@code LocalDate}</li>
 * </ul>
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-09-02
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CheckDate.CheckDateValidatorForDate.class,
    CheckDate.CheckDateValidatorForLocalDate.class})
public @interface CheckDate {

  int value() default 0;

  String message() default "invalid date";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@link CheckDate} annotations on the same element
   */
  @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {

    CheckDate[] value();
  }

  class CheckDateValidatorForDate implements ConstraintValidator<CheckDate, Date> {

    @Override
    public void initialize(CheckDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date content, ConstraintValidatorContext constraintValidatorContext) {
      if (content == null || "1970-01-01".equals(content.toString())) {
        return false;
      }
      return DateValidator.getInstance().isValid(content.toString(), "yyyy-MM-dd");
    }
  }

  class CheckDateValidatorForLocalDate implements ConstraintValidator<CheckDate, LocalDate> {

    @Override
    public void initialize(CheckDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate content,
        ConstraintValidatorContext constraintValidatorContext) {
      if (content == null || "1970-01-01".equals(content.toString())) {
        return false;
      }
      return DateValidator.getInstance().isValid(content.toString(), "yyyy-MM-dd");
    }
  }
}
