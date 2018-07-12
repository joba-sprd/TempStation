package de.ba.tempstation.db.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

@Entity
@Table(name = "critical_value")
public class CriticalValue extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "critical_value_id", nullable = false, unique = true)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "min_value", nullable = false)
    private Float minValue;

    @Column(name = "max_value", nullable = false)
    private Float maxValue;

    public CriticalValue() {
    }

    public CriticalValue(int id) {
        this.id = id;
    }

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    @JsonSetter("unitId")
    public void setUnitById(int unitId) {
        this.unit = new Unit(unitId);
    }
    //endregion
}
