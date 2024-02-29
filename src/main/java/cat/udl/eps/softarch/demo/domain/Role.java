package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "Role")
@Data
@JsonIdentityReference(alwaysAsId = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends UriEntity<String> {

    @Id
    private String id;

    @NotBlank
    private String name;

    @ManyToMany
    private Set<User> users;

    public Role(String id) {
        this.id = id;
    }

    public Role() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

