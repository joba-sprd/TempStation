package de.ba.tempstation.db.model;


import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "location_category",
            joinColumns = {@JoinColumn(name = "location_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "location", fetch = FetchType.EAGER)
    private Set<CriticalValue> criticalValues;

    public Location() {
    }

    //region Getter/Setter
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
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
    //endregion
}
