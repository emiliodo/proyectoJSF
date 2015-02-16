/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author YSF
 */
@ManagedBean
@RequestScoped
public class EditPerfilBean implements Serializable{
    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;
    private String email;
    private String website;
    private String facebook;
    private String twitter;
    private String linkedin;  
    private String description;
    private String instagram;
    private Part foto;
    private StreamedContent image;


    public String getLinkedin() {
        return linkedin;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }

    public StreamedContent getImage() {
        InputStream inputStream = new ByteArrayInputStream((byte[]) this.usuario.getImg());
        this.setImage(new DefaultStreamedContent(inputStream, "image/jpg"));
       

        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }
    


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public EditPerfilBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twiter) {
        this.twitter = twiter;
    }
    
    public  String cargarPerfil(){
        BigDecimal user = (BigDecimal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        
        if (user.compareTo(BigDecimal.ZERO)>=1){
            usuario = usuarioFacade.findById(user);

            setEmail(usuario.getEmail());
            setFacebook(usuario.getFacebook());
            setTwitter(usuario.getTwitter());
            setWebsite(usuario.getWebsite());
            setDescription(usuario.getDescription());
            setLinkedin(usuario.getLinkedin());
            setInstagram(usuario.getInstagram());
            return "editProfile.xhtml";
        }else{
            return "index.xhtml";
        }
    }
    
    public void editarPerfil(ActionEvent event) throws IOException{
        BigDecimal user = (BigDecimal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user.compareTo(BigDecimal.ZERO)>=1){
            usuario = usuarioFacade.findById(user);
            usuario.setEmail(email);
            usuario.setDescription(description);
            usuario.setWebsite(website);
            usuario.setFacebook(facebook);
            usuario.setTwitter(twitter);
            usuario.setLinkedin(linkedin);
            usuario.setInstagram(instagram);
            if (foto != null) {
               InputStream inputStream = foto.getInputStream();
                byte[] imagen = IOUtils.toByteArray(inputStream);
                usuario.setImg(imagen);
            }  
            usuarioFacade.edit(usuario); 
            FacesMessage message = new FacesMessage("Perfil actualizado con éxito");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
     }
    
    public StreamedContent getUserImage() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId()== PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
       }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String iduser = context.getExternalContext().getRequestParameterMap().get("iduser");
            byte [] img = this.usuarioFacade.find(new BigDecimal(iduser)).getImg();
            return new DefaultStreamedContent(new ByteArrayInputStream(img));
        }
    }
}
