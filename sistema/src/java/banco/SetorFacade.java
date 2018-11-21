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
public class SetorFacade extends AbstractFacade<Setor> implements SetorFacadeLocal {

    @PersistenceContext(unitName = "GerenciadorMariMaisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SetorFacade() {
        super(Setor.class);
    }
    
    @Override
    public Setor findSetor(int id) {
        try {
            Query query = em.createNamedQuery("Setor.findById");
            query.setParameter("id", id);
            return (Setor) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    @Override
    public Setor findSetorNome(String nome) {
        try {
            Query query = em.createNamedQuery("Setor.findByDescricao");
            query.setParameter("descricao", nome);
            return (Setor) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
