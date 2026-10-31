package ac.rw.Tasklink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.rw.Tasklink.Service.DashboardService;

@RestController 
@RequestMapping (value = "/api/dashboard")
public class DashboardController {
    
    @Autowired 
    private DashboardService dashboardServe;

    //bonus 1
    @GetMapping (value = "/summary")
    public ResponseEntity<?> getSummary(){
        return new ResponseEntity<>(dashboardServe.getSummary(), HttpStatus.OK);
    }

}
