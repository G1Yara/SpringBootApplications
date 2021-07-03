package com.jwt.config;


import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@PropertySource("classpath:application.properties")
@Configuration
@EnableTransactionManagement
/*@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager",
		basePackages = {
		"com.jwt.repositories"})*/
public class DBConfig {

	@Autowired
	Environment environment;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
		dataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		dataSource.setCatalog(environment.getProperty("spring.datasource"));
		/**
		 * HikariCP specific properties. Remove if you move to other connection pooling
		 * library.
		 **/
		dataSource.addDataSourceProperty("cachePrepStmts", true);
		dataSource.addDataSourceProperty("prepStmtCacheSize", 25000);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 20048);
		dataSource.addDataSourceProperty("useServerPrepStmts", true);
		dataSource.addDataSourceProperty("initializationFailFast", true);
		dataSource.setPoolName("JWT_TOKEN_POOL");

		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("token");
		em.setPackagesToScan("com.jwt");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put(AvailableSettings.HBM2DDL_AUTO, environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put(AvailableSettings.DIALECT, environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put(AvailableSettings.JDBC_TIME_ZONE, "UTC");
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
