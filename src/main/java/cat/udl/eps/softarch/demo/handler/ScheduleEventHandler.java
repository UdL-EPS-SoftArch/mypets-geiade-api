package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.domain.Schedule;
import cat.udl.eps.softarch.demo.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;



@Component
@RepositoryEventHandler
@Validated
public class ScheduleEventHandler {

    private final Logger logger = LoggerFactory.getLogger(Schedule.class);

    private final ScheduleRepository scheduleRepository;

    public ScheduleEventHandler(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleScheduleValidation(Schedule schedule) {
        if (schedule.getStartTime().isAfter(schedule.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        logger.info("Schedule is valid: {}", schedule);
        scheduleRepository.save(schedule);
    }
}
