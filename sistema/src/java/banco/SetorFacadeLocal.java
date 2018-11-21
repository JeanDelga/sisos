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
public interface SetorFacadeLocal {

    void create(Setor setor);

    void edit(Setor setor);

    void remove(Setor setor);

    Setor find(Object id);
    
    Setor findSetor(int id);
    
    Setor findSetorNome(String nome);

    List<Setor> findAll();

    List<Setor> findRange(int[] range);

    int count();
    
}
