package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
public abstract class GenericDAO<E,PK extends java.io.Serializable > implements IGenericDAO<E,PK> {
	

	private static SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.SessionFactory : \n" + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private Class<E> entityClass;

	//constructors
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		//this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}

	//getters and setters
	public Class<E> getEntityClass() {
		return entityClass;
	}


	// CRUD stuff
	@SuppressWarnings("unchecked")

	public PK save(E newInstance) {
		PK result = null;
		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			result = (PK) session.save(newInstance);
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.Save : \n" + ex.getMessage());
		}
		return result;
	}

	public boolean saveBool(E newInstance)
	{
		boolean res = false;
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			session.beginTransaction();
			session.save(newInstance);
			session.getTransaction().commit();
			res=true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		session.close();
		return res;
	}

	public boolean update(E transientObject) {
		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			session.update(transientObject);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.Update : \n" + ex.getMessage());
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	public void saveOrUpdate(E transientObject) {

		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(transientObject);
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.saveOrUpdate : \n" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(E persistentObject) {
		try {
			Session session = this.sessionFactory.openSession();
			session.beginTransaction();
			session.delete(persistentObject);
			session.getTransaction().commit();
			session.close();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.delete : \n" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		List<E> results = null;
		try {
			Session session = this.sessionFactory.openSession();
			Criteria crit = session.createCriteria(getEntityClass());
			results = crit.list();
			session.close();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.findAll : \n" + ex.getMessage());
		}
		return results;

	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(String propertyName1, Object value1) {
		List<E> results = null;
		try {
			Session session = this.sessionFactory.openSession();
			results = session.createQuery("from " + getEntityClass().getName() + " u where u." + propertyName1 + " = :one")
					.setParameter("one", value1)
					.list();
			session.close();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.findAllBy1Properties : \n" + ex.getMessage());
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public E findById(String id) {
		List<E> results = null;
		E result = null;
		try {
			Session session = this.sessionFactory.openSession();
			Criteria crit = session.createCriteria(entityClass);
			crit.add(Restrictions.eq("id", id));
			results = crit.list();
			if (!results.isEmpty()) {
				if (results.size() == 1) {
					result = results.get(0);
				}
			}
			session.close();
		} catch (Exception ex) {
			System.err.println("Erreur Dans GenericDAO.findById : \n" + ex.getMessage());
		}
		return result;
	}

/*	public Object findBy(Class<?> clazz, Serializable id) {
	/*	Object obj =null;
		Session session=sessionFactory.openSession();
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			obj=session.get(clazz, id);
			transaction.commit();
		}catch(Exception e){
			obj=null;
			if(transaction!=null){
				transaction.rollback();
			}
		}
		finally{
			session.close();
		}
		return obj;
		return sessionFactory.getCurrentSession().get(clazz, id);
	}*/

}
