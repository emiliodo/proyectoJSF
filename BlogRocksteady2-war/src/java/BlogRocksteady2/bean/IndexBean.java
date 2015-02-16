/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Post;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Emilio
 */
@ManagedBean
@RequestScoped
public class IndexBean {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;

    private List<Post> mvpostlist;
    private StreamedContent image;
    private Post postMVP;

    public StreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String idpost = context.getExternalContext().getRequestParameterMap().get("idpost");
            byte[] img = this.postFacade.find(new BigDecimal(idpost)).getHeaderImage();
            return new DefaultStreamedContent(new ByteArrayInputStream(img));
        }
    }

    public Post getPostMVP() {
        return postMVP;
    }

    public void setPostMVP(Post postMVP) {
        this.postMVP = postMVP;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public List<Post> getMvpostlist() {
        return mvpostlist;
    }

    public void setMvpostlist(List<Post> mvpostlist) {
        this.mvpostlist = mvpostlist;
    }

    @PostConstruct
    public void init() {
        this.mvpostlist = this.postFacade.getMVPost();
        if (mvpostlist != null && !mvpostlist.isEmpty()) {
            postMVP = mvpostlist.get(0);
        }
    }
    public IndexBean() {
    }

}
