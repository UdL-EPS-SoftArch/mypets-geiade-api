package cat.udl.eps.softarch.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Time;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Schedule {

    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    private Time startTime;

    @NotNull
    private Time endTime;

}
