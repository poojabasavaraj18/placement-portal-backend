package com.college.placementportal.controller;

public class JobSummaryDTO {

    private Long jobId;
    private String jobTitle;
    private long appliedCount;

    // getters and setters

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public long getAppliedCount() {
        return appliedCount;
    }

    public void setAppliedCount(long appliedCount) {
        this.appliedCount = appliedCount;
    }
}