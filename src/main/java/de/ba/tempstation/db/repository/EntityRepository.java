package de.ba.tempstation.db.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class EntityRepository<T> {

    @Autowired
    SessionFactory sessionFactory;

    public Integer insertEntity(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = session.save(entity);

        transaction.commit();
        session.close();

        return (Integer) id;
    }

    public List<T> getEntities(int limit, Class<T> entityClass) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        List<T> entities = session.createQuery(query).setMaxResults(limit).list();

        transaction.commit();
        session.close();

        return entities;
    }

    public T getEntityById(int id, Class<T> entityClass) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        query.where(builder.equal(root.get("id"), id));
        T entity = session.createQuery(query).uniqueResult();

        transaction.commit();
        session.close();

        return entity;
    }
}
