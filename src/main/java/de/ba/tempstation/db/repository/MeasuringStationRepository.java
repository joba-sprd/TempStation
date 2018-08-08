package de.ba.tempstation.db.repository;

import de.ba.tempstation.db.model.MeasuringStation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class MeasuringStationRepository extends EntityRepository<MeasuringStation> {

    @Autowired
    SessionFactory sessionFactory;

    public MeasuringStation getMeasuringStationByHardwareId(String hardwareId) {
        Session session = null;
        Transaction transaction = null;
        MeasuringStation measuringStation = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MeasuringStation> query = builder.createQuery(MeasuringStation.class);
            Root<MeasuringStation> root = query.from(MeasuringStation.class);
            query.select(root);
            query.where(builder.equal(root.get("hardwareId"), hardwareId));
            measuringStation = session.createQuery(query).uniqueResult();

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

        return measuringStation;
    }
}
