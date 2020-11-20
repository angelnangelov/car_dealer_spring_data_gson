package softuni.cardealer.domain.enitities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Set;
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity  {

    private String name;
    private boolean isImporter;
    private Set<Part> parts;
@Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "is_importer")
    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public Supplier() {
    }

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
