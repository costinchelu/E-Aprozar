package ro.ase.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ro.ase.controller", "ro.ase.service"})
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
	public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/css/**").addResourceLocations("/WEB-INF/css/").setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
		registry.addResourceHandler("/resources/js/**").addResourceLocations("/WEB-INF/js/").setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());	
		registry.addResourceHandler("/resources/xml/**").addResourceLocations("/WEB-INF/xml/").setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
		registry.addResourceHandler("/resources/images/**").addResourceLocations("/WEB-INF/images/").setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760);
	    multipartResolver.setMaxUploadSizePerFile(1048576);
		return multipartResolver;
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
}
