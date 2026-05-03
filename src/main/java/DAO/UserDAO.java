package DAO;

import org.hibernate.Session;
import model.User;
import util.HibernateUtil;

public class UserDAO {

	public UserDAO() {
		initializeDefaultUsers();
	}

	private void initializeDefaultUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			long count = (long) session.createQuery("select count(*) from User").getSingleResult();
			if (count == 0) {
				session.beginTransaction();
				session.save(new User("jalal", "1234", "admin"));
				session.save(new User("test", "1234", "user"));
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public User findByLoginAndPassword(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return session.createQuery(
					"from User where login = :login and password = :password",
					User.class)
					.setParameter("login", login)
					.setParameter("password", password)
					.uniqueResult();
		} finally {
			session.close();
		}
	}
}
