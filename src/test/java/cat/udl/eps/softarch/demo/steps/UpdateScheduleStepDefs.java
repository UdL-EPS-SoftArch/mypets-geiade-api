package cat.udl.eps.softarch.demo.steps;


import cat.udl.eps.softarch.demo.domain.Schedule;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ScheduleRepository;
import cat.udl.eps.softarch.demo.steps.AuthenticationStepDefs;
import cat.udl.eps.softarch.demo.steps.StepDefs;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateScheduleStepDefs {

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private ScheduleRepository scheduleRepository;

    /*@Autowired
    private ShelterRepository shelterRepository; falta aprovar PR Shelter Test*/
    public static String newResourceUri;

    @Given("^There is a registered schedule with startTime \"([^\"]*)\" and endTime \"([^\"]*)\"$")
    public void thereIsARegisteredShelterWithNameEmailMobileAndIsActiveTrue(String startTime, String endTime) {
        Schedule schedule = new Schedule();
        ZonedDateTime start = ZonedDateTime.parse(startTime);
        ZonedDateTime end = ZonedDateTime.parse(endTime);
        schedule.setStartTime(start);
        schedule.setEndTime(end);
        scheduleRepository.save(schedule);

        Schedule schedule_find = scheduleRepository.findScheduleByStartTimeAndEndTime(start, end);
        assertThat(schedule_find).isNotNull();
    }

    @When("I update a schedule with startTime \"([^\"]*)\" and endTime \"([^\"]*)\" with new values startTime \"([^\"]*)\" and endTime \"([^\"]*)\"$")
    public void iUpdateAScheduleWithStartTimeAndEndTimeWithNewValuesStartTimeAndEndTime(String existingStartTime, String existingEndTime, String newStartTime, String newEndTime) throws Exception {

        ZonedDateTime existingStart = ZonedDateTime.parse(existingStartTime);
        ZonedDateTime existingEnd = ZonedDateTime.parse(existingEndTime);
        ZonedDateTime newStart = ZonedDateTime.parse(newStartTime);
        ZonedDateTime newEnd = ZonedDateTime.parse(newEndTime);



        Schedule schedule = scheduleRepository.findScheduleByStartTimeAndEndTime(existingStart, existingEnd);
        if (schedule != null) {
            schedule.setStartTime(newStart);
            schedule.setEndTime(newEnd);
        }



        stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/schedules/{id}", (schedule != null) ? schedule.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(schedule))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());


    }
}
