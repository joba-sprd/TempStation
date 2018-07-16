package de.ba.tempstation.db.repository;

import de.ba.tempstation.db.model.MeasuringStation;
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MeasuringStation> query = builder.createQuery(MeasuringStation.class);
        Root<MeasuringStation> root = query.from(MeasuringStation.class);
        query.select(root);
        query.where(builder.equal(root.get("hardwareId"), hardwareId));
        MeasuringStation measuringStation = session.createQuery(query).uniqueResult();

        transaction.commit();
        session.close();

        return measuringStation;
    }
}
