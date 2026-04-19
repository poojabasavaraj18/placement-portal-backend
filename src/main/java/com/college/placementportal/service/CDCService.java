package com.college.placementportal.service;

import com.college.placementportal.controller.JobSummaryDTO;
import com.college.placementportal.dto.CDCApplicationDTO;
import com.college.placementportal.dto.DashboardResponse;
import com.college.placementportal.dto.JobStatsDTO;
import com.college.placementportal.entity.Application;
import com.college.placementportal.entity.ApplicationStatus;
import com.college.placementportal.entity.JobPost;
import com.college.placementportal.repository.ApplicationRepository;
import com.college.placementportal.repository.JobPostRepository;
import com.college.placementportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CDCService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public DashboardResponse getDashboardData() {

        DashboardResponse response = new DashboardResponse();

        // ✅ Total counts
        long totalStudents = studentRepository.count();
        long totalJobs = jobPostRepository.count();
        long placedStudents = applicationRepository.countByStatus(ApplicationStatus.SELECTED);

        double placementPercentage = totalStudents == 0 ? 0 :
                (placedStudents * 100.0) / totalStudents;

        response.setTotalStudents(totalStudents);
        response.setTotalJobs(totalJobs);
        response.setPlacedStudents(placedStudents);
        response.setPlacementPercentage(placementPercentage);

        // 🔥 Job Stats
        List<JobPost> jobs = jobPostRepository.findAll();
        List<Application> applications = applicationRepository.findAll();

        Map<Long, JobStatsDTO> statsMap = new HashMap<>();

        // Step 1: empty stats
        for (JobPost job : jobs) {
            JobStatsDTO dto = new JobStatsDTO();
            dto.setJobId(job.getId());
            dto.setJobTitle(job.getTitle());
            statsMap.put(job.getId(), dto);
        }

        // Step 2: count
        for (Application app : applications) {

            JobStatsDTO dto = statsMap.get(app.getJobPost().getId());
            if (dto == null) continue;

            switch (app.getStatus()) {
                case APPLIED -> dto.setApplied(dto.getApplied() + 1);
                case ROUND1 -> dto.setRound1(dto.getRound1() + 1);
                case ROUND2 -> dto.setRound2(dto.getRound2() + 1);
                case SELECTED -> dto.setSelected(dto.getSelected() + 1);
                case REJECTED -> dto.setRejected(dto.getRejected() + 1);
                default -> throw new IllegalArgumentException("Unexpected value: " + app.getStatus());
            }
        }

        response.setJobStats(new ArrayList<>(statsMap.values()));

        // 🔥 Recent activity
        List<String> activities = applications.stream()
                .limit(10)
                .map(app -> app.getName() + " applied for " +
                        app.getJobPost().getTitle())
                .toList();

        response.setRecentActivities(activities);

        return response;
    }
    public List<CDCApplicationDTO> getAllApplications() {

    List<Application> applications = applicationRepository.findAll();
    List<CDCApplicationDTO> list = new ArrayList<>();

    for (Application app : applications) {

        CDCApplicationDTO dto = new CDCApplicationDTO();
        dto.setApplicationId(app.getId());
        dto.setStudentName(app.getName());
        dto.setJobTitle(app.getJobPost().getTitle());
        dto.setStatus(app.getStatus().name());
        dto.setResumePath(app.getResumePath());
        dto.setCgpa(app.getCgpa());
        dto.setSkills(app.getSkills());
        dto.setApplicationId(app.getId());  // 🔥 VERY IMPORTANT
        dto.setCompanyName(app.getJobPost().getCompanyName());
dto.setSalary(app.getJobPost().getSalary());

        list.add(dto);
    }

    return list;
}
    public List<CDCApplicationDTO> getApplicationsByJob(Long jobId) {

    List<Application> applications = applicationRepository.findAll();
    List<CDCApplicationDTO> list = new ArrayList<>();

    for (Application app : applications) {

        if (app.getJobPost().getId().equals(jobId)) {

            CDCApplicationDTO dto = new CDCApplicationDTO();
            
            dto.setApplicationId(app.getId()); 
            dto.setStudentName(app.getName());
            dto.setJobTitle(app.getJobPost().getTitle());
            dto.setStatus(app.getStatus().name());
            dto.setResumePath(app.getResumePath());
            dto.setCgpa(app.getCgpa());
            dto.setSkills(app.getSkills());
            // 🔥 THIS IS WHAT YOU MISSED
            dto.setCompanyName(app.getJobPost().getCompanyName());
            dto.setSalary(app.getJobPost().getSalary());

            list.add(dto);
        }
    }

    return list;
}
    public Application getApplicationById(Long id) {
    return applicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
}
    public List<JobSummaryDTO> getAllJobsWithCounts() {

    List<JobPost> jobs = jobPostRepository.findAll();
    List<Application> applications = applicationRepository.findAll();

    Map<Long, Long> countMap = new HashMap<>();

    // count applications per job
    for (Application app : applications) {
        Long jobId = app.getJobPost().getId();
        countMap.put(jobId, countMap.getOrDefault(jobId, 0L) + 1);
    }

    List<JobSummaryDTO> result = new ArrayList<>();

    for (JobPost job : jobs) {
        JobSummaryDTO dto = new JobSummaryDTO();
        dto.setJobId(job.getId());
        dto.setJobTitle(job.getTitle());
        dto.setAppliedCount(countMap.getOrDefault(job.getId(), 0L));
        dto.setCompanyName(job.getCompanyName());
        dto.setSalary(job.getSalary());
        dto.setCompanyName(job.getCompanyName());
dto.setSalary(job.getSalary());
        result.add(dto);
    }

    return result;
}
}
