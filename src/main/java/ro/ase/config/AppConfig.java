package ro.ase.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("postgresql.driver"));
		dataSource.setUrl(environment.getProperty("postgresql.jdbcUrl"));
		dataSource.setUsername(environment.getProperty("postgresql.user"));
		dataSource.setPassword(environment.getProperty("postgresql.password"));
		return dataSource;
	}
}
