/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
public class GruposFacade extends AbstractFacade<Grupos> implements GruposFacadeLocal {

    @PersistenceContext(unitName = "GerenciadorMariMaisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GruposFacade() {
        super(Grupos.class);
    }
    
    @Override
    public Grupos findGroup(int id) {
        try {
            Query query = em.createNamedQuery("Grupos.findById");
            query.setParameter("id", id);
            return (Grupos) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
