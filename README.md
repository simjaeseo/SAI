![image](/uploads/a96fc83be7c9523e23cadea4af27d9d4/image.png)

# 웹서비스 소개

SAI는 언제 어디서든 면접 준비가 가능한 모의 면접 컨설팅 서비스입니다.

SAI는 삼성 청년 SW 아카데미의 취업지원센터와 교육생의 연계를 강화하기위해 만들어졌습니다.

SAI는 나혼자 면접 연습 서비스에서 기출 면접 질문, 직접 질문 추가 등을 통해 원하는 질문에 대해 연습이 가능하며 TTS (Text-To-Speech) 기능을 통해 더욱 현실감 있는 면접 환경을 제공합니다.

또한, 나혼자 면접 연습 서비스를 마친 후, 질문 별 영상을 각각 저장하여 제공하며, STT(Speech-To-Text), 음성 높낮이 분석, 자세 분석 (Teachable Machine), 표정 분석(Face API)와 같이 진행한 면접에 대한 분석을 제공합니다.

이와 더불어, 진행한 모의면접을 취업 컨설턴트에게 피드백 요청을 보낼 수 있으며, 취업 컨설턴트는 교육생의 모의 면접 영상에 대해 질문별로 피드백을 남길 수 있습니다.

SAI는 컨설턴트와 1:1 면접 서비스를 통해 취업 컨설턴트와 실시간 화상 1:1 모의면접 기능을 제공합니다.

## 핵심기능

#### 일정관리 
```
컨설턴트는 개인일정을 등록하고 확인 할 수 있고, 교육생은 컨설턴트의 일정으로 인해 불가능한 시간을 확인 할 수 있습니다.
교육생은 해당 시간을 피해 원하는 시간에 모의 면접을 신청할 수 있으며 개인 일정을 등록하여 관리할 수 있습니다.
```

#### 나 혼자 연습
```
컨설턴트와 시간 조율이 쉽지 않기에 나 혼자 연습 기능을 제공합니다.
인성, 직무 별 사전 질문들을 제공하고 개인 별 원하는 질문을 등록하고 원하는 질문만 선택하여 면접연습을 진행 할 수 있습니다. 
혼자 연습이지만 TTS를 통해 질문 스크립트를 읽어주어 실제와 비슷한 환경으로 면접을 진행할 수 있습니다. 
진행한 면접 영상과 음성은 저장되고 컨설턴트에게 피드백 신청을 할 수 있습니다.
```

#### 컨설턴트 1대1 모의 면접 
```
Openvidu를 활용한 컨설턴트 교육생 1대1 모의 면접 기능을 제공합니다.
```

#### 면접 피드백
```
질문 별 답변영상 확인을 통한 컨설턴트 피드백 
Google Speech API STT기능을 이용한 답변 스크립트 생성 
오디오 데이터 시각화
Teachable Machine을 사용한 자세 흔들림 분석 
Face API를 이용한 표정분석
총평과 같은 면접 피드백을 제공합니다.
```


## [📃 개발일지 Wiki](https://lab.ssafy.com/s07-webmobile2-sub2/S07P12C206/-/wikis/home)

## :sparkles: 팀 구성 

| 팀　장 | 팀　원 | 팀　원 | 팀　원 | 팀　원 | 팀　원 |
| :-----: | :-----: | :---: | :-----: | :-----: | :-----: |
| **최인호** | **윤형준** | **지 근** | **조덕희** | **심재서** | **김지수** |
| <img src="https://img.shields.io/badge/FE-F8DC75"> | <img src="https://img.shields.io/badge/FE-F8DC75"> | <img src="https://img.shields.io/badge/BE-6DB33F">  | <img src="https://img.shields.io/badge/BE-6DB33F"> | <img src="https://img.shields.io/badge/BE-6DB33F"> | <img src="https://img.shields.io/badge/FE-F8DC75"> |


<img src = "/uploads/f50ed29f5e5cb395e08f9aaf1ab1ddd0/image.png" width = "60" height = "60">
인호 : 개발 프로젝트는 처음인 내가 이 세계에서는 팀장이라니?! 열심히 하겠습니다 화이팅! <br>     
<img src = "/uploads/fd220bd593e4692498289ba0cd328050/image.png" width = "60" height = "60">
형준 : 저는 바보입니다 <br> 
<img src = "/uploads/7814fe722369bce8eeb7484103c5e4f8/image.png" width = "60" height = "60">
지근 : "아무거나 다 좋아요"가 없는 팀원이 되겠습니다 !  <br>
<img src = "/uploads/044cc950ab727ae05ca3b21972ea360b/image.png" width = "60" height = "60">
덕희 : 전공자지만 제대로 된 프로젝트는 처음입니다. 좋은 결과를 위해 열심히 참여하겠습니다! <br>
<img src = "/uploads/754059cebbec97d3f7228f521ad9f9d0/image.png" width = "60" height = "60">
재서 : 다같이 열심히해서 좋은 프로젝트 만들어봐요😊 <br>
<img src = "/uploads/30eb7d89542ecece03f421638327e35a/image.png" width = "60" height = "60">
지수 : 수학은 10 넘어가면 어지러워요 그치만 넥슨 연락주세요   


## 기술 스택
#### FE
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">
<br>

#### BE
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white">
<img src="https://img.shields.io/badge/jpa-6DB33F?style=for-the-badge&logo=jpa&logoColor=white">
<br>

#### Infra
<img src="https://img.shields.io/badge/Nginx-009639C?style=for-the-badge&logo=Nginx&logoColor=white">
<img src="https://img.shields.io/badge/Aws-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
<img src="https://img.shields.io/badge/google-4285F4?style=for-the-badge&logo=googlecloud&logoColor=white">
<br>

#### IDE
<img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white">
<img src="https://img.shields.io/badge/Inteelij-000000?style=for-the-badge&logo=Intellijidea&logoColor=white">
<br>

#### Tool
<img src="https://img.shields.io/badge/POSTMAN-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">
<img src="https://img.shields.io/badge/Mattermost-0058CC?style=for-the-badge&logo=Mattermost&logoColor=white">
<img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=JIRAsoftware&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
<br>
