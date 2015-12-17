/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.config.ws;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author lentiummmx
 *
 */
@Configuration
@ComponentScan({"net.dontdrinkandroot.example.angularrestspringsecurityws.rest.resources"})
@Order(5)
public class RestConfiguration {

}
