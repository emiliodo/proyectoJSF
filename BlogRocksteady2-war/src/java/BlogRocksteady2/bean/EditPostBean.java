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
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

/**
 *
 * @author Emilio
 */
@ManagedBean
@SessionScoped
public class EditPostBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private PostFacade postFacade;

    private String title;
    private Part headerImage;
    private String content;
    private Post postE;
    private String latitude;
    private String longitude;

    public Post getPostE() {
        return postE;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String editPost(ActionEvent event) throws IOException {

        postE.setTitle(title);
        postE.setPostContent(content);
        postE.setPostGps(content);
        postE.setPostGps(latitude + "," + longitude);
        postE.setPostDate(Calendar.getInstance().getTime());
        postFacade.edit(postE);
        FacesMessage message = new FacesMessage("Post editado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "blog.xhtml?faces-redirect=true";
    }

    public String cargarEditar(Post p) {
        this.postE = p;
        this.title = postE.getTitle();
        this.content = postE.getPostContent();

        String[] latlong = postE.getPostGps().split(",");
        this.latitude = latlong[0];
        this.longitude = latlong[1];
        return "editPost.xhtml?faces-redirect=true";
    }

    public EditPostBean() {
    }

}
