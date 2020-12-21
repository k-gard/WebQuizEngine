package com.gardikiotis.WebQuizEngine.security;

import com.gardikiotis.WebQuizEngine.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()
                .and().authorizeRequests().antMatchers("/h2/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/").hasRole("ADMIN")
              //  .and().authorizeRequests().antMatchers("/api/users/{id}").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/api/quizzes").authenticated()
                .and().authorizeRequests().antMatchers("/api/quizzes/{id}").authenticated()
                .and().authorizeRequests().antMatchers("/api/quizzes/{id}/solve").authenticated()
                //.and().authorizeRequests().antMatchers("/api/register").hasAnyRole("ADMIN","USER")
                .and().authorizeRequests().antMatchers("/api/users/{id}").authenticated()
                .and().authorizeRequests().antMatchers("/api/users/{email}").authenticated()
                .and().authorizeRequests().antMatchers("/api/users").authenticated()
                .and().authorizeRequests().antMatchers("/api/quizSet").authenticated()
                .and().authorizeRequests().antMatchers("/api/quizSet/{id}/solve").authenticated()
                .and().authorizeRequests().antMatchers("/api/login").authenticated()
                .and().authorizeRequests().antMatchers("/api/allQuizSets").authenticated()
                .and().authorizeRequests().antMatchers( "/api/myQuizzes?page={pageNo}").authenticated()
                .and().authorizeRequests().antMatchers("/actuator/shutdown").permitAll()
                .and().authorizeRequests().antMatchers("api/quizzes?page={pageNo}").authenticated()

                .anyRequest().permitAll().and().cors().and()
                //.headers().frameOptions().sameOrigin().and()//don't apply CSRF protection to /h2-console
                .httpBasic();

    }

/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN");
                //.and()
                //.withUser("test@gmail.com").password("secret").roles("USER");
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }


}