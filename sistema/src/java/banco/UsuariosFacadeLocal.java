/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrador
 */
@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);
    
    Object findLogin(String usuario);
    
    Usuarios findUsuario(String nome);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();
    
}
