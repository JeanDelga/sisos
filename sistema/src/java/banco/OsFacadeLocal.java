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
public interface OsFacadeLocal {

    void create(Os os);

    void edit(Os os);

    void remove(Os os);

    Os find(Object id);

    List<Os> findAll();

    List<Os> findRange(int[] range);

    int count();
    
}
