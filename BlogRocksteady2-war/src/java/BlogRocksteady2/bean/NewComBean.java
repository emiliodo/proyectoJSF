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
import java.math.BigInteger;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Emilio
 */
@ManagedBean
@RequestScoped
public class NewComBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PostFacade postFacade;

    @EJB
    private ComentarioFacade comentarioFacade;

    private String contenidoComentario;
    private Post postComentado;

    public Post getPostComentado() {
        return postComentado;
    }

    public void setPostComentado(Post postComentado) {
        this.postComentado = postComentado;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public void createCommnt(Post commented) {

        Comentario newComentario = new Comentario();
        newComentario.setCommentContent(contenidoComentario);
        newComentario.setPostCommented(commented);
        newComentario.setCommentBy(usuarioFacade.find(new BigDecimal(BigInteger.ONE)));
        newComentario.setCommentDate(Calendar.getInstance().getTime());
        comentarioFacade.create(newComentario);
        
    }

    public NewComBean() {
    }

}
