package com.sistema.prueba.pino.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecuityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource datasource;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.and()
		.formLogin()
		.loginPage("/Login").permitAll()
		.defaultSuccessUrl("/inicio").failureUrl("/Login")
		.and()
		.authorizeRequests()
		.antMatchers("/inicio").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
		.and()
		.logout()
		.logoutUrl("/logout")
		.invalidateHttpSession(true)
		.logoutSuccessHandler(logoutSuccessHandler())
		.deleteCookies("JSESSIONID")
		.and()
		.exceptionHandling().authenticationEntryPoint(null);
		;
	}
	
	@Bean
	@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutSuccessHandler();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		        auth.jdbcAuthentication()
		        .dataSource(datasource)
		        .usersByUsernameQuery("SELECT USERNAME, PASSWORD, CASE HABILITADO WHEN 'Y' THEN 1 ELSE 2 END AS HABILITADO\r\n"
		        		+ "		                FROM ACCESO_USUARIOS  WHERE USERNAME=?")
		        .authoritiesByUsernameQuery("SELECT U.USERNAME, "
		        		+ "R.PERMISO FROM ACCESO_USUARIOS U, ROLES_USUARIOS R WHERE U.ROLE_USER = R.ID_PERMISO "
		        		+ "AND U.USERNAME=?");        
    }

}
