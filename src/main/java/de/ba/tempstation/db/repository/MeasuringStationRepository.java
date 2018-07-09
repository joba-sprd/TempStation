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
import java.util.List;

@Repository
public class MeasuringStationRepository {

    @Autowired
    SessionFactory sessionFactory;

    public MeasuringStation insertMeasuringStation(MeasuringStation station) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(station);
        transaction.commit();
        session.close();

        return station;
    }

    public List<MeasuringStation> getMeasuringStations(int limit) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaQuery<MeasuringStation> query = session.getCriteriaBuilder().createQuery(MeasuringStation.class);
        Root<MeasuringStation> root = query.from(MeasuringStation.class);
        query.select(root);
        List<MeasuringStation> measuringStations = session.createQuery(query).setMaxResults(limit).list();

        transaction.commit();
        session.close();

        return measuringStations;
    }

    public MeasuringStation getMeasuringStationById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MeasuringStation> query = builder.createQuery(MeasuringStation.class);
        Root<MeasuringStation> root = query.from(MeasuringStation.class);
        query.select(root);
        query.where(builder.equal(root.get("id"), id));
        MeasuringStation measuringStation = session.createQuery(query).uniqueResult();

        transaction.commit();
        session.close();

        return measuringStation;
    }
}
