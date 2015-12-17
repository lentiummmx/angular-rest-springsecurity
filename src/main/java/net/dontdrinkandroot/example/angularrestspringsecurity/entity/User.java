package net.dontdrinkandroot.example.angularrestspringsecurity.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="\"User\"", schema="APP")
public class User implements BaseEntity, UserDetails
{

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, length = 16, nullable = false)
	private String name;

	@Column(length = 80, nullable = false)
	private String password;

	//@ElementCollection(fetch = FetchType.EAGER)
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_role", schema="APP",
			joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "ID") },
			inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "ID") })
	private Set<Role> roles = new HashSet<Role>();


	protected User()
	{
		/* Reflection instantiation */
	}


	public User(String name, String passwordHash)
	{
		this.name = name;
		this.password = passwordHash;
	}


	public Long getId()
	{
		return this.id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public String getName()
	{
		return this.name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public Set<Role> getRoles()
	{
		return this.roles;
	}


	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}


	public void addRole(Role role)
	{
		this.roles.add(role);
	}


	public Role getRole(String roleName)
	{
		Role searchRole = null;
		for (Role role : roles) {
			if(role.getName().equals(roleName)) {
				searchRole = role;
			}
		}
		
		if(searchRole != null) {
			return searchRole;
		} else {
			searchRole = new Role(roleName);
			return searchRole;
		}
	}


	@Override
	public String getPassword()
	{
		return this.password;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		Set<Role> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}


	@Override
	public String getUsername()
	{
		return this.name;
	}


	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}


	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}


	@Override
	public boolean isEnabled()
	{
		return true;
	}

}