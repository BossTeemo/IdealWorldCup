package com.example.idealworldcup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JobDataManager {

    private static final List<JobData> jobList;

    static {
        jobList = new ArrayList<>(Arrays.asList(


                new JobData("데이터 사이언티스트", "대용량 데이터를 분석하고 해석하여 비즈니스 인사이트를 제공하는 전문가.",
                        "데이터분석", "딥러닝기초", "머신러닝기초", "디지털시스템"),
                new JobData("AI 엔지니어","인공지능 기술을 개발하고 적용하는 역할을 수행하는 전문가",
                        "기계학습", "AI기반 영상처리", "딥러닝 프로그래밍", "인공지능 응용"),
                new JobData("IoT개발자", "인터넷에 연결된 물리적 장치들이 서로 정보를 주고받을 수 있게 하는 기술인",
                        "IoT(Internet of Things)를 개발하고 관리하는 전문가", "컴퓨터 네트워크","IoT 시스템설계 실습"),
                new JobData("UI/UX 디자이너","사용자 인터페이스(UI)와 사용자 경험(UX)를 설계하는 전문가로, 사용자가 제품을 이해하고 사용하기 쉽도록 디자인하는 전문가",
                        "UI/UX디자인", "HCI와 UX평가", "데이터 시각화"),
                new JobData("웹 개발자", "인터넷 환경에서 동작하는 웹사이트나 웹 애플리케이션을 개발하는 역할을 하는 전문가",
                        "웹 프로그래밍", "웹서비스설계및실습"),
                new JobData("데이터베이스 관리자", "데이터베이스 시스템의 설계, 구축, 운영, 관리 등을 담당하는 전문가",
                        "데이터베이스", "자료구조", "데이터 마이닝"),
                new JobData("VR, AR개발자", "가상 현실(VR)과 증강 현실(AR) 기술을 개발하고 적용하는 역할을 하는 전문가",
                        "증강현실", "가상현실"),
                new JobData("마케팅 및 상업분석가","기업의 마케팅 전략을 수립하고 실행하는데 필요한 데이터를 수집, 분석하는 역할을 하는 전문가",
                        "텍스트 마이닝", "소셜 네트워크 분석", "빅데이터 처리 및 응용")

        ));
    }

    public static List<JobData> getJobList() {
        Collections.shuffle(jobList);  // 랜덤하게 리스트를 섞음.
        return jobList;
    }
}
