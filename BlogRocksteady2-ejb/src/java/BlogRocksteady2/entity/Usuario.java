/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlogRocksteady2.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Blackproxy
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUserId", query = "SELECT u FROM Usuario u WHERE u.userId = :userId"),
    @NamedQuery(name = "Usuario.findByUserType", query = "SELECT u FROM Usuario u WHERE u.userType = :userType"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByName", query = "SELECT u FROM Usuario u WHERE u.name = :name"),
    @NamedQuery(name = "Usuario.findByLastName", query = "SELECT u FROM Usuario u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByWebsite", query = "SELECT u FROM Usuario u WHERE u.website = :website"),
    @NamedQuery(name = "Usuario.findByDescription", query = "SELECT u FROM Usuario u WHERE u.description = :description"),
    @NamedQuery(name = "Usuario.findByTwitter", query = "SELECT u FROM Usuario u WHERE u.twitter = :twitter"),
    @NamedQuery(name = "Usuario.findByFacebook", query = "SELECT u FROM Usuario u WHERE u.facebook = :facebook"),
    @NamedQuery(name = "Usuario.findByInstagram", query = "SELECT u FROM Usuario u WHERE u.instagram = :instagram"),
    @NamedQuery(name = "Usuario.findByLinkedin", query = "SELECT u FROM Usuario u WHERE u.linkedin = :linkedin")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "generadorSeqUsuario", sequenceName = "USUARIO_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generadorSeqUsuario")
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_TYPE")
    private BigInteger userType;
    @Size(max = 50)
    @Column(name = "USERNAME")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Lob
    @Column(name = "IMG")
    private byte[] img;
    @Size(max = 40)
    @Column(name = "WEBSITE")
    private String website;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 40)
    @Column(name = "TWITTER")
    private String twitter;
    @Size(max = 40)
    @Column(name = "FACEBOOK")
    private String facebook;
    @Size(max = 40)
    @Column(name = "INSTAGRAM")
    private String instagram;
    @Size(max = 40)
    @Column(name = "LINKEDIN")
    private String linkedin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postedBy")
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentBy")
    private Collection<Comentario> comentarioCollection;

    public Usuario() {
    }

    public Usuario(BigDecimal userId) {
        this.userId = userId;
    }

    public Usuario(BigDecimal userId, BigInteger userType) {
        this.userId = userId;
        this.userType = userType;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
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

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Usuario[ userId=" + userId + " ]";
    }
    
    public String userTypeToText(){
        System.out.print("usertype= " + this.userType + " username= "+ this.username);
        if(this.userType == (BigInteger.valueOf(1))){
            return "Admin";
        }else if(this.userType == (BigInteger.valueOf(2))){
            return "Writer";
        }else{
            return "Registered";
        }
    }
    
}
