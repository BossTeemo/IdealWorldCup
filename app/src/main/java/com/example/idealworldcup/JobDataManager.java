package com.example.idealworldcup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobDataManager {

    private static final List<JobData> jobList;

    static {
        jobList = new ArrayList<>(Arrays.asList(
                new JobData("데이터 사이언티스트", "대용량 데이터를 분석하고 해석하여 비즈니스 인사이트를 제공하는 전문가.",
                        "데이터분석", "딥러닝기초", "머신러닝기초", "디지털시스템")
                // 다른 직업 데이터도 이곳에 추가...
        ));
    }

    public static List<JobData> getJobList() {
        return jobList;
    }
}
