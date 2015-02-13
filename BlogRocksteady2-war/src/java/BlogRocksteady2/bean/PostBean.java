package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Post;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.annotation.MultipartConfig;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
@MultipartConfig
public class PostBean {

    @EJB
    private PostFacade postFacade;
    @EJB
    private ComentarioFacade comentarioFacade;

    private Post post;
    private List<Post> postlist;
    private Comentario comentario;
    private Collection<Comentario> comentariolist;
    private StreamedContent image;

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
        this.postlist = this.postFacade.getRecentPost();
    }

    public StreamedContent getImage() {
        
        FacesContext context = FacesContext.getCurrentInstance();
//        context.getCurrentPhaseId()== PhaseId.RENDER_RESPONSE;
        if (context.getCurrentPhaseId()== PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
       }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String idpost = context.getExternalContext().getRequestParameterMap().get("idpost");
            byte [] img = this.postFacade.find(new BigDecimal(idpost)).getHeaderImage();
            return new DefaultStreamedContent(new ByteArrayInputStream(img));
        }
    }
    public void setImage(StreamedContent img) {
        this.image = img;
    }

//    public StreamedContent getImage() {
//        return image;
//    }

    public String doImage() {
        InputStream input = new ByteArrayInputStream(this.postFacade.find(3).getHeaderImage());
        this.setImage(new DefaultStreamedContent(input));
        return "blog.hxtml";
    }

    public PostBean() {

    }

}
