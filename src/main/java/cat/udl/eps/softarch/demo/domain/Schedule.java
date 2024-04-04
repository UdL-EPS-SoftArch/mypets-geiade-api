package cat.udl.eps.softarch.demo.domain;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Time;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<<<< Temporary merge branch 1
    private int Id;
=========
    private long Id;
>>>>>>>>> Temporary merge branch 2

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @ManyToOne
    //@NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Shelter shelter;
}
