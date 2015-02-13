/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Post;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Emilio
 */
@ManagedBean
@RequestScoped
public class EditPostBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PostFacade postFacade;

    private String title;
    private Part headerImage;
    private String content;
    private Post postE;

    public Post getPostE() {
        return postE;
    }

    public void setPostE(Post postE) {
        this.postE = postE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Part getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(Part headerImage) {
        this.headerImage = headerImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String editPost() {

        Post editPost = new Post();

        if (headerImage != null) {
            InputStream is;
            try {
                is = headerImage.getInputStream();
                byte[] img = IOUtils.toByteArray(is);
                editPost.setHeaderImage(img);
            } catch (IOException ex) {
                Logger.getLogger(NewPostBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        editPost.setTitle(title);
        editPost.setPostContent(content);
        editPost.setPostDate(Calendar.getInstance().getTime());
        postFacade.edit(editPost);
        return "blog.xhtml?faces-redirect=true";
    }

    public String cargarEditar(Post p) {
        this.postE = p;
        return "editPost.xhtml?faces-redirect=true";
    }

    public EditPostBean() {
    }

}
