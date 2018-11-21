package converters;

import banco.Setor;
import banco.SetorFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "setoresConverter")
@RequestScoped
public class SetoresConverter implements Converter, Serializable{
    @EJB
    SetorFacadeLocal setoresEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()){
            return setoresEJB.findSetor(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Setor setorr = (Setor) value;
            return setorr.getId() != null && setorr.getId() >= 0 ? setorr.getId().toString() : null;
        }
        return null;
    }
}
