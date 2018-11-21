package controller;

import banco.Os;
import banco.OsFacadeLocal;
import banco.Setor;
import banco.SetorFacadeLocal;
import banco.Usuarios;
import banco.UsuariosFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.SessionUser;

@Named(value = "osController")
@RequestScoped
public class OsController implements Serializable{
    private Integer id;
    private String titulo;
    private Setor setor;
    private String descricao;
    private String status;
    private Date data_solicitacao;
    private Date hora_solicitacao;
    private Date data_inicio_aten;
    private Date hora_inicio_aten;
    private Date data_fim_aten;
    private Date hora_fim_aten;
    private String correcao;
    private Usuarios usuario_soli;
    private Usuarios usuario_aten;
    private List<Os> listarOSs;
    private List<Setor> listarSetores;
    private Os selectedOS;

    @EJB
    OsFacadeLocal osEJB;
    
    @EJB
    SetorFacadeLocal setoresEJB;
    
    @EJB
    UsuariosFacadeLocal usuariosEJB;
    
    @PostConstruct
    public void init() {
        listarOSs = osEJB.findAll();
        listarSetores = setoresEJB.findAll();
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

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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

    public Date getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(Date data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public Date getHora_solicitacao() {
        return hora_solicitacao;
    }

    public void setHora_solicitacao(Date hora_solicitacao) {
        this.hora_solicitacao = hora_solicitacao;
    }

    public Date getData_inicio_aten() {
        return data_inicio_aten;
    }

    public void setData_inicio_aten(Date data_inicio_aten) {
        this.data_inicio_aten = data_inicio_aten;
    }

    public Date getHora_inicio_aten() {
        return hora_inicio_aten;
    }

    public void setHora_inicio_aten(Date hora_inicio_aten) {
        this.hora_inicio_aten = hora_inicio_aten;
    }

    public Date getData_fim_aten() {
        return data_fim_aten;
    }

    public void setData_fim_aten(Date data_fim_aten) {
        this.data_fim_aten = data_fim_aten;
    }

    public Date getHora_fim_aten() {
        return hora_fim_aten;
    }

    public void setHora_fim_aten(Date hora_fim_aten) {
        this.hora_fim_aten = hora_fim_aten;
    }

    public String getCorrecao() {
        return correcao;
    }

    public void setCorrecao(String correcao) {
        this.correcao = correcao;
    }

    public Usuarios getUsuario_soli() {
        return usuario_soli;
    }

    public void setUsuario_soli(Usuarios usuario_soli) {
        this.usuario_soli = usuario_soli;
    }

    public Usuarios getUsuario_aten() {
        return usuario_aten;
    }

    public void setUsuario_aten(Usuarios usuario_aten) {
        this.usuario_aten = usuario_aten;
    }

    public List<Os> getListarOSs() {
        return listarOSs;
    }

    public void setListarOSs(List<Os> listarOSs) {
        this.listarOSs = listarOSs;
    }
    
    public Os getSelectedOS() {
        return selectedOS;
    }

    public void setSelectedOS(Os selectedOS) {
        this.selectedOS = selectedOS;
        setId(selectedOS.getId());
        setTitulo(selectedOS.getTitulo());
        setSetor(selectedOS.getSetor());
        setDescricao(selectedOS.getDescricao());
        setStatus(selectedOS.getStatus());
        setData_solicitacao(selectedOS.getDataSolicitacao());
        setHora_solicitacao(selectedOS.getHoraSolicitacao());
        setData_inicio_aten(selectedOS.getDataInicioAten());
        setHora_inicio_aten(selectedOS.getHoraInicioAten());
        setData_fim_aten(selectedOS.getDataFimAten());
        setHora_fim_aten(selectedOS.getHoraFimAten());
        setCorrecao(selectedOS.getCorrecao());
        setUsuario_soli(selectedOS.getUsuarioSoli());
        setUsuario_aten(selectedOS.getUsuarioAten());   
    }
    
    public List<Setor> getListarSetores() {
        return listarSetores;
    }

    public void setListarSetores(List<Setor> listarSetores) {
        this.listarSetores = listarSetores;
    }
    
    public void cadastrarOs() throws IOException{
        Os cadOs = new Os();
        
        GregorianCalendar gcAtual = new GregorianCalendar();
        Date datahora = gcAtual.getTime();
        
        String userName = SessionUser.getUserName();
        Usuarios usuario = new Usuarios();
        usuario = (Usuarios) usuariosEJB.findLogin(userName);
        
        cadOs.setTitulo(titulo);
        cadOs.setSetor(setor);
        cadOs.setDescricao(descricao);
        cadOs.setStatus("Aguardando Atendimento");
        cadOs.setDataSolicitacao(datahora);
        cadOs.setHoraSolicitacao(datahora);
        cadOs.setUsuarioSoli(usuario);
        
        osEJB.create(cadOs);
        
        String grupo = usuario.getIdgrupo().getGrupo();
        
        if(grupo.equals("atendente") || grupo.equals("admin")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("atender-os.xhtml");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("abrir-os.xhtml");
        }
    }
    
    public String buttonAlterarOs(){
        return "alterar-os";
    }
    
    public String buttonAtenderOs(){
        return "alterar-os-atend";
    }
    
    public String buttonVisualizarOs(){
        return "visualizar-os";
    }
    
    public void buttonVoltar() throws IOException{
        String userName = SessionUser.getUserName();
        Usuarios usuario = new Usuarios();
        usuario = (Usuarios) usuariosEJB.findLogin(userName);
        
        String grupo = usuario.getIdgrupo().getGrupo();
        
        if(grupo.equals("atendente") || grupo.equals("admin")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("atender-os.xhtml");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("abrir-os.xhtml");
        }
    }
    
    public void alterarOs() throws IOException{
        Os alterarOs = new Os();
        
        alterarOs = osEJB.find(id);
        
        alterarOs.setTitulo(titulo);
        alterarOs.setSetor(setor);
        alterarOs.setDescricao(descricao);
     
        osEJB.edit(alterarOs);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("abrir-os.xhtml");
    }
    
    public void atenderOS() throws IOException{
        Os atenderOs = new Os();
        
        atenderOs = osEJB.find(id);
        
        GregorianCalendar gcAtual = new GregorianCalendar();
        Date datahora = gcAtual.getTime();
        
        String userName = SessionUser.getUserName();
        Usuarios usuario = new Usuarios();
        usuario = (Usuarios) usuariosEJB.findLogin(userName);
        
        atenderOs.setStatus("Em Atendimento");
        atenderOs.setDataInicioAten(datahora);
        atenderOs.setHoraInicioAten(datahora);
        atenderOs.setCorrecao(correcao);
        atenderOs.setUsuarioAten(usuario);
     
        osEJB.edit(atenderOs);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("atender-os.xhtml");
    }
    
    public void finalizarOs() throws IOException{
        Os finalizarOs = new Os();
        
        finalizarOs = osEJB.find(id);
        
        GregorianCalendar gcAtual = new GregorianCalendar();
        Date datahora = gcAtual.getTime();
        
        finalizarOs.setStatus("Atendimento Finalizado");
        finalizarOs.setDataFimAten(datahora);
        finalizarOs.setHoraFimAten(datahora);
        
        osEJB.edit(finalizarOs);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("atender-os.xhtml");
    }
}
