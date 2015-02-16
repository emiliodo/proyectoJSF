/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Post;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Blackproxy
 */
@ManagedBean
@SessionScoped
public class ViewPostBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ComentarioFacade comentarioFacade;
    @EJB
    private PostFacade postFacade;
    
    private Post post;
    private String contenidoComentario;
    private BigDecimal idUserCom;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public BigDecimal getIdUserCom() {
        return idUserCom;
    }

    public void setIdUserCom(BigDecimal idUserCom) {
        this.idUserCom = idUserCom;
    }
    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    public ViewPostBean() {
    }
    
     public String ComentarPost(){
        
         if(contenidoComentario!=null && contenidoComentario.compareTo("")!=0){
            Comentario nuevoComentario = new Comentario();
            nuevoComentario.setCommentBy(loginBean.getUsuario());
            nuevoComentario.setCommentContent(contenidoComentario);
            nuevoComentario.setCommentDate(Calendar.getInstance().getTime());
            nuevoComentario.setPostCommented(post);
            comentarioFacade.create(nuevoComentario); 
            contenidoComentario="";
            this.post=postFacade.find(post.getPostId());
         }
        return "viewPost.xhtml?faces-redirect=true";
        
    }
     
    public String EliminarComentario(Comentario comentarioDelete){
        comentarioFacade.remove(comentarioDelete);
        contenidoComentario="";
        this.post=postFacade.find(post.getPostId());
        return "viewPost.xhtml?faces-redirect=true";
    }
    
    public String cargarPost(Post post){
        this.post = post;
        return "viewPost.xhtml?faces-redirect=true";
    }
}
