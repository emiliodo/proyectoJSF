/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.entity.Usuario;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Blackproxy
 */
@ManagedBean
@RequestScoped
public class ViewProfileBean {

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
    }
    
}
