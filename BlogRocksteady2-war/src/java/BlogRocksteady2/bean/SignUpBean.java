/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author samia
 */
@ManagedBean
@SessionScoped
public class SignUpBean implements Serializable {
    @EJB
    private UsuarioFacade usuarioFacade;
    private String name;
    private String email;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String description;
    private String twitter;
    private String facebook;
    private String linkedin;             
    private String userName;
    private BigDecimal userId;
    private BigInteger userType;
    private String website;
    private String instagram;
    
    /**
     * Creates a new instance of SignUpBean
     */
    public SignUpBean() {
    }
    
    public String doRegister() {

         int usertype = 3;

        if (password.equals(confirmPassword)) {

            Usuario antiguoUsuario = usuarioFacade.getUsuarioByUserName(userName);
            if (antiguoUsuario == null) {               
                
                Usuario nuevoUsuario = new Usuario(); 
                nuevoUsuario.setUserType( BigInteger.valueOf(usertype));
           
                nuevoUsuario.setUsername(userName);
                nuevoUsuario.setName(name);
                nuevoUsuario.setLastName(lastName);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setPassword(password);
                nuevoUsuario.setDescription(description);
                nuevoUsuario.setTwitter(twitter);
                nuevoUsuario.setFacebook(facebook);
                nuevoUsuario.setInstagram(instagram);
                nuevoUsuario.setLinkedin(linkedin);
                
                usuarioFacade.create(nuevoUsuario);

                return ("login.xhtml");
                
            } else {
                
            return ("index.xhtml");   

            }
        }
        else{
            
            return ("index.xhtml");
        }
    }
        
    
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigInteger getUserType() {
        return userType;
    }

    public void setUserType(BigInteger userType) {
        this.userType = userType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
