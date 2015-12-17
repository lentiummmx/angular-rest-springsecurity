/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Added just for monitoring and debugging.
 * 
 * @author lentiummmx
 *
 */
@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		LOGGER.debug(" *** ContextListener.sessionCreated - NOT GOOD! *** ");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOGGER.debug(" *** ContextListener.sessionDestroyed - NOT GOOD! *** ");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.debug(" *** ContextListener.contextInitialized *** ");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.debug(" *** ContextListener.contextDestroyed *** ");
	}

}
