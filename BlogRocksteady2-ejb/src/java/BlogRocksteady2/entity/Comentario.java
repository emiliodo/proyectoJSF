/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emilio
 */
@Entity
@Table(name = "COMENTARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByCommentId", query = "SELECT c FROM Comentario c WHERE c.commentId = :commentId"),
    @NamedQuery(name = "Comentario.findByCommentContent", query = "SELECT c FROM Comentario c WHERE c.commentContent = :commentContent"),
    @NamedQuery(name = "Comentario.findByCommentDate", query = "SELECT c FROM Comentario c WHERE c.commentDate = :commentDate"),
    @NamedQuery(name = "Comentario.findByCommentGps", query = "SELECT c FROM Comentario c WHERE c.commentGps = :commentGps")})
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMMENT_ID")
    private BigDecimal commentId;
    @Size(max = 255)
    @Column(name = "COMMENT_CONTENT")
    private String commentContent;
    @Column(name = "COMMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @Size(max = 255)
    @Column(name = "COMMENT_GPS")
    private String commentGps;
    @JoinColumn(name = "POST_COMMENTED", referencedColumnName = "POST_ID")
    @ManyToOne
    private Post postCommented;
    @JoinColumn(name = "COMMENT_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private Usuario commentBy;

    public Comentario() {
    }

    public Comentario(BigDecimal commentId) {
        this.commentId = commentId;
    }

    public BigDecimal getCommentId() {
        return commentId;
    }

    public void setCommentId(BigDecimal commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentGps() {
        return commentGps;
    }

    public void setCommentGps(String commentGps) {
        this.commentGps = commentGps;
    }

    public Post getPostCommented() {
        return postCommented;
    }

    public void setPostCommented(Post postCommented) {
        this.postCommented = postCommented;
    }

    public Usuario getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(Usuario commentBy) {
        this.commentBy = commentBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BlogRocksteady2.entity.Comentario[ commentId=" + commentId + " ]";
    }
    
}
