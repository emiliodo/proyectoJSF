/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Blackproxy
 */
@ManagedBean
@RequestScoped
public class ViewProfileBean {
    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Creates a new instance of ViewProfileBean
     */
    public ViewProfileBean() {
    }
    
    @PostConstruct
    public void init(){
        //recoger id usuario
        FacesContext context = FacesContext.getCurrentInstance();
        String iduser = context.getExternalContext().getRequestParameterMap().get("iduser");
        this.usuario = usuarioFacade.find(new BigDecimal(iduser));
    }
    
}
