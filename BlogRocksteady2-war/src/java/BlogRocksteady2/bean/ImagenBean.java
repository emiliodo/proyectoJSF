/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.ejb.UsuarioFacade;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Blackproxy
 */
@ManagedBean
@RequestScoped
public class ImagenBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PostFacade postFacade;
    
    private StreamedContent postImage;
    private StreamedContent userImage;

    /**
     * Creates a new instance of ImagenBean
     */
    public ImagenBean() {
    }
    
    
    public StreamedContent getPostImage() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId()== PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
       }
        else {
            // So, browser is requesting the image. Get ID value from actual request param.
            String idpost = context.getExternalContext().getRequestParameterMap().get("idpost");
            byte [] img = this.postFacade.find(new BigDecimal(idpost)).getHeaderImage();
            return new DefaultStreamedContent(new ByteArrayInputStream(img));
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
