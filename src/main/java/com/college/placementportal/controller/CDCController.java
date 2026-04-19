// package com.college.placementportal.controller;

// import com.college.placementportal.dto.DashboardResponse;
// import com.college.placementportal.service.CDCService;
// import com.college.placementportal.dto.DashboardResponse;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/cdc")
// public class CDCController {

//     @Autowired
//     private CDCService cdcService;

//     @GetMapping("/dashboard")
//     public DashboardResponse getDashboard() {
//         return cdcService.getDashboardData();
//     }
// }
package com.college.placementportal.controller;

import com.college.placementportal.dto.CDCApplicationDTO;
import com.college.placementportal.dto.DashboardResponse;
import com.college.placementportal.entity.Application;
import com.college.placementportal.service.CDCService;   // ✅ ADD THIS


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cdc")
public class CDCController {

    @Autowired
    private CDCService cdcService;

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        return cdcService.getDashboardData();
    }
    @GetMapping("/applications")
public List<CDCApplicationDTO> getAllApplications() {
    return cdcService.getAllApplications();
}
   @GetMapping("/jobs/{jobId}/applications")
public List<CDCApplicationDTO> getApplicationsByJob(@PathVariable Long jobId) {
    return cdcService.getApplicationsByJob(jobId);
}


   @GetMapping("/application/{id}")
public Application getApplication(@PathVariable Long id) {
    return cdcService.getApplicationById(id);
}
   @GetMapping("/jobs")
public List<JobSummaryDTO> getJobs() {
    return cdcService.getAllJobsWithCounts();
}
}