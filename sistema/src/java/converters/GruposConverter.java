package converters;

import banco.Grupos;
import banco.GruposFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "gruposConverter")
@RequestScoped
public class GruposConverter implements Converter, Serializable{
    
    @EJB
    GruposFacadeLocal groupEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()){
            return groupEJB.findGroup(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Grupos groupp = (Grupos) value;
            return groupp.getId() != null && groupp.getId() >= 0 ? groupp.getId().toString() : null;
        }
        return null;
    }
}