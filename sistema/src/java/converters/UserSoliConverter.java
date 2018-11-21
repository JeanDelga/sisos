package converters;

import banco.Usuarios;
import banco.UsuariosFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "userSoliConverter")
@RequestScoped
public class UserSoliConverter implements Converter, Serializable{
    @EJB
    UsuariosFacadeLocal usuariosEJB;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()){
            return usuariosEJB.findUsuario(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Usuarios usuarioo = (Usuarios) value;
            return usuarioo.getId() != null && usuarioo.getId() >= 0 ? usuarioo.getNome() : null;
        }
        return null;
    }
}
