/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 
 * Set ups the utilities needed inside application, like: sending-mail, fileupload
 * excel files, etc.
 * 
 * @author lentiummmx
 *
 */
@Configuration
public class UtilsConfig {

	@Autowired
	private Environment environment;

	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setDefaultEncoding("UTF-8");
		mailSenderImpl.setHost(environment.getProperty("smtp.host"));
		mailSenderImpl.setPort(environment.getProperty("smtp.port", Integer.class));
		mailSenderImpl.setProtocol(environment.getProperty("smtp.protocol"));
		mailSenderImpl.setUsername(environment.getProperty("smtp.username"));
		mailSenderImpl.setPassword(environment.getProperty("smtp.password"));

		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", environment.getProperty("smtp.auth"));
		// javaMailProps.put("mail.smtp.starttls.enable", environment.getProperty("smtp.starttls.enable"));
		javaMailProps.put("mail.debug", environment.getProperty("smtp.debug"));
		// javaMailProps.put("mail.smtp.socketFactory.class", environment.getProperty("smtp.socketFactory.port"));
		// javaMailProps.put("mail.smtp.socketFactory.port", environment.getProperty("smtp.socketFactory.class"));
		javaMailProps.put("mail.smtps.ssl.checkserveridentity", "false");
		javaMailProps.put("mail.smtps.ssl.trust", "*");
		mailSenderImpl.setJavaMailProperties(javaMailProps);

		return mailSenderImpl;
	}

}
