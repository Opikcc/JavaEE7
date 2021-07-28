
import javax.faces.application.FacesMessage;
import javax.faces.validator.*;

import javax.faces.context.FacesContext;
import javax.faces.component.*;

@FacesValidator("myValidator")
public class MyValidator implements Validator {

    @Override
    public void validate(FacesContext context,
            UIComponent component,
            java.lang.Object value) throws ValidatorException {
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Number must be even");
        throw new ValidatorException(fm);

    }

}
