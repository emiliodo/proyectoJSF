/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author YSF
 */
@ManagedBean
@SessionScoped
public class EditPerfilBean {
    @EJB
    private UsuarioFacade usuarioFacade;

    
    private String email;
    private String facebook;
    private String twiter;
    private Usuario usuario;
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

    public String getTwiter() {
        return twiter;
    }

    public void setTwiter(String twiter) {
        this.twiter = twiter;
    }
    
    public  String cargarPerfil(){
    Integer user = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        
    if (user>0){
             usuario = usuarioFacade.findById(user);
             
             setEmail(usuario.getEmail());
             setFacebook(usuario.getFacebook());
             setTwiter(usuario.getTwitter());
             return null;
         }else{
    return "index.xhtml";
    }
    }
    
     public  String editarPerfil(){
        
      usuario.setEmail(email);
      
     usuario.setFacebook(facebook);
   
     usuario.setTwitter(twiter);
     
     usuarioFacade.edit(usuario);
         return "pruebajsf.xhtml";
     }
}
