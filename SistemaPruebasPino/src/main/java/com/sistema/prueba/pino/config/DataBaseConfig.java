package com.sistema.prueba.pino.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sistema.prueba.pino.repository.RepositorioDao;
import com.sistema.prueba.pino.repository.RepositorioImpl;

@Configuration
@PropertySource(
		value = {
				"classpath:/properties/CONEXIONES.properties"
		})
public class DataBaseConfig {

	@Autowired
	private Environment environment;
	
	@Bean(name="dataSource")
	public DataSource dataSource()
	{
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName(environment.getRequiredProperty("DRIVER"));
		data.setUrl(environment.getRequiredProperty("IP"));
		data.setUsername(environment.getRequiredProperty("USUARIO"));
		data.setPassword(environment.getRequiredProperty("PASSWORD"));
		
		return data;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(dataSource());
		
		return jdbc;
	}
	@Bean
	public RepositorioDao servicio() {
		RepositorioImpl servicio=new RepositorioImpl();
		servicio.setJdbcTemplate(jdbcTemplate());
		return servicio;
	}
	
}
