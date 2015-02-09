package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Post;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class PostBean {

    private static final Logger LOG = Logger.getLogger(PostBean.class.getName());

    @EJB
    private PostFacade postFacade;
    @EJB
    private ComentarioFacade comentarioFacade;

    private Post post;
    private List<Post> postlist;
    private Comentario comentario;
    private Collection<Comentario> comentariolist;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getPostlist() {
        return postlist;
    }

    public void setPostlist(List<Post> postlist) {
        this.postlist = postlist;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Collection<Comentario> getComentariolist() {
        return comentariolist;
    }

    public void setComentariolist(Collection<Comentario> comentariolist) {
        this.comentariolist = comentariolist;
    }

    @PostConstruct
    public void init() {
        this.postlist = this.postFacade.findAll();
//        if ( this.postlist != null)
//            LOG.info("Postlist = " + this.postlist.get(0).getPostedBy().getUsername() + " - " +
//                     this.postlist.get(0).getPostedBy().toString());

    }

    public Collection<Comentario> cargarComentarios(BigDecimal postId) {
        return this.postFacade.find(postId).getComentarioCollection();

    }

    public StreamedContent cargarFoto(BigDecimal postId) {
        

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getRenderResponse()) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
//            String id = context.getExternalContext().getRequestParameterMap().get("id");
            byte [] image = this.postFacade.find(postId).getHeaderImage();
            return new DefaultStreamedContent(new ByteArrayInputStream(image));
        }
    }
    

    public PostBean() {

    }

}
