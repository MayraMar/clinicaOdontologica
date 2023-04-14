
package com.dh.integrador.martinezMayra.security;

import com.dh.integrador.martinezMayra.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UsuarioServiceImpl usuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(UsuarioServiceImpl usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

  /*  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/pacientes")
                .hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable();

    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        http.csrf().disable() // Cross-site request forgery
                .authorizeRequests()
                .antMatchers("/get_all_odontologos.html")
                .hasRole("ADMIN")
                .antMatchers("/get_all_pacientes.html")
                .hasRole("ADMIN")
                .antMatchers("/get_all_turnos.html")
                .hasRole("ADMIN")
                .antMatchers("/post_odontologo.html")
                .hasRole("ADMIN")
                .antMatchers("/post_paciente.html")
                .hasRole("ADMIN")
                .antMatchers("/post_turno.html")
                .hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }


}

