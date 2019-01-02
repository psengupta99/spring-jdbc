package myjava.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import myjava.model.Circle;
//hibernate package..this is just a test
@Repository(value = "hibernateDao")
public class HibernateDaoImpl {

	@Autowired
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Circle> getCircle() {

		String sql = " FROM Circle";
		Query query = getSessionFactory().openSession().createQuery(sql);
		return query.getResultList();
	}

	public Circle getCircleByID(int id) {

		String hql = "FROM Circle C WHERE C.id=:ID";
		Query query = getSessionFactory().openSession().createQuery(hql).
				setParameter("ID", id);
		return (Circle)query.getSingleResult();

	}
}
