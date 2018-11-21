/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findById", query = "SELECT u FROM Usuarios u WHERE u.id = :id")
    , @NamedQuery(name = "Usuarios.findByNome", query = "SELECT u FROM Usuarios u WHERE u.nome = :nome")
    , @NamedQuery(name = "Usuarios.findByEmail", query = "SELECT u FROM Usuarios u WHERE u.email = :email")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findBySenha", query = "SELECT u FROM Usuarios u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuarios.findByStatuss", query = "SELECT u FROM Usuarios u WHERE u.statuss = :statuss")
    , @NamedQuery(name = "Usuarios.findByTentativas", query = "SELECT u FROM Usuarios u WHERE u.tentativas = :tentativas")
    , @NamedQuery(name = "Usuarios.findByUltimoLogin", query = "SELECT u FROM Usuarios u WHERE u.ultimoLogin = :ultimoLogin")})
public class Usuarios implements Serializable {

    @OneToMany(mappedBy = "usuarioSoli")
    private Collection<Os> osCollection;
    @OneToMany(mappedBy = "usuarioAten")
    private Collection<Os> osCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "statuss")
    private String statuss;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tentativas")
    private int tentativas;
    @Column(name = "ultimo_login")
    private BigInteger ultimoLogin;
    @JoinColumn(name = "idgrupo", referencedColumnName = "id")
    @ManyToOne
    private Grupos idgrupo;

    public Usuarios() {
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Usuarios(Integer id, String nome, String email, String usuario, String senha, String statuss, int tentativas) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
        this.statuss = statuss;
        this.tentativas = tentativas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public BigInteger getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(BigInteger ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Grupos getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupos idgrupo) {
        this.idgrupo = idgrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.Usuarios[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Os> getOsCollection() {
        return osCollection;
    }

    public void setOsCollection(Collection<Os> osCollection) {
        this.osCollection = osCollection;
    }

    @XmlTransient
    public Collection<Os> getOsCollection1() {
        return osCollection1;
    }

    public void setOsCollection1(Collection<Os> osCollection1) {
        this.osCollection1 = osCollection1;
    }
    
}
