package com.mindhub.homebanking.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization{


    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http.authorizeRequests()

            .antMatchers("/web/index.html","/web/assets/bank.css","/web/assets/img/**").permitAll()
            .antMatchers("/web/login.html","/web/assets/form.css","/web/assets/login.js").permitAll()
            .antMatchers("/web/assets/register.js").permitAll()
            .antMatchers(HttpMethod.POST,"/api/clients").permitAll()

            .antMatchers("/web/register.html").permitAll()

            .antMatchers("/web/accounts.html","/web/assets/**").hasAuthority("CLIENT")
            .antMatchers("/web/account.html","/web/cards.html").hasAuthority("CLIENT")
            .antMatchers("/web/cards.html","/web/create-cards.html").hasAuthority("CLIENT")
            .antMatchers("/api/clients/current", "/api/clients/current/accounts","/api/clients/current/cards").hasAuthority("CLIENT")




            .antMatchers("/rest/**").hasAuthority("ADMIN")
         //   .antMatchers("/api/**").hasAuthority("ADMIN")
            .anyRequest().denyAll();

        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        //hace q no se pueda complicar la solicitud sin el token  pero nostros la desactivamos
        http.csrf().disable();//no solo form

            //permite ingresar paginas de 3ros
        http.headers().frameOptions().disable();


    // si entro sin estar autenticado
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
                                        //limpia el los datos de autenticacion al

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
        //si falla el login ( error intermiedio)
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        //if logout is successful,just  send  a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}