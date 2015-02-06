/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Emilio
 */
@Entity
@Table(name = "POST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByPostId", query = "SELECT p FROM Post p WHERE p.postId = :postId"),
    @NamedQuery(name = "Post.findByMvpost", query = "SELECT p FROM Post p WHERE p.mvpost = :mvpost"),
    @NamedQuery(name = "Post.findByPostContent", query = "SELECT p FROM Post p WHERE p.postContent = :postContent"),
    @NamedQuery(name = "Post.findByPostDate", query = "SELECT p FROM Post p WHERE p.postDate = :postDate"),
    @NamedQuery(name = "Post.findByPostGps", query = "SELECT p FROM Post p WHERE p.postGps = :postGps"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "POST_ID")
    private BigDecimal postId;
    @Lob
    @Column(name = "HEADER_IMAGE")
    private Byte[] headerImage;
    @Column(name = "MVPOST")
    private Character mvpost;
    @Size(max = 255)
    @Column(name = "POST_CONTENT")
    private String postContent;
    @Column(name = "POST_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    @Size(max = 255)
    @Column(name = "POST_GPS")
    private String postGps;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @JoinColumn(name = "POSTED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private Usuario postedBy;
    @OneToMany(mappedBy = "postCommented")
    private Collection<Comentario> comentarioCollection;

    public Post() {
    }

    public Post(BigDecimal postId) {
        this.postId = postId;
    }

    public BigDecimal getPostId() {
        return postId;
    }

    public void setPostId(BigDecimal postId) {
        this.postId = postId;
    }

    public Byte[] getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(Byte[] headerImage) {
        this.headerImage = headerImage;
    }

    public Character getMvpost() {
        return mvpost;
    }

    public void setMvpost(Character mvpost) {
        this.mvpost = mvpost;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostGps() {
        return postGps;
    }

    public void setPostGps(String postGps) {
        this.postGps = postGps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Usuario getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Usuario postedBy) {
        this.postedBy = postedBy;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlogRocksteady2.entity.Post[ postId=" + postId + " ]";
    }
    
}
