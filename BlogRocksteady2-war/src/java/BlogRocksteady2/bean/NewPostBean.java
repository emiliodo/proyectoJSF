/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.bean;

import BlogRocksteady2.ejb.PostFacade;
import BlogRocksteady2.entity.Post;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Blackproxy
 */
@ManagedBean
@RequestScoped
public class NewPostBean {
    @EJB
    private PostFacade postFacade;

    public Post getPost() {
        return post;
    }

    /**
     * Creates a new instance of NewPostBean
     */
    public void setPost(Post post) {
        this.post = post;
    }

    
    
    private Post post;
    
    public NewPostBean() {
    }
    
    public void createPost(){
        
        this.post.setMvpost('n');
        this.postFacade.create(post);
    }
    
}
