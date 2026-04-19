package com.college.placementportal.dto;

import java.util.List;

public class DashboardResponse {

    private long totalStudents;
    private long totalJobs;

    private long placedStudents;
    private double placementPercentage;

    private List<JobStatsDTO> jobStats;
    private List<String> recentActivities;

    public long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(long totalStudents) { this.totalStudents = totalStudents; }

    public long getTotalJobs() { return totalJobs; }
    public void setTotalJobs(long totalJobs) { this.totalJobs = totalJobs; }

    public long getPlacedStudents() { return placedStudents; }
    public void setPlacedStudents(long placedStudents) { this.placedStudents = placedStudents; }

    public double getPlacementPercentage() { return placementPercentage; }
    public void setPlacementPercentage(double placementPercentage) { this.placementPercentage = placementPercentage; }

    public List<JobStatsDTO> getJobStats() { return jobStats; }
    public void setJobStats(List<JobStatsDTO> jobStats) { this.jobStats = jobStats; }

    public List<String> getRecentActivities() { return recentActivities; }
    public void setRecentActivities(List<String> recentActivities) { this.recentActivities = recentActivities; }
}