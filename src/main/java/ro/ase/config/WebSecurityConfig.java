package ro.ase.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT email, password, enabled FROM infouser WHERE email=?")
			.authoritiesByUsernameQuery("SELECT email, authority FROM infouser WHERE email=?")
			.passwordEncoder(new BCryptPasswordEncoder()).rolePrefix("ROLE_");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/", "/welcome", "/setProduct", "/setAdmin", "/buy/**", "/register", "/registered", "/saveUser", "/resources/**", "/login", "/dologin", "/postLogin", "/loginFailed", "/doLogout").permitAll()
			.antMatchers("/managementProduct/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
			.loginProcessingUrl("/dologin").successForwardUrl("/postLogin").failureForwardUrl("/loginFailed")
			.and().logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll()
			.and().csrf().disable();
	}
}
