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
public interface GruposFacadeLocal {

    void create(Grupos grupos);

    void edit(Grupos grupos);

    void remove(Grupos grupos);

    Grupos find(Object id);
    
    Grupos findGroup(int id);

    List<Grupos> findAll();

    List<Grupos> findRange(int[] range);

    int count();
    
}
