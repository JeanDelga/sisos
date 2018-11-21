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
public interface ItensFacadeLocal {

    void create(Itens itens);

    void edit(Itens itens);

    void remove(Itens itens);

    Itens find(Object id);

    List<Itens> findAll();

    List<Itens> findRange(int[] range);

    int count();
    
}
