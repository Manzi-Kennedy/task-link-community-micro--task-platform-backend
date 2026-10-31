package ac.rw.Tasklink.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.rw.Tasklink.Domain.TaskStatus;
import ac.rw.Tasklink.Repository.HelperRepository;
import ac.rw.Tasklink.Repository.PosterRepository;
import ac.rw.Tasklink.Repository.RatingRepository;
import ac.rw.Tasklink.Repository.TaskRepository;
import ac.rw.Tasklink.Repository.ZoneRepository;

@Service 
public class DashboardService {
    @Autowired 
    private PosterRepository posterRepo;

    @Autowired 
    private HelperRepository helperRepo;

    @Autowired 
    private TaskRepository taskRepo;

    @Autowired 
    private ZoneRepository zoneRepo;

    @Autowired 
    private RatingRepository ratingRepo;

    public Map<String, Long> getSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("posters", posterRepo.count());
        summary.put("helpers", helperRepo.count());
        summary.put("freeZones", (long) zoneRepo.findFreeZones().size());
        summary.put("tasks Today", taskRepo.countByTaskDate(LocalDate.now()));
        summary.put("open", taskRepo.countByStatus(TaskStatus.OPEN));
        summary.put("ratings", ratingRepo.count());

        return summary;
    }
}
