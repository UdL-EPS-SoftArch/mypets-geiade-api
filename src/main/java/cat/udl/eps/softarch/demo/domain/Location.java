package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Location {
    @OneToOne
    /*@NotNull*/
    @JsonIdentityReference(alwaysAsId = true)
    private Shelter shelter;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String address;

    private Float latitude;

    private Float longitude;

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    @NotNull
    private Integer postalCode;
}
