package de.ba.tempstation.db.repository;

import org.hibernate.HibernateException;
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
        Session session = null;
        Transaction transaction = null;
        Serializable id = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            id = session.save(entity);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return (Integer) id;
    }

    public List<T> getEntities(int limit, Class<T> entityClass) {
        Session session = null;
        Transaction transaction = null;
        List<T> entities = null;


        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaQuery<T> query = session.getCriteriaBuilder().createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root);
            entities = session.createQuery(query).setMaxResults(limit).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entities;
    }

    public T getEntityById(int id, Class<T> entityClass) {
        Session session = null;
        Transaction transaction = null;
        T entity = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root);
            query.where(builder.equal(root.get("id"), id));
            entity = session.createQuery(query).uniqueResult();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entity;
    }
}
