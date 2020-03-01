package jt.db;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jt.db.model.EntityBase;

public class DataService {

	public static void sysdate() {
		Query q = DataConn.getEm().createNativeQuery("SELECT sysdate FROM dual");
		System.out.println("DB time: " + q.getSingleResult());
	}

	public static  <S extends EntityBase> Vector<S> getAllEntities(Class<S> entityClass){
		EntityManager em = DataConn.getEm();
		TypedQuery<? extends EntityBase> query = em.createQuery("SELECT o FROM " + entityClass.getSimpleName() + " o ", entityClass);
		Vector<S> ret = new Vector<>();
		ret.addAll((List<S>)query.getResultList());
		return ret;
	}

	public static void remove(EntityBase o) {
		EntityManager em = DataConn.getEm();
		EntityTransaction tr = em.getTransaction();
		try{
			tr.begin();
			o = em.merge(o);
			em.remove(o);
			tr.commit();
		}
		catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

	}

	public static void insert(EntityBase o) {
		EntityManager em = DataConn.getEm();
		EntityTransaction tr = em.getTransaction();
		try{
			tr.begin();
			em.persist(o);
			tr.commit();
		}
		catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

	}
	
	public static void update(EntityBase o) {
		EntityManager em = DataConn.getEm();
		EntityTransaction tr = em.getTransaction();
		try{
			tr.begin();
			em.merge(o);
			tr.commit();
		}
		catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

	}

}