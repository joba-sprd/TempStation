package de.ba.tempstation.db.model;


import javax.persistence.*;

@Entity
@Table(name = "settings", uniqueConstraints = {@UniqueConstraint(columnNames = {"settings_id"})})
public class Settings extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settings_id", nullable = false, unique = true)
    private int id;

    @Column(name = "measure_duration", nullable = false)
    private Integer measureDuration;

    public Settings() {
    }

    //region Getter/Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMeasureDuration() {
        return measureDuration;
    }

    public void setMeasureDuration(Integer measureDuration) {
        this.measureDuration = measureDuration;
    }
    //endregion
}
