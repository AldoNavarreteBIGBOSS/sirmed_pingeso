/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



@FacesValidator("RutValidator")
public class RutValidator implements Validator {
   
    private static final String RUT_PATTERN = "^[0-9]+$";
 
	private Pattern pattern;
	private Matcher matcher;
 
	public RutValidator(){
		  pattern = Pattern.compile(RUT_PATTERN);
	}
 
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg =
                    new FacesMessage("El rut ingresado no es válido",
                    "Deben ser sólo números");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (value.toString().length() > 8 || value.toString().length() < 7) {
            FacesMessage msg =
                    new FacesMessage("El rut ingresado no es válido",
                    "No concuerda la cantidad de dígitos");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }
        if (value.toString().length() == 0) {
            FacesMessage msg =
                    new FacesMessage("El rut ingresado no es válido",
                    "No puede quedar en blanco");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
    }

   
}
