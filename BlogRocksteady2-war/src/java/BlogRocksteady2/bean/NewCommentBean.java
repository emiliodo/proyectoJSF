/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Post;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Blackproxy
 */
@ManagedBean
@RequestScoped
public class NewCommentBean {
    @EJB
    private ComentarioFacade comentarioFacade;
    
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    private Post postComentado;
    private String contenidoComentario;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Post getPostComentado() {
        return postComentado;
    }

    public void setPostComentado(Post postComentado) {
        this.postComentado = postComentado;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenido) {
        this.contenidoComentario = contenido;
    }
    
    
    /**
     * Creates a new instance of NewCommentBean
     */
    public NewCommentBean() {
    }
    
    public String ComentarPost(){
        
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setCommentBy(loginBean.getUsuario());
        nuevoComentario.setCommentContent(contenidoComentario);
        nuevoComentario.setCommentDate(Calendar.getInstance().getTime());
        nuevoComentario.setPostCommented(postComentado);
        comentarioFacade.create(nuevoComentario);
        
        return "viewPost.xhtml?faces-redirect=true";
        
    }
    
}