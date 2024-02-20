package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pet extends UriEntity<String> {

    @Id
    private Long id;

    @NotBlank
    private String name;

    private LocalDate dateOfBirth;

    private boolean isAdopted;

    private String colour;

    private Integer size;

    private String chip;

    private String sex;

    private String race;

    private boolean dangerous;

}
