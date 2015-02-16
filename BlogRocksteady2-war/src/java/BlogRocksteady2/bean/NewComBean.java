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
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Emilio
 */
@ManagedBean
@RequestScoped
public class NewComBean {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PostFacade postFacade;

    @EJB
    private ComentarioFacade comentarioFacade;
    private String contenido;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    private Post postComentado;

    public Post getPostComentado() {
        return postComentado;
    }

    public void setPostComentado(Post postComentado) {
        this.postComentado = postComentado;
    }

    public String createCommnt() throws IOException {

        Comentario newComentario = new Comentario();
        newComentario.setCommentContent(contenido);
        newComentario.setPostCommented(postComentado);
        newComentario.setCommentBy(loginBean.getUsuario());
        newComentario.setCommentDate(Calendar.getInstance().getTime());
        comentarioFacade.create(newComentario);
        return "blog.xhtml?faces-redirect=true";
    }

    public NewComBean() {
    }

}
