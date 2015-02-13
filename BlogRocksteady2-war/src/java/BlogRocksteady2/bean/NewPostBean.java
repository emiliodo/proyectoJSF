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
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author Blackproxy
 */
@ManagedBean
@RequestScoped
public class NewPostBean {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;

    private String title;
    private Part headerImage;
    private String content;

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
    private String latitude;
    private String longitude;

    /**
     * Creates a new instance of NewPostBean
     */
    public NewPostBean() {
    }

    public String createPost() {
        
        //Controlar que el usuario logueado tiene permisos para crear Post
        Post newPost = new Post();
        newPost.setMvpost(Character.MIN_VALUE);
        newPost.setPostContent(content);

        if (headerImage != null) {
            InputStream is;
            try {
                is = headerImage.getInputStream();
                byte[] img = IOUtils.toByteArray(is);
                newPost.setHeaderImage(img);
            } catch (IOException ex) {
                Logger.getLogger(NewPostBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        newPost.setTitle(title);
        newPost.setPostGps(latitude + "," + longitude);

        //El usuario que tiene la sesion abierta.
        newPost.setPostedBy(usuarioFacade.find(new BigDecimal(BigInteger.ONE)));
        newPost.setPostDate(Calendar.getInstance().getTime());
        postFacade.create(newPost);

        return "blog.xhtml?faces-redirect=true";
    }

}
