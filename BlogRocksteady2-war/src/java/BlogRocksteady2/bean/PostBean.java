package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.ComentarioFacade;
import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.entity.Comentario;
import BlogRocksteady2.entity.Post;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

    public Byte[] cargarFotos(BigDecimal postId) {
        
//        BigDecimal postId = new BigDecimal(Integer.parseInt(request.getParameter("postId")));
//        Post post = postFacade.find(postId);
//        byte[] file = post.getHeaderImage();
//        response.setContentType("image/jpg");
//        try (OutputStream out = response.getOutputStream()) {
//            out.write(file);
//            out.flush();
//        }
        
        return null;
    }

    public PostBean() {

    }

}
