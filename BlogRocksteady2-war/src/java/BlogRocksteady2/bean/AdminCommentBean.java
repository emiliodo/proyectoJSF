/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author inftel13
 */
@ManagedBean
@ViewScoped
public class AdminCommentBean implements Serializable{
    @EJB
    private ComentarioFacade comentarioFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    
    private boolean searchPerformed;
    private List<Comentario> commentList;
    private String userFilter;

    public List<Comentario> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comentario> commentList) {
        this.commentList = commentList;
    }

    public String getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }

    public boolean isSearchPerformed() {
        return searchPerformed;
    }

    public void setSearchPerformed(boolean searchPerformed) {
        this.searchPerformed = searchPerformed;
    }
    
    /**
     * Creates a new instance of AdminCommentBean
     */
    public AdminCommentBean() {
        this.searchPerformed = false;
    }
    
    public String doSearchCommentsByUser(){
        Usuario user;
        List resBusqueda = usuarioFacade.getUserByName(this.userFilter);
        if (resBusqueda.isEmpty()) {
            this.setCommentList(null);
        } else {
            user = (Usuario) resBusqueda.get(0);
            this.setCommentList(comentarioFacade.getCommentsByUser(user));
        }
        this.searchPerformed = true;
        return null;
        
    }
    
    public String doDeleteCommentPostWithID(BigDecimal commentID){
        Comentario c = comentarioFacade.find(commentID);
        comentarioFacade.remove(c);
        this.searchPerformed = false;
        return null;
    }
    
}
