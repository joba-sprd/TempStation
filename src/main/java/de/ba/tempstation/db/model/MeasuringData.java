package de.ba.tempstation.db.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "measuring_data")
public class MeasuringData extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measuring_data_id", nullable = false, unique = true)
    private int id;

    @Column(name = "value", nullable = false)
    private Float value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "measuring_station_id")
    private MeasuringStation measuringStation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "date_measured", nullable = false)
    private Date dateMeasured;

    public MeasuringData() {
    }

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public MeasuringStation getMeasuringStation() {
        return measuringStation;
    }

    public void setMeasuringStation(MeasuringStation measuringStation) {
        this.measuringStation = measuringStation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDateMeasured() {
        return dateMeasured;
    }

    public void setDateMeasured(Date dateMeasured) {
        this.dateMeasured = dateMeasured;
    }

    @JsonSetter("locationId")
    public void setLocationById(int locationId) {
        this.location = new Location(locationId);
    }

    @JsonSetter("measuringStationId")
    public void setMeasuringStationById(int measuringStationId) {
        this.measuringStation = new MeasuringStation(measuringStationId);
    }

    @JsonSetter("unitId")
    public void setUnitById(int unitId) {
        this.unit = new Unit(unitId);
    }
    //endregion
}
