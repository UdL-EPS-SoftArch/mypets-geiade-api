package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Role") // Nombre de la tabla en la base de datos
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends UriEntity<String> {

    @Id
    private String id;

    @NotBlank
    private String name;
}
