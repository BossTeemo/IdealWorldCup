package com.example.idealworldcup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobDataManager {

    private static final List<JobData> jobList;
    private static final Map<String, Integer> jobImageMap; // 직업 이름과 이미지 리소스 ID를 매핑하는 맵


    static {
        jobList = new ArrayList<>(Arrays.asList(
                new JobData("데이터 사이언티스트", "대용량 데이터를 분석하고 해석하여\n비즈니스 인사이트를 제공하는 전문가.",
                        R.drawable.data_scientist, "데이터분석\n딥러닝기초\n머신러닝기초\n디지털시스템"),
                new JobData("AI 엔지니어", "인공지능 기술을 개발하고\n적용하는 역할을 수행하는 전문가",
                        R.drawable.ai_engineer, "기계학습\nAI기반 영상처리\n딥러닝 프로그래밍\n인공지능 응용"),
                new JobData("IoT개발자", "인터넷에 연결된 물리적 장치들이\n서로 정보를 주고받을 수 있게 하는 기술인",
                        R.drawable.iot_developer, "컴퓨터 네트워크\nIoT 시스템설계 실습"),
                new JobData("UI/UX 디자이너", "사용자 인터페이스(UI)와 사용자 경험(UX)를\n설계하는 전문가로,\n사용자가 제품을 이해하고 사용하기 쉽도록\n디자인하는 전문가",
                        R.drawable.ui_ux_designer, "UI/UX디자인\nHCI와 UX평가\n데이터 시각화"),
                new JobData("웹 개발자", "인터넷 환경에서 동작하는 웹사이트나\n웹 애플리케이션을 개발하는 역할을 하는 전문가",
                        R.drawable.web_developer, "웹 프로그래밍\n웹서비스설계및실습"),
                new JobData("데이터베이스 관리자", "데이터베이스 시스템의 설계, 구축,\n운영, 관리 등을 담당하는 전문가",
                        R.drawable.database_manager, "데이터베이스\n자료구조\n데이터 마이닝"),
                new JobData("VR, AR개발자", "가상 현실(VR)과 증강 현실(AR) 기술을\n개발하고 적용하는 역할을 하는 전문가",
                        R.drawable.vr_ar_developer, "증강현실\n가상현실"),
                new JobData("마케팅 및 상업분석가", "기업의 마케팅 전략을 수립하고\n실행하는데 필요한 데이터를\n수집, 분석하는 역할을 하는 전문가",
                        R.drawable.marketing_and_commercial_analyst, "텍스트 마이닝\n소셜 네트워크 분석\n빅데이터 처리 및 응용")
        ));
        jobImageMap = new HashMap<>();
        jobImageMap.put("데이터 사이언티스트", R.drawable.data_scientist);
        jobImageMap.put("AI 엔지니어", R.drawable.ai_engineer);
        jobImageMap.put("IoT개발자", R.drawable.iot_developer);
        jobImageMap.put("UI/UX 디자이너", R.drawable.ui_ux_designer);
        jobImageMap.put("웹 개발자", R.drawable.web_developer);
        jobImageMap.put("데이터베이스 관리자", R.drawable.database_manager);
        jobImageMap.put("VR, AR개발자", R.drawable.vr_ar_developer);
        jobImageMap.put("마케팅 및 상업분석가", R.drawable.marketing_and_commercial_analyst);

    }
    // 직업 이름에 해당하는 이미지 리소스 ID를 반환하는 메서드
    public static int getImageResourceIdForJob(String jobName) {
        if (jobImageMap.containsKey(jobName)) {
            return jobImageMap.get(jobName);
        } else {
            return -1; // 적절한 이미지 리소스 ID가 없는 경우 -1 반환
        }
    }
    public static List<JobData> getJobList() {
        Collections.shuffle(jobList);  // 랜덤하게 리스트를 섞음.
        return jobList;
    }
}