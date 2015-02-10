/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.ejb;

import BlogRocksteady2.entity.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Emilio
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "BlogRocksteady2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public List getUserByName(String s) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.username LIKE :userName")
                .setParameter("userName", s)
                .getResultList();
    }

    public List findByNameContaining(String s) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.username LIKE :userName")
                .setParameter("userName", "%" + s + "%")
                .getResultList();
    }

    public List findByNameBegining(String s) {
        return em.createQuery("SELECT u FROM Usuario u WHERE u.username LIKE :userName")
                .setParameter("userName", s + "%")
                .getResultList();
    }

    public Usuario getUserByNickname(String nick, String password) {

        List<Usuario> usersList = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :nick and u.password = :password")
                .setParameter("nick", nick)
                .setParameter("password", password)
                .getResultList();

        if (usersList != null && usersList.size() > 0) {
            return usersList.get(0);
        } else {
            return null;
        }

    }
    public Usuario getUsuarioByUserName(String userName){
        List <Usuario> listaUsuarios;
        Usuario usuario = null;
        listaUsuarios = em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username")
                .setParameter("username", userName)
                .getResultList();
        if(!listaUsuarios.isEmpty()){
            usuario = listaUsuarios.get(0);
        }
        
        return usuario;
    }
    
    public void makeAdmin(BigDecimal userID){
        Usuario u = em.find(Usuario.class, userID);
        em.getTransaction().begin();
        u.setUserType(BigInteger.valueOf(1));
        em.getTransaction().commit();
    }
    
    public void makeRegistered(BigDecimal userID){
        Usuario u = em.find(Usuario.class, userID);
        em.getTransaction().begin();
        u.setUserType(BigInteger.valueOf(3));
        em.getTransaction().commit();
    }
    
    public void makeWriter(BigDecimal userID){
        Usuario u = em.find(Usuario.class, userID);
        em.getTransaction().begin();
        u.setUserType(BigInteger.valueOf(2));
        em.getTransaction().commit();
    }
    
    public Usuario findById(Integer id){
        
         List<Usuario> usersList = em.createQuery("SELECT u FROM Usuario u WHERE u.userId = :id")
                .setParameter("id", id)
                .getResultList();
    if (usersList != null && usersList.size() > 0) {
            return usersList.get(0);
        } else {
            return null;
        }
    }
}
