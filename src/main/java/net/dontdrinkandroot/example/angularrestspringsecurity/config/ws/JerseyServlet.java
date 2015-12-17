/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config.ws;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;

/**
 * @author lentiummmx
 *
 */
@WebServlet(urlPatterns = {"/rest/*"}, initParams = {
	    @WebInitParam(name = "com.sun.jersey.config.property.packages",
	    		value = "net.dontdrinkandroot.example.angularrestspringsecurityws.rest"),
	    @WebInitParam(name="com.sun.jersey.api.json.POJOMappingFeature", value="true"),
	    @WebInitParam(name = "contextConfigLocation", value = ""),
	    @WebInitParam(name = "contextConfig", value = "NOTNULL")},
		name = "jersey-servlet")
public class JerseyServlet extends SpringServlet {

}
