package de.ba.tempstation.db.model;


import javax.persistence.*;

@Entity
@Table(name = "unit", uniqueConstraints = {@UniqueConstraint(columnNames = {"unit_id"})})
public class Unit extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit_of_measurement", nullable = false)
    private String unitOfMeasurement;

    public Unit() {
    }

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
    //endregion
}
