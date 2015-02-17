/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Post;
import BlogRocksteady2.entity.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
//import static java.util.Objects.isNull;
//import static java.util.Objects.isNull;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author inftel13
 */
@ManagedBean
@ViewScoped
public class AdminPostBean implements Serializable{
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginBean;
    
    private List<Post> postList;
    private String userFilter;
    private boolean setMVP;
    private boolean searchPerformed;
    
    /**
     * Creates a new instance of AdminPostBean
     */
    public AdminPostBean() {
        this.setMVP = false;
        this.searchPerformed = false;
    }

    public boolean isSetMVP() {
        return setMVP;
    }

    public void setSetMVP(boolean setMVP) {
        this.setMVP = setMVP;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
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
    
    public String doSearchPostsByUser(){
        Usuario user;
        List resBusqueda = usuarioFacade.getUserByName(this.userFilter);
        this.setMVP = false;
        if(resBusqueda.isEmpty()){
            this.setPostList(null);
        }else{
            user = (Usuario) resBusqueda.get(0);
            this.setPostList(postFacade.getPostsByUser(user));
        }
        this.searchPerformed = true;
        loginBean.setBusquedaRealizada("posts");
        return null;
    }
    
    public String doListPostsForMVP(){
        this.setPostList(this.postFacade.findAll());
        this.setMVP = true;
        this.searchPerformed = true;
        loginBean.setBusquedaRealizada("posts");
        return null;
    }
    
    public String doDeletePostWithID(BigDecimal postID){
        Post p;
        p= postFacade.find(postID);
        postFacade.remove(p);
        this.searchPerformed = false;
        loginBean.setBusquedaRealizada(null);
        return null;
    }
    
    public String doMakeMVP(BigDecimal postID){
        
        
        Post p;
        List<Post> old_mvp;
        old_mvp = postFacade.getMVPost();
        
        if (!old_mvp.isEmpty()) {
            p = old_mvp.get(0);
            p.setMvpost('N');
            postFacade.edit(p);
        }

        p = postFacade.find(postID);
        if (!isNull(p)) {
            p.setMvpost('Y');
            postFacade.edit(p);
        }
        this.searchPerformed = false;
        loginBean.setBusquedaRealizada(null);
        return null;
    }
       
        

}

