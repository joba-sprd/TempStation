package de.ba.tempstation.db.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = {"category_id"})})
public class Category extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "location_category",
            joinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "location_id", nullable = false, updatable = false)}
    )
    private Set<Location> locations;

    public Category() {
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

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    @JsonSetter("locationIds")
    public void setLocationsByIds(int[] locationIds) {
        HashSet<Location> locations = new HashSet<>();
        for (int locationId : locationIds) {
            locations.add(new Location(locationId));
        }
        this.locations = locations;
    }
    //endregion
}
