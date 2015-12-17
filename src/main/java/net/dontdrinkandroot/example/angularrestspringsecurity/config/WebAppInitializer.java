/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import net.dontdrinkandroot.example.angularrestspringsecurity.config.data.DataAccessConfig;
import net.dontdrinkandroot.example.angularrestspringsecurity.config.security.SecurityConfig;
import net.dontdrinkandroot.example.angularrestspringsecurity.config.util.UtilsConfig;
import net.dontdrinkandroot.example.angularrestspringsecurity.config.web.WebMvcConfig;
import net.dontdrinkandroot.example.angularrestspringsecurity.config.ws.RestConfiguration;

/**
 * @author lentiummmx
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class, DataAccessConfig.class, SecurityConfig.class, UtilsConfig.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/**" };
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletName()
	 *
	@Override
	protected String getServletName() {
		return "RestService";
	}
	 */

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[]{characterEncodingFilter};
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#customizeRegistration(javax.servlet.ServletRegistration.Dynamic)
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("defaultHtmlEscape", "true");
	}

}
