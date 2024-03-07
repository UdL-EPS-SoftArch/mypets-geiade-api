package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Shelter extends UriEntity<Long> {

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Pet pet;

    @OneToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private ShelterCertificate shelterCertificate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Pattern(regexp="(^$|[0-9]{11})")
    private String mobile;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    @BooleanFlag
    private boolean isActive;

}
