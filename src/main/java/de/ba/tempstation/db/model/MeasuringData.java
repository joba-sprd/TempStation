package de.ba.tempstation.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "measuring_station_id")
    private MeasuringStation measuringStation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "date_measured", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
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

    @JsonIgnore
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @JsonIgnore
    public MeasuringStation getMeasuringStation() {
        return measuringStation;
    }

    public void setMeasuringStation(MeasuringStation measuringStation) {
        this.measuringStation = measuringStation;
    }

    @JsonIgnore
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

    @JsonGetter("locationId")
    public int getLocationId() {
        return this.location.getId();
    }

    @JsonSetter("locationId")
    public void setLocationById(int locationId) {
        this.location = new Location(locationId);
    }

    @JsonGetter("measuringStationId")
    public int getMeasuringStationId() {
        return this.measuringStation.getId();
    }

    @JsonSetter("measuringStationId")
    public void setMeasuringStationById(int measuringStationId) {
        this.measuringStation = new MeasuringStation(measuringStationId);
    }

    @JsonGetter("unitId")
    public int getUnitById() {
        return this.unit.getId();
    }

    @JsonSetter("unitId")
    public void setUnitById(int unitId) {
        this.unit = new Unit(unitId);
    }
    //endregion
}
