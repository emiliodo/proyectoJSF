/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.ejb;

import BlogRocksteady2.entity.Post;
import BlogRocksteady2.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emilio
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "BlogRocksteady2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
    public List<Post> getPostsByUser(Usuario u){
        return em.createQuery("SELECT p FROM Post p WHERE p.postedBy = :postedBy")
                .setParameter("postedBy", u)
                .getResultList();
    }
    
    public List <Post> getRecentPost(){
        return em.createQuery("SELECT p FROM Post p ORDER BY p.postDate DESC")
                .setMaxResults(10)
                .getResultList();
    }
    
    public List <Post> getFilteredPost(Date dateFrom, Date dateTo){
        return em.createQuery("SELECT p FROM Post p WHERE p.postDate BETWEEN :dateFrom AND :dateTo ORDER BY p.postDate DESC")
                .setParameter("dateFrom",new java.sql.Date(dateFrom.getTime()))
                .setParameter("dateTo", new java.sql.Date(dateTo.getTime()))
                .setMaxResults(10)
                .getResultList();
    }
    
    public List <Post> getMVPost(){
        return em.createQuery("SELECT p FROM Post p WHERE p.mvpost = :one")
                .setParameter("one", 'Y')
                .getResultList();
    }
    
    public List <String> getAllGPS(){
        return em.createQuery("SELECT p.postGps FROM Post p")
                .getResultList();
    }
    
}
