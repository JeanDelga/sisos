/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "os")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Os.findAll", query = "SELECT o FROM Os o")
    , @NamedQuery(name = "Os.findById", query = "SELECT o FROM Os o WHERE o.id = :id")
    , @NamedQuery(name = "Os.findByTitulo", query = "SELECT o FROM Os o WHERE o.titulo = :titulo")
    , @NamedQuery(name = "Os.findByDescricao", query = "SELECT o FROM Os o WHERE o.descricao = :descricao")
    , @NamedQuery(name = "Os.findByStatus", query = "SELECT o FROM Os o WHERE o.status = :status")
    , @NamedQuery(name = "Os.findByDataSolicitacao", query = "SELECT o FROM Os o WHERE o.dataSolicitacao = :dataSolicitacao")
    , @NamedQuery(name = "Os.findByHoraSolicitacao", query = "SELECT o FROM Os o WHERE o.horaSolicitacao = :horaSolicitacao")
    , @NamedQuery(name = "Os.findByDataInicioAten", query = "SELECT o FROM Os o WHERE o.dataInicioAten = :dataInicioAten")
    , @NamedQuery(name = "Os.findByHoraInicioAten", query = "SELECT o FROM Os o WHERE o.horaInicioAten = :horaInicioAten")
    , @NamedQuery(name = "Os.findByDataFimAten", query = "SELECT o FROM Os o WHERE o.dataFimAten = :dataFimAten")
    , @NamedQuery(name = "Os.findByHoraFimAten", query = "SELECT o FROM Os o WHERE o.horaFimAten = :horaFimAten")
    , @NamedQuery(name = "Os.findByCorrecao", query = "SELECT o FROM Os o WHERE o.correcao = :correcao")})
public class Os implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2600)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_solicitacao")
    @Temporal(TemporalType.DATE)
    private Date dataSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_solicitacao")
    @Temporal(TemporalType.TIME)
    private Date horaSolicitacao;
    @Column(name = "data_inicio_aten")
    @Temporal(TemporalType.DATE)
    private Date dataInicioAten;
    @Column(name = "hora_inicio_aten")
    @Temporal(TemporalType.TIME)
    private Date horaInicioAten;
    @Column(name = "data_fim_aten")
    @Temporal(TemporalType.DATE)
    private Date dataFimAten;
    @Column(name = "hora_fim_aten")
    @Temporal(TemporalType.TIME)
    private Date horaFimAten;
    @Size(max = 2600)
    @Column(name = "correcao")
    private String correcao;
    @JoinColumn(name = "setor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Setor setor;
    @JoinColumn(name = "usuario_soli", referencedColumnName = "id")
    @ManyToOne
    private Usuarios usuarioSoli;
    @JoinColumn(name = "usuario_aten", referencedColumnName = "id")
    @ManyToOne
    private Usuarios usuarioAten;

    public Os() {
    }

    public Os(Integer id) {
        this.id = id;
    }

    public Os(Integer id, String titulo, String descricao, String status, Date dataSolicitacao, Date horaSolicitacao, Date dataInicioAten) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataSolicitacao = dataSolicitacao;
        this.horaSolicitacao = horaSolicitacao;
        this.dataInicioAten = dataInicioAten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(Date horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public Date getDataInicioAten() {
        return dataInicioAten;
    }

    public void setDataInicioAten(Date dataInicioAten) {
        this.dataInicioAten = dataInicioAten;
    }

    public Date getHoraInicioAten() {
        return horaInicioAten;
    }

    public void setHoraInicioAten(Date horaInicioAten) {
        this.horaInicioAten = horaInicioAten;
    }

    public Date getDataFimAten() {
        return dataFimAten;
    }

    public void setDataFimAten(Date dataFimAten) {
        this.dataFimAten = dataFimAten;
    }

    public Date getHoraFimAten() {
        return horaFimAten;
    }

    public void setHoraFimAten(Date horaFimAten) {
        this.horaFimAten = horaFimAten;
    }

    public String getCorrecao() {
        return correcao;
    }

    public void setCorrecao(String correcao) {
        this.correcao = correcao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Usuarios getUsuarioSoli() {
        return usuarioSoli;
    }

    public void setUsuarioSoli(Usuarios usuarioSoli) {
        this.usuarioSoli = usuarioSoli;
    }

    public Usuarios getUsuarioAten() {
        return usuarioAten;
    }

    public void setUsuarioAten(Usuarios usuarioAten) {
        this.usuarioAten = usuarioAten;
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
        if (!(object instanceof Os)) {
            return false;
        }
        Os other = (Os) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banco.Os[ id=" + id + " ]";
    }
    
}
