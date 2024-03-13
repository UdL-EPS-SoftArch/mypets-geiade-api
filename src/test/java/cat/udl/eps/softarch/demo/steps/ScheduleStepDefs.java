package cat.udl.eps.softarch.demo.steps;


import cat.udl.eps.softarch.demo.domain.Schedule;
import cat.udl.eps.softarch.demo.repository.ScheduleRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.sql.Time;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ScheduleStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private ScheduleRepository scheduleRepository;

    /*@Autowired
    private ShelterRepository shelterRepository; */
    public static String newResourceUri;

    @When("I create a new schedule with startTime \"([^\"]*)\" and endTime \"([^\"]*)\" for shelter \"([^\"]*)\"$")
    public void iCreateANewScheduledWithStartTimeAndEndTime(String startTime, String endTime) throws Throwable {
        Schedule schedule = new Schedule();
        schedule.setStartTime(Time.valueOf(startTime));
        schedule.setEndTime(Time.valueOf(endTime));

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/schedules")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(schedule))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("There is (\\d+) schedule created$")
    public void thereIsScheduleCreated(int scheduleCreatedNum) {
        Assert.assertEquals(scheduleCreatedNum, scheduleRepository.count());
    }

    @And("I try to retrieve that schedule")
    public void retrieveSchedule() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform (
                get(newResourceUri)
                        .with(AuthenticationStepDefs.authenticate())
                        .accept(MediaType.APPLICATION_JSON));
    }

    @And("No schedule is created")
    public void noScheduleIsCreated() throws Exception {
        Assert.assertEquals(0, scheduleRepository.count());
    }
}