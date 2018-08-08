package de.ba.tempstation.db.repository;

import de.ba.tempstation.db.model.MeasuringData;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.List;

@Repository
public class MeasuringDataRepository extends EntityRepository<MeasuringData> {

    @Autowired
    SessionFactory sessionFactory;

    public List<MeasuringData> getMeasuringDataByLocationId(int locationId, int unitId, Calendar from, Calendar to) {
        Session session = null;
        Transaction transaction = null;
        List<MeasuringData> measuringData = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MeasuringData> query = builder.createQuery(MeasuringData.class);
            Root<MeasuringData> root = query.from(MeasuringData.class);
            query.select(root);

            Predicate equalLocationId = builder.equal(root.get("location"), locationId);
            Predicate equalUnitId = builder.equal(root.get("unit"), unitId);
            Predicate dateMeasured = builder.between(root.get("dateMeasured"), from.getTime(), to.getTime());
            Predicate predicate = builder.and(equalLocationId, equalUnitId, dateMeasured);
            query.where(predicate);
            measuringData = session.createQuery(query).list();
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


        return measuringData;
    }

    public List<MeasuringData> getMeasuringDataByMeasuringStationId(int measuringStationId, int unitId, Calendar from, Calendar to) {
        Session session = null;
        Transaction transaction = null;
        List<MeasuringData> measuringData = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MeasuringData> query = builder.createQuery(MeasuringData.class);
            Root<MeasuringData> root = query.from(MeasuringData.class);
            query.select(root);

            Predicate equalMeasuringStationId = builder.equal(root.get("measuringStation"), measuringStationId);
            Predicate equalUnitId = builder.equal(root.get("unit"), unitId);
            Predicate dateMeasured = builder.between(root.get("dateMeasured"), from.getTime(), to.getTime());
            Predicate predicate = builder.and(equalMeasuringStationId, equalUnitId, dateMeasured);
            query.where(predicate);
            measuringData = session.createQuery(query).list();

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

        return measuringData;
    }
}
