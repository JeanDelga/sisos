package controller;

import banco.Usuarios;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import com.lambdaworks.crypto.SCryptUtil;
import banco.UsuariosFacadeLocal;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.enterprise.context.RequestScoped;
import util.SessionUser;

@Named(value = "loginController")
@RequestScoped
public class LoginController implements Serializable{
    
    private int tentativas;
    private long ultimoLogin;
    private String usuario;
    private String senha;
    private Usuarios usuarios;

    @EJB
    UsuariosFacadeLocal userEJB;

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

    public LoginController() {
    }

    public String validaLogin() throws ParseException {
        this.usuarios = (Usuarios) userEJB.findLogin(usuario);
        
        GregorianCalendar gcAtual = new GregorianCalendar();
        
        if (usuarios != null && usuarios.getUsuario().equals(usuario) && usuarios.getStatuss().equals("Inativo")){
            if(desbloqueioAut(usuarios.getUltimoLogin().longValue())){
                usuarios.setStatuss("Ativo");
                usuarios.setTentativas(0);
                usuarios.setUltimoLogin(BigInteger.valueOf(gcAtual.getTimeInMillis()));
                userEJB.edit(usuarios);
                tentativas = 0;
            }
        }
        
        if(usuarios != null && usuarios.getUsuario().equals(usuario) && usuarios.getUltimoLogin() != null && usuarios.getTentativas() >= 0){
            ultimoLogin = usuarios.getUltimoLogin().longValue();
            tentativas = usuarios.getTentativas();
        }
        
        if (usuarios != null && usuarios.getUsuario().equals(usuario) && !"".equals(senha) && SCryptUtil.check(senha, usuarios.getSenha()) && usuarios.getStatuss().equals("Ativo")) {
            
            if (tentativas > 0){
                tentativas = 0;
                usuarios.setTentativas(tentativas);
                usuarios.setUltimoLogin(BigInteger.valueOf(gcAtual.getTimeInMillis()));
                userEJB.edit(usuarios);
            } else {
                usuarios.setUltimoLogin(BigInteger.valueOf(gcAtual.getTimeInMillis()));
                userEJB.edit(usuarios);
            }
            
            HttpSession session = SessionUser.getSession();
            session.setAttribute("username", usuario);
           
            return usuarios.getIdgrupo().getGrupo();
            
        } else if(tentativas == 2 && usuarios != null && usuarios.getStatuss().equals("Ativo") && usuarios.getUsuario().equals(usuario)){
            tentativas++;
            usuarios.setTentativas(tentativas);
            usuarios.setStatuss("Inativo");
            usuarios.setUltimoLogin(BigInteger.valueOf(gcAtual.getTimeInMillis()));
            userEJB.edit(usuarios);
            
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Senha Invalida - Tentativa: " + tentativas + "!  Usuario Broqueado. - Aguarde " + (Math.round((((300000 - ((long) gcAtual.getTimeInMillis() - ultimoLogin))/1000)/60)) + 1 ) + " Minuto(s).",
                            "Tentativas de mais."));
            return null;
        } else if(usuarios != null && usuarios.getUsuario().equals(usuario) && tentativas <= 3 && usuarios.getStatuss().equals("Ativo")){
            tentativas++;
            usuarios.setTentativas(tentativas);
            usuarios.setUltimoLogin(BigInteger.valueOf(gcAtual.getTimeInMillis()));
            userEJB.edit(usuarios);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Senha Invalida - Tentativa: " + tentativas + "!  Usuario será bloqueado apos 3 tentativas.",
                            "Usuario será bloqueado apos 3 tentativas."));
            return null;
        } else if(usuarios != null && usuarios.getStatuss().equals("Inativo")){
            
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario Bloqueado! Aguarde " + (Math.round((((300000 - ((long) gcAtual.getTimeInMillis() - ultimoLogin))/1000)/60)) + 1) + " Minuto(s).",
                            "Usuario será bloqueado."));
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario invalido!",
                            "Favor digitar os dados corretamente."));
            return null;
        }
    }

    public String logout() {
        HttpSession session = SessionUser.getSession();
        session.invalidate();
        return "index";
    }
    
    public boolean desbloqueioAut(long ultimoLogin) throws ParseException {
        
        GregorianCalendar gcAtual = new GregorianCalendar();
        
        return ((long) gcAtual.getTimeInMillis() - ultimoLogin) > 300000;
        
        /*
        long diferenca;
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        
        GregorianCalendar gcInicio = new GregorianCalendar();
        GregorianCalendar gcFim = new GregorianCalendar();
        
        gcInicio.setTime(formatter.parse("03/10/2018 18:34:00"));
        gcFim.setTime(formatter.parse("03/10/2018 18:39:00"));
        
        diferenca = (long) (gcFim.getTimeInMillis() - gcInicio.getTimeInMillis());
        System.out.println("Diferença: " + diferenca);
        
        return ((long) gcAtual.getTimeInMillis() - ultimoLogin) > 300000;
        */
    }
    
}
