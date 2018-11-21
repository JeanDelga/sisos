package filter;

import banco.Usuarios;
import banco.UsuariosFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AutorizacaoLogin", urlPatterns = { "*.xhtml" })
public class Permissoes implements Filter{
    
    @EJB
    UsuariosFacadeLocal usuariosEJB;
    
    public Permissoes() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
                  
            String reqURI = reqt.getRequestURI();
            String pagina = rcs(reqURI, "/GerenciadorMariMais/faces/;.xhtml;", ";");
            
            Usuarios users = new Usuarios();
            
            if(reqURI.contains("/index.xhtml") && (ses != null && ses.getAttribute("username") != null)){
                
                users = (Usuarios) usuariosEJB.findLogin((String) ses.getAttribute("username"));
                resp.sendRedirect(reqt.getContextPath() + "/faces/" + users.getIdgrupo().getGrupo() + ".xhtml");
                
            } else if (reqURI.contains("/index.xhtml") || reqURI.contains("/public/") || reqURI.contains("javax.faces.resource")) {
                chain.doFilter(request, response);
            } else if (ses != null && ses.getAttribute("username") != null) {
                
                
                users = (Usuarios) usuariosEJB.findLogin((String) ses.getAttribute("username"));
                
                long item = users.getIdgrupo().getItensCollection().stream().filter(i -> i.getItem().equals(pagina)).count();
                
                if ((item != 0 || pagina.equals(users.getIdgrupo().getGrupo())) && !reqURI.contains("/index.xhtml")) {
                    chain.doFilter(request, response);
                } else{
                    resp.sendRedirect(reqt.getContextPath() + "/faces/" + users.getIdgrupo().getGrupo() + ".xhtml");
                }
                
            } else{
                    resp.sendRedirect(reqt.getContextPath() + "/faces/index.xhtml");
            }
        } catch (IOException | ServletException e) {
                System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
    
    public static String rcs(String str, String charsRemove, String delimiter) {
         
        if (charsRemove != null && charsRemove.length() > 0 && str != null) {

            String[] remover = charsRemove.split(delimiter);

            for (String remover1 : remover) {
                if (str.contains(remover1)) {
                    str = str.replace(remover1, "");
                }
            }
        }
         
        return str;
    }
}
