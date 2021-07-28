package javaeems.chapter4.photo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("imageUploadValidator")
public class ImageUploadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;
        if (!file.getContentType().equals("application/octet-stream")
                && !file.getContentType().equals("image/jpeg")) {
            throw new ValidatorException(new FacesMessage("The file you tried to upload is not an image file. Please try again."));
        }
    }
    
    
    
}
