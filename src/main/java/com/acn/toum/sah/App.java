package com.acn.toum.sah;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acn.toum.model.Employee;

/**
 * Hello world!
 *
 */
public class App {
	private App() {
		super();
	}

	public static void main(String[] args) {
		/*
		 * Hibernate configuration
		 */
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();

		/*
		 * Spring configuration
		 */
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");

		session.beginTransaction();
		Employee e = ctx.getBean("employee", Employee.class);
		e.setLastname("Thoumsin");
		e.setFirstname("Fabien");
        session.persist(e);
        session.save(e);
        session.getTransaction().commit();
		session.close();
	}

	public static String jean() {
		return "Salut";
	}
}