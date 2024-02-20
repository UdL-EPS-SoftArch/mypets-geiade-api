package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Shelter extends UriEntity<Long> {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Length(min=12, max=12)
    private String mobile;

    @NotBlank
    private LocalDateTime createdAt;

    @NotBlank
    private LocalDateTime updatedAt;

    @BooleanFlag
    private boolean isActive;

}
