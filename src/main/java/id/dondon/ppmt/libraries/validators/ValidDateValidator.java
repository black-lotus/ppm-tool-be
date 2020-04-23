package id.dondon.ppmt.libraries.validators;

import com.google.common.base.Strings;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

  private Boolean isOptional;
  private String format;

  @Override
  public void initialize(ValidDate validDate) {
    this.isOptional = validDate.optional();
    this.format = validDate.format();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    boolean validDate = isValidFormat(format, value);

    return isOptional ? (validDate || (Strings.isNullOrEmpty(value))) : validDate;
  }

  private static boolean isValidFormat(String format, String value) {
    Date date = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      if (value != null){
        date = sdf.parse(value);
        if (!value.equals(sdf.format(date))) {
          date = null;
        }
      }

    } catch (ParseException ex) {
    }

    return date != null;
  }

}
