package com.example.idealworldcup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobData {

    private String jobName;  // 직업 이름
    private String jobDescription;  // 직업 설명
    private List<String> subjects;  // 관련 과목 리스트

    private int imageResourceId;

    // 생성자
    public JobData(String jobName, String jobDescription, int imageResourceId, String... subjects) {
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.imageResourceId = imageResourceId;
        this.subjects = new ArrayList<>(Arrays.asList(subjects));
    }

    // Getter 및 Setter 메서드
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
