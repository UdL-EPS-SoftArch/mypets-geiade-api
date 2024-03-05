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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String address;

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    @NotNull
    private Integer postalCode;
}
