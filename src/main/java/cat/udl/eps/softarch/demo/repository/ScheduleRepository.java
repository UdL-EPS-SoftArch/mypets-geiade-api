package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Schedule;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;

@RepositoryRestResource
public interface ScheduleRepository extends CrudRepository<Schedule, Long>, PagingAndSortingRepository<Schedule, Long> {

    Schedule findScheduleByStartTimeAndEndTime(@NotNull ZonedDateTime startTime, @NotNull ZonedDateTime endTime);
}
