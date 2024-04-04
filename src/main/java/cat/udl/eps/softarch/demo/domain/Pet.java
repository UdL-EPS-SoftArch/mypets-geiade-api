package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public abstract class Pet extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Adoptions adoptions;
    
    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Shelter shelter;

}
