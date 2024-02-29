package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Table(name = "Role")
@Data
@JsonIdentityReference(alwaysAsId = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends UriEntity<Long> {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    private Set<User> users;

    public Role(Long id) {
        this.id = id;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

