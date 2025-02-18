package com.projeto.controleContatos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Extrair o token do cabeçalho da requisição
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            //validar o token
            if(jwtTokenUtil.validateToken(token)) {
                //se válido, configurar a autenticação no contexto da aplicação
                String username = jwtTokenUtil.getUsernameFromToken(token);

                //criar uma lista de autenticação
                List<SimpleGrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("USER"));

                Authentication authentication = new
                        UsernamePasswordAuthenticationToken(username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }
        filterChain.doFilter(request, response);
    }
}
