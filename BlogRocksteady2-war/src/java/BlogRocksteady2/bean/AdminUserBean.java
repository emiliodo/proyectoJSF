/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.UsuarioFacade;
import BlogRocksteady2.entity.Usuario;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author inftel13
 */
@ManagedBean
@ViewScoped
public class AdminUserBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AdminUserBean.class.getName());

    @EJB
    private UsuarioFacade usuarioFacade;

    private List<Usuario> userList;
    private String userFilter;

    private boolean searchPerformed;

    public boolean isSearchPerformed() {
        return searchPerformed;
    }

    public void setSearchPerformed(boolean searchPerformed) {
        this.searchPerformed = searchPerformed;
    }

    public List<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
    }

    public String getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(String userFilter) {
        this.userFilter = userFilter;
    }

    /**
     * Creates a new instance of AdminUserBean
     */
    public AdminUserBean() {

        this.searchPerformed = false;

    }

    public String doSearchUsersByNameContaining() {
        if ((this.userFilter.isEmpty()) || this.userFilter.equals("") || this.userFilter.equals("")) {
            this.userList = usuarioFacade.findAll();
        } else {
            this.userList = usuarioFacade.findByNameContaining(this.userFilter);
        }
        this.searchPerformed = true;
        return null;
    }

    public String actionUserEditActionPerformed(ValueChangeEvent e) {
        String comando = e.getNewValue().toString();
        BigInteger userID;

        int i = comando.length();
        while (i > 0 && Character.isDigit(comando.charAt(i - 1))) {
            i--;
        }
        userID = new BigInteger(comando.substring(i));

        comando = comando.replaceAll("\\d*$", "");

        switch (comando) {
            case "Admin":
                LOG.log(Level.INFO, "Setting user as admin. User ID = {0}", userID);
                break;
            case "Writer":
                LOG.log(Level.INFO, "Setting user as writer {0}", userID);
                break;
            case "Registered":
                LOG.log(Level.INFO, "Setting user as registered {0}", userID);
                break;
            default:
                LOG.log(Level.INFO, "Not changing anything for user {0}", userID);
                break;
        }
        this.searchPerformed = false;
        return null;
    }

}
