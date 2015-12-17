/**
 * 
 */
package net.dontdrinkandroot.example.angularrestspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;

/**
 * @author lentiummmx
 *
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
	
	User findById(Long id);

}
