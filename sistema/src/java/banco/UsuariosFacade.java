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
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "GerenciadorMariMaisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    @Override
    public Object findLogin(String usuario) {
        try {
            Query query = em.createNamedQuery("Usuarios.findByUsuario");
            query.setParameter("usuario", usuario);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    @Override
    public Usuarios findUsuario(String nome) {
        try {
            Query query = em.createNamedQuery("Usuarios.findByNome");
            query.setParameter("nome", nome);
            return (Usuarios) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
