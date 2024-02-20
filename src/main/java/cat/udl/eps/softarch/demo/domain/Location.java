package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private Float latitude;

    @NotNull
    @NotBlank
    private Float longitude;

    @NotNull
    @NotBlank
    private String province;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private Integer postalCode;
}
