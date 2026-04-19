package com.college.placementportal.dto;

public class JobStatsDTO {

    private Long jobId;
    private String jobTitle;

    private int applied = 0;
    private int round1 = 0;
    private int round2 = 0;
    private int selected = 0;
    private int rejected = 0;

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public int getApplied() { return applied; }
    public void setApplied(int applied) { this.applied = applied; }

    public int getRound1() { return round1; }
    public void setRound1(int round1) { this.round1 = round1; }

    public int getRound2() { return round2; }
    public void setRound2(int round2) { this.round2 = round2; }

    public int getSelected() { return selected; }
    public void setSelected(int selected) { this.selected = selected; }

    public int getRejected() { return rejected; }
    public void setRejected(int rejected) { this.rejected = rejected; }
}