package com.eventify.zuul;

/**
 * Created by spasoje on 25-Nov-18.
 */
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                // authorization requests config
                .authorizeRequests()
                // allow all who are accessing "auth" service
                .antMatchers(HttpMethod.OPTIONS, jwtConfig.getUri()).permitAll()
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/auth/register").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/scraper/scrap").permitAll()
                .antMatchers(HttpMethod.POST, "/scraper/scrap").permitAll()
                .antMatchers(HttpMethod.GET, "/event/events").permitAll()
                .antMatchers(HttpMethod.POST, "/event/addEvent").permitAll()
                .antMatchers(HttpMethod.GET, "/event/addEvent").permitAll()
                // must be an admin if trying to access admin area (authentication is also required here)
                .antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
                // Any other request must be authenticated
                .anyRequest().authenticated();
    }

    //TODO This is to skip security for this path https://stackoverflow.com/questions/30366405/how-to-disable-spring-security-for-particular-url
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/event/**");
        web.ignoring().antMatchers("/scrap/**");
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
