package BlogRocksteady2.bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author samia
 */
@ManagedBean
@SessionScoped
public class InternacionalizacionBean implements Serializable{

    private String localeCode;
    private Locale locale = FacesContext.getCurrentInstance()
            .getViewRoot().getLocale();
    private static final long serialVersionUID = 1L;

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }
    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public InternacionalizacionBean() {
   
    }

    public void cambiarLocale(String idioma) {

        locale = new Locale(idioma);
        FacesContext.getCurrentInstance()
                .getViewRoot().setLocale(locale);
        //return "/index";
    }
}