package de.ba.tempstation.db.model;


import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "location", uniqueConstraints = {@UniqueConstraint(columnNames = {"location_id"})})
public class Location extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gps")
    private String gps;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Set<CriticalValue> criticalValues;

    public Location() {
    }

    public Location(int id) {
        this.id = id;
    }

    public Set<CriticalValue> getCriticalValues() {
        return criticalValues;
    }

    public void setCriticalValues(Set<CriticalValue> criticalValues) {
        this.criticalValues = criticalValues;
    }

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

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @JsonSetter("criticalValueIds")
    public void setCriticalValuesByIds(int[] criticalValueIds) {
        HashSet<CriticalValue> criticalValues = new HashSet<>();
        for (int criticalValueId : criticalValueIds) {
            criticalValues.add(new CriticalValue(criticalValueId));
        }
        this.criticalValues = criticalValues;
    }
    //endregion
}
