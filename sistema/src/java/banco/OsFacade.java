/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class OsFacade extends AbstractFacade<Os> implements OsFacadeLocal {

    @PersistenceContext(unitName = "GerenciadorMariMaisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OsFacade() {
        super(Os.class);
    }
    
}
