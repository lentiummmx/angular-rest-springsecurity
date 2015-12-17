package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import java.util.Date;

import javax.annotation.PostConstruct;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.newsentry.NewsEntryDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.user.UserDao;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.Role;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * Initialize the database with some test entries.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
@Component
public class DataBaseInitializer
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseInitializer.class);
	
	@Autowired
	private NewsEntryDao newsEntryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${init-db:false}")
	private String initDatabase;


	protected DataBaseInitializer()
	{
		/* Default constructor for reflection instantiation */
	}


	public DataBaseInitializer(UserDao userDao, NewsEntryDao newsEntryDao, PasswordEncoder passwordEncoder)
	{
		this.userDao = userDao;
		this.newsEntryDao = newsEntryDao;
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void init()
	{
		try {
			LOGGER.info("############## InitDatabase :" + initDatabase + " ########################");
			if (Boolean.parseBoolean(initDatabase)) {
				initDataBase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initDataBase()
	{
		Role userRole = new Role("user");
		Role adminRole = new Role("admin");

		User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
		adminUser.addRole(userRole);
		adminUser.addRole(adminRole);
		adminUser = this.userDao.save(adminUser);
		
		User userUser = new User("user", this.passwordEncoder.encode("user"));
		userUser.addRole(adminUser.getRole(userRole.getName()));
		this.userDao.save(userUser);

		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		for (int i = 0; i < 10; i++) {
			NewsEntry newsEntry = new NewsEntry();
			newsEntry.setContent("This is example content " + i);
			newsEntry.setDate(new Date(timestamp));
			this.newsEntryDao.save(newsEntry);
			timestamp += 1000 * 60 * 60;
		}
	}

}