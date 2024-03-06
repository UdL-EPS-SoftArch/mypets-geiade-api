package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@Data
@EqualsAndHashCode(callSuper = false)
public class Permission {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @ManyToMany
    private Set<Role> role;

}
