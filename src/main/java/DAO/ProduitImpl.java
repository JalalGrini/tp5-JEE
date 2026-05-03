package DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class ProduitImpl implements ProduitDAO {

	@Override
	public void addProduit(Produit p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteProduit(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Produit p = session.get(Produit.class, id);
			if (p != null) {
				session.delete(p);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Produit getProduitById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return session.get(Produit.class, id);
		} finally {
			session.close();
		}
	}

	@Override
	public List<Produit> getAllProduits() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			return session.createQuery("from Produit", Produit.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public void updateProduit(Produit p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(p);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
