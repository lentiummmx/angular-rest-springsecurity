/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config;

import javax.servlet.annotation.WebServlet;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.dontdrinkandroot.example.angularrestspringsecurity.Application;

/**
 * @author lentiummmx
 *
 */
@Configuration
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableAspectJAutoProxy
@PropertySource(value = { "classpath:application.properties", "classpath:persistence.properties" })
@ComponentScan(basePackageClasses={Application.class}, excludeFilters=@Filter({Controller.class, EnableWebMvc.class}))
//@ComponentScan(basePackageClasses={ApplicationConfig.class, ApplicationDao.class, ApplicationEntity.class, ApplicationRepository.class}, excludeFilters=@Filter({Controller.class, EnableWebMvc.class, WebServlet.class}))
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
}
