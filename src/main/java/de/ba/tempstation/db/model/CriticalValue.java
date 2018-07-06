package de.ba.tempstation.db.model;

import javax.persistence.*;

@Entity
@Table(name = "critical_value")
public class CriticalValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "critical_value_id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name="location_id", nullable = false)
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "value", nullable = false)
    private float value;

    public CriticalValue() {
    }

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    //endregion
}
