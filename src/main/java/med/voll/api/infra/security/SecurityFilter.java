package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter
{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal
    (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        //Obtener el header
        var authHeader = request.getHeader("authorization");

        if(authHeader != null)
        {

            var token = authHeader.replace("Bearer ", "");

            var nomUsuario = tokenService.getSubject(token);
            if(nomUsuario != null)
            {
                //Token valido
                var usuario = usuarioRepository.findByLogin(nomUsuario);
                var authentication = new UsernamePasswordAuthenticationToken
                        (usuario, null,usuario.getAuthorities());//forzamos inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);
    }
}
