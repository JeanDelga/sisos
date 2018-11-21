/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "itens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i")
    , @NamedQuery(name = "Itens.findById", query = "SELECT i FROM Itens i WHERE i.id = :id")
    , @NamedQuery(name = "Itens.findByItem", query = "SELECT i FROM Itens i WHERE i.item = :item")})
public class Itens implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "item")
    private String item;
    @ManyToMany(mappedBy = "itensCollection")
    private Collection<Grupos> gruposCollection;

    public Itens() {
    }

    public Itens(Integer id) {
        this.id = id;
    }

    public Itens(Integer id, String item) {
        this.id = id;
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @XmlTransient
    public Collection<Grupos> getGruposCollection() {
        return gruposCollection;
    }

    public void setGruposCollection(Collection<Grupos> gruposCollection) {
        this.gruposCollection = gruposCollection;
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
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.Itens[ id=" + id + " ]";
    }
    
}
