package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.ejb.PostFacade;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class PostBean {

    @EJB
    private PostFacade postFacade;
    @EJB
    private ComentarioFacade comentarioFacade;

    private Date dateFrom;
    private Date dateTo;
    private Post post;
    private List<Post> postlist;
    private Comentario comentario;
    private Collection<Comentario> comentariolist;
    private StreamedContent image;

    
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

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

    
    public String goProfileView(){
        return "viewProfile.xhtml";
    }
    
    public String GoPostView(){
        return "viewPost.xhtml";
    }

    public PostBean() {

    }
    
    public String filterPost(){
        Date dateFrom2 = dateFrom;
        return "index.xhtml";
    }
    
    public String EliminarPost(BigDecimal postID){
        Post p;
        p= postFacade.find(postID);
        postFacade.remove(p);
        return "blog.xhtml?faces-redirect=true";
    }

}
