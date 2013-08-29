/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;



@FacesValidator("DataValidator")
public class DataValidator implements Validator {
   
   
 
	public DataValidator(){
		  
	}
 
    @Override
    public void validate(FacesContext context, UIComponent component,Object value) throws ValidatorException {
        
        if (value == null) {
            FacesMessage msg =
                    new FacesMessage("Los datos ingresados no son válidos",
                    "Ingrese datos correspondientes");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }
        if (((String)value).isEmpty()) {
            FacesMessage msg =
                    new FacesMessage("Los datos ingresados no son válidos",
                    "Ingrese datos correspondientes");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }
        
    }

   
}

