
![sayworld-logo](https://i.imgur.com/ADnSC3t.png)

# 🌎 SAYWORLD

![web](https://img.shields.io/badge/platform-web-yellow) ![framework](https://img.shields.io/badge/framework-SpringBoot-9cf) ![library](https://img.shields.io/badge/libray-Vue-orange) ![db](https://img.shields.io/badge/database-MySQL-inactive)

> 책/영화를 기반으로 한 문화생활 공유 화상 플랫폼
## 👨‍👨‍👦‍👦 팀원
🧡 **박채린**
💛 **박지현**
💚 **박형민**
💙 **안수빈**
💜 **정성학**

## 🎥 프로젝트 개요
### 진행 기간
- 2021.07.05 ~ 2021.08.20

### 주제
- 문화생활을 기록하고 공유하는 화상 플랫폼

### 목표
1. 웹과 openvidu 기술을 접목하여 비디오 컨퍼런스 서비스를 구현한다.
2. 변화하는 시대에서 필요한 서비스를 기획 및 구현한다.
3. 책/영화 취향과 관심사과 같은 사람들의 모임을 쉽게 찾고, 작품에 대한 생각을 공유하는 온라인 공간을 만든다.
4. 읽은 책/영화 목록을 자신의 페이지에서 확인하고, 나만의 지식으로 기록해둘 수 있는 공간을 만든다.

### 프로젝트 RULE
1. JPA를 사용해서 DB와 백엔드를 연동한다.
2. Jira를 사용하여 1주일 단위의 스프린트를 진행하고 프로젝트를 관리한다.
3. 매일 오전, 오후 스크럼 미팅을 실시하고 Mattermost에 기록한다.
4. 서비스 배포 환경으로는 아마존 EC2와 Docker 컨테이너를 사용한다.
5. 정한 git commit 규칙을 준수하여 프로젝트 형상 관리를 한다.


### 와이어프레임
![wireframe](https://i.imgur.com/logFNE8.png)


<br>

## 🍀 핵심기능
![important](https://i.imgur.com/IV1Xvmk.png)
1) 토글을 활용한 독서/영화 서비스 전환
2) 카테고리/키워드 별로 화상회의 검색 및 모임 참석 
3) S3저장소를 이용한 화상방 썸네일 이미지 저장, 내부 채팅 기능
4) WebRTC 기술을 활용한 다대다 화상회의
5) 네이버 API를 활용한 작품명 검색
6) 문화력 등급 기능
7) 생각 정리를 위한 카드 뉴스 형태의 개인 블로그

### 책과 영화의 분리
![toggle](https://i.imgur.com/07VLmRO.gif)
### 카테고리화 및 검색
![search](https://i.imgur.com/WazQk4K.gif)
### 화상회의 및 채팅

### 문화력 기록
![culture](https://i.imgur.com/QyqziXB.gif)

## 📚 Tech Stack
<details>
    <summary>Front</summary>
    <ul>
        <li>Vue3</li>
        <li>Vue CLI</li>
        <li>Vuex</li>
        <li>BootStrap</li>
        <li>Element Plus</li>
    </ul>
</details>
<details>
    <summary>Back</summary>
    <ul>
        <li>SpringBoot</li>
        <li>MySQL</li>
        <li>Swagger</li>
        <li>JPA</li>
        <li>docker</li>
    </ul>
</details>
<br>

## 📊 서비스 구조도
![service-architecture](https://i.imgur.com/xSdacUV.png)

## 💿 DB 모델링
![ERD](https://i.imgur.com/L4UkXiS.png)
## 💻 개발환경
- Java : jdk1.8 (1.8.0_192)
- node.js : 14.17.3
- npm : 6.14.13
- vue-cli : 4.5.13
- IntelliJ : 2021.1.3
- openvidu : 2.19.0

## 💻 실행방법
### Frontend
1. Install packages
```bash=
cd frontend
npm i
```
2. Run Server
```bash=
npm run server
```
### Backend
#### Intellij 사용시
```
gradle란 clean > build > GroupCallApplication.java 실행 
```

#### terminal/bash
```bash= 
# jar 파일 생성 경로 - /backend
gradle clean build 
# 실행 경로 - /backend/src/build/libs
java -jar ssafy-fifth-web-common-project-1.0-SNAPSHOP.jar 

```
## 📃 최종 산출물

- [최종 발표 PPT](documentation/Sayworld_최종발표.pdf)
- [SayWorld UCC](documentation/Sayworld_UCC.mp4)
