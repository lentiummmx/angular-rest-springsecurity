/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lentiummmx
 *
 */
@Entity
@Table(schema="APP")
public class Role implements BaseEntity {
	
	public static final String ROLE_PREFIX = "ROLE_";
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true, length = 16, nullable = false)
	private String name;
	
	public Role(){}

	/**
	 * @param name
	 */
	public Role(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
