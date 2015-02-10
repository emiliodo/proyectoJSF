/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.ejb;

import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emilio
 */
@Stateless
public class ComentarioFacade extends AbstractFacade<Comentario> {
    @PersistenceContext(unitName = "BlogRocksteady2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioFacade() {
        super(Comentario.class);
    }
    public List<Comentario> getCommentsByUser(Usuario u) {
        return em.createQuery("SELECT c FROM Comentario c WHERE c.commentBy = :commentedBy")
                .setParameter("commentedBy", u)
                .getResultList();
    }
    
    
}
