package controller;

import banco.Grupos;
import banco.GruposFacadeLocal;
import banco.Usuarios;
import javax.ejb.EJB;
import javax.inject.Named;
import com.lambdaworks.crypto.SCryptUtil;
import banco.UsuariosFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@Named(value = "usuariosController")
@RequestScoped
public class UsuariosController implements Serializable{
    private Integer id;
    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private Grupos idgrupo;
    private String statuss;
    private int tentativas;
    private List<Usuarios> listarUsers;
    private List<Grupos> listarGrupos;

    @EJB
    UsuariosFacadeLocal userEJB;
    
    @EJB
    GruposFacadeLocal groupEJB;
    
    @PostConstruct
    public void init() {
        listarUsers = userEJB.findAll();
        listarGrupos = groupEJB.findAll();
    }
    
    public interface BaseEntity {
        public Long getId();  
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

    public Grupos getIdgrupo() {
        return idgrupo;
    }

    public void setIdgrupo(Grupos idgrupo) {
        this.idgrupo = idgrupo;
    }

    public UsuariosController() {
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

    public List<Usuarios> getListarUsers() {
        return listarUsers;
    }

    public void setListarUsers(List<Usuarios> listarUsers) {
        this.listarUsers = listarUsers;
    }

    public List<Grupos> getListarGrupos() {
        return listarGrupos;
    }

    public void setListarGrupos(List<Grupos> listarGrupos) {
        this.listarGrupos = listarGrupos;
    }
    
    public String updateStatus(int idd){
        Usuarios usuarios = new Usuarios();
        usuarios = (Usuarios) userEJB.findLogin(listarUsers.get(idd-1).getUsuario());
        usuarios.setStatuss("Ativo");
        usuarios.setTentativas(0);
        userEJB.edit(usuarios);
        listarUsers = userEJB.findAll();
        return "usuarios";
    }
    
    public String cadastrar(){
        String password = SCryptUtil.scrypt(senha, 16384, 8, 1);
        
        Usuarios users = new Usuarios();
        users.setNome(nome);
        users.setSenha(password);
        users.setEmail(email);
        users.setUsuario(usuario);
        users.setIdgrupo(idgrupo);
        users.setStatuss("Ativo");
        users.setTentativas(0);
        userEJB.create(users);
        return "admin";
    }
}
