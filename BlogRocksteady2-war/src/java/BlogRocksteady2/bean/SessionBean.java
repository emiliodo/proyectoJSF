/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.entity.Usuario;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author inftel13
 */
@ManagedBean
@SessionScoped
public class SessionBean {
    private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    
    @PostConstruct 
    private void init(){
        Usuario u = new Usuario();
        u.setUserType(BigInteger.valueOf(1));
        u.setUsername("admin");
        u.setUserId(BigDecimal.valueOf(1));
        this.user = u;
        
    }
    /**
     * Creates a new instance of sessionBean
     */
    public SessionBean() {
    }
    
}
