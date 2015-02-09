/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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

    public String getLinkedin() {
        return linkedin;
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
        Integer user = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        if (user>0){
            usuario = usuarioFacade.findById(user);

            setEmail(usuario.getEmail());
            setFacebook(usuario.getFacebook());
            setTwitter(usuario.getTwitter());
            setWebsite(usuario.getWebsite());
            setDescription(usuario.getDescription());
            setLinkedin(usuario.getLinkedin());
            setInstagram(usuario.getInstagram());
            return null;
        }else{
            return "index.xhtml";
        }
    }
    
     public  String editarPerfil(){
        Integer user = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user>0){
            usuario = usuarioFacade.findById(user);
            usuario.setEmail(email);
            usuario.setDescription(description);
            usuario.setWebsite(website);
            usuario.setFacebook(facebook);
            usuario.setTwitter(twitter);
            usuario.setLinkedin(linkedin);
            usuario.setInstagram(instagram);
            usuarioFacade.edit(usuario);
      
        }
        return "/pruebajsf.xhtml?faces-redirect=true";
     }
}
