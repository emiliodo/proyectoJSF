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

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public void createCommnt() {

        Comentario newComentario = new Comentario();
        newComentario.setCommentContent(contenidoComentario);
        //newComentario.setPostCommented(postFacade.find(this));
 
        newComentario.setCommentBy(usuarioFacade.find(new BigDecimal(BigInteger.ONE)));
        newComentario.setCommentDate(Calendar.getInstance().getTime());
        
        comentarioFacade.create(newComentario);
        
    }

    public NewComBean() {
    }

}
