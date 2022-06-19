package com.sistema.prueba.pino.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sistema.prueba.pino.entities.UploadFile;




@Configuration
@ComponentScan("com.sistema.prueba.pino")
@EnableTransactionManagement
public class ApplicationContextConfig {

	
	 private Properties getHibernateProperties() {
		 	Properties properties = new Properties();
		 	properties.put("hibernate.show_sql", "true");
		 	properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10Dialect");
		 	return properties;
		 	
	 }
	 @Autowired
	 @Bean(name = "sessionFactory")
	 public SessionFactory getSessionFactory(DataSource dataSource) {
		 LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    	sessionBuilder.addProperties(getHibernateProperties());
	    	sessionBuilder.addAnnotatedClasses(User.class, UploadFile.class, HistoricoCasos.class); // Declarar all clases Entitys para poder hacer las transacciones por Hibernate.
	    	return sessionBuilder.buildSessionFactory();
		 
	 }
	 @Autowired
	 @Bean(name = "transactionManager")
	 public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		 public HibernateTransactionManager getTransactionManager(
					SessionFactory sessionFactory) {
				HibernateTransactionManager transactionManager = new HibernateTransactionManager(
						sessionFactory);

				return transactionManager;
		 
	 }
		 @Bean(name = "multipartResolver")
		    public CommonsMultipartResolver getCommonsMultipartResolver() {
		    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		    	multipartResolver.setMaxUploadSize(20971520); // 20MB
		    	multipartResolver.setMaxInMemorySize(1048576);	// 1MB
		    	return multipartResolver;
		    }
		 @Autowired
		    @Bean(name = "userDao")
		    public UserDAO getUserDao(SessionFactory sessionFactory) {
		    	return new UserDAOImpl(sessionFactory);
		    }
		    
		    @Autowired
		    @Bean(name = "fileUploadDao")
		    public FileUploadDAO getFileDao(SessionFactory sessionFactory) {
		    	return new FileUploadDAOImpl(sessionFactory);
		    }

}
