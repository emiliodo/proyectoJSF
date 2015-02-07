/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author YSF
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    @EJB
    private UsuarioFacade usuarioFacade;

    private String nick ;
    private String password;
    private Usuario usuario ;
    private UIComponent validar;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String comprobar(){
     usuario = usuarioFacade.getUserByNickname(nick, password);
     if(usuario!=null){
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario.getUserId());
     //    String user = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
         
        return "pruebajsf.xhtml?faces-redirect=true";
        }else{
           FacesMessage message = new FacesMessage("usuario o contraseña invalido");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(validar.getClientId(context), message);
     return null;
    }
    }
    
    public String logout(){
    
          FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
          
          return "index";
    }
}
