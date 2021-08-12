<template>\
<el-button type="primary" icon="el-icon-folder-add" @click="dialogFormVisible = true" circle></el-button>
<el-dialog title="화상채팅방 생성" v-model="dialogFormVisible">
    <el-form :model="form">
    <el-form-item prop="roomName" label="방 이름" :label-width="formLabelWidth">
        <!-- <el-input v-model="mySessionId" autocomplete="off" type="text" required></el-input> -->
        <el-input v-model="mySessionId" autocomplete="off" id="rName" value=""></el-input>
    </el-form-item>
    <el-form-item prop="keyword" label="키워드" id="kTag" :label-width="formLabelWidth">
        <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)"
        id="kTag">
        {{tag}}
        </el-tag>
        <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="mini"
        @keyup.enter="handleInputConfirm"
        @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
    </el-form-item>
    <el-form-item prop="personNum" label="인원" :label-width="formLabelWidth">
        <el-input-number v-model="num" id = "pNum" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
    </el-form-item>
    <el-form-item prop="roomTheme" label="카테고리" :label-width="formLabelWidth">
        <!-- <el-radio v-model="radio" label="book">Book</el-radio>
        <el-radio v-model="radio" label="movie">Movie</el-radio> -->
        <!-- book일때, room일때 구분해줘야함. -->
        <el-select v-model="bValue" placeholder="Book">
            <el-option
                v-for="item in b_options"
                :key="item.bookCategoryId"
                :label="item.bookCategory"
                :value="item.bookCategoryId">
            </el-option>
        </el-select>
        <el-select v-model="mValue" placeholder="Movie">
            <el-option
                v-for="item in m_options"
                :key="item.movieCategoryId"
                :label="item.movieCategory"
                :value="item.movieCategoryId">
            </el-option>
        </el-select>
    </el-form-item>
    <el-form-item prop="roomLock" label="방 잠금" :label-width="formLabelWidth">
        <el-checkbox v-model="checkedLock" @change="handleCheckbox"></el-checkbox>
        <el-input v-model="form.pwd" v-show="isLocked" placeholder="비밀번호를 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호를 입력하세요.'" id="rPwd" value=""></el-input>
    </el-form-item>
    <div class="image-box">
        <label for="file">채팅방 사진 등록하기</label>
        <input type="file" id="file" ref="files" @change="imageUpload" multiple />
    </div>
    </el-form>
    <template #footer>
    <span class="dialog-footer">
        <el-button type="primary" @click="[formRoom(),joinSession(),fromClose1()]" >생성</el-button>
        <el-button @click="formClose()">취소</el-button>
    </span>
    </template>
</el-dialog>
    <div id="session" v-if="session">
        <div id="session-header">
            <!-- <h1 id="session-title">{{mySessionId}}</h1> -->
            <h1 id="session-title">{{mySessionId}}</h1>
            <!-- <el-button type="primary" id="blockIcon" icon="el-icon-turn-off-microphone" @click="blockUnblock" circle></el-button>
            <el-button type="primary" icon="el-icon-microphone" @click="dialogFormVisible = true" circle></el-button> -->
            <input class="btn btn-large btn-danger" type="checkbox" id="switchBlock" @click="blockUnblock" v-model="block"> 비디오중지
            <input class="btn btn-large btn-danger" type="checkbox" id="switchMute" @click="muteUnmute" v-model="mute"> 음소거
            <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="[deleteRoom(),leaveSession(),formClose()]" value="Leave session">
        </div>
        <!-- <div id="main-video" class="col-md-6">
            <user-video :stream-manager="mainStreamManager"/>
        </div> -->
        <div id="video-container" class="col-md-6">
            <user-video :stream-manager="publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
            <user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './UserVideo';
import { useStore } from 'vuex';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + "i5a407.p.ssafy.io";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
    name: 'CreateRoom',

    components: {
        UserVideo,
    },

    data() {
        const store = useStore();
    return {
        dynamicTags: ['키워드를', '입력하세요'],
        inputVisible: false,
        inputValue: '',
        checkedLock: false,
        b_options:[],
        m_options:[],
        num: 1,
        bValue:'',
        mValue:'',
        dialogFormVisible: false,
        form: {
            name: '',
            pwd:''
        },
        mySessionId : '',
        myUserName: store.state.root.userInfo.nickname,
        
        isLocked: false,
        formLabelWidth: '120px',
        files: [], //업로드용 파일
        filesPreview: [],
        uploadImageIndex: 0, // 이미지 업로드를 위한 변수
        
        OV: undefined,
        session: undefined,
        mainStreamManager: undefined,
        publisher: undefined,
        subscribers: [],

        block : false,
        mute : false,

    };
    },
    mounted() {
        this.$nextTick(function () {
            axios.get('category/book')
            .then((response) =>  { this.b_options = response.data;
                console.log(response.data);
                console.log(this.b_options);
            }).catch(function (error) { console.log(error); })

            axios.get('category/movie')
            .then((response) =>  { this.m_options = response.data;
                console.log(response.data);
                console.log(this.m_options);
            }).catch(function (error) { console.log(error); })
        })
    },
    methods: {
    fromClose1(){
        this.dialogFormVisible = false;
    },
    formClose() {
        this.dialogFormVisible = false;

        this.dynamicTags = ['키워드를', '입력하세요'];
        this.inputVisible = false
        this.inputValue = ''
        this.checkedLock = false
        this.num=1
        this.mySessionId=''
        this.bValue=''
        this.mValue=''
        //this.form.name=''
        this.isLocked=false
        this.form.pwd=''
    },
    handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },

    showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
        });
    },

    handleInputConfirm() {
        let inputValue = this.inputValue;
        if (inputValue) {
            this.dynamicTags.push(inputValue);
        }
        this.inputVisible = false;
        this.inputValue = '';
    },
    handleChange(value) {
        console.log(value);
    },
    handleCheckbox() {
        this.isLocked= !this.isLocked;
    },
    //방생성 API
    formRoom(){
        console.log("formRoom");
        //store.dispatch('root/requestRoomInfo', roomInfo)
            //const roomName= document.getElementById("mySessionId");
            const roomName= document.getElementById("rName");
            //const hostId = 10000;
            const keywords = [];
            //const keywords = document.getElementById("kTag");
            const limit = document.getElementById("pNum");
            // const bookCategory = 0;
            // const movieCategory = 1;
            const url = "";
            const password = document.getElementById("rPwd");
            axios({
                method:"POST",
                url: "rooms",
                data:{
                    roomName : rName.value,
                    hostId: 1,//store.state.root.userInfo.user_id,
                    keywords : this.dynamicTags,
                    limit: pNum.value,
                    bookCategory:this.bValue,
                    movieCategory: this.mValue,
                    //"roomInviteCode": String, 
                    //password: String,
                    url: "src/assets/images/ssafy-logo.png",
                    //"email": email.value,
                    password: password.value,
                }
            }).then((res)=>{
                console.log(res);
            }).catch(error=>{
                console.log("에러 발생ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
                console.log(roomName.value);
                console.log(error);
                throw new Error(error);
            });
    },
    //방삭제 API
    deleteRoom(){
        console.log("deleteRoom");
        // axios.delete("rooms").then(function(response){
        //     console.log(response);
        // }).catch(function(ex){
        //     throw new Error(ex)
        // });

        // axios.delete('/rooms/val') //val = 특정 값
        // .then(res => {
        //     console.log(res);  
        // })

        // axios.delete(`rooms${rId}`)
        // .then((res)=>{
        //         console.log(res);
        //     }).catch(error=>{
        //         console.log(error);
        //         throw new Error(error);
        //     });
        //store.dispatch('root/requestRoomInfo', roomInfo)
    },

    // 오픈비두
    joinSession () {
        // --- Get an OpenVidu object ---
        this.OV = new OpenVidu();

        // --- Init a session ---
        this.session = this.OV.initSession();

        // --- Specify the actions when events take place in the session ---

        // On every new Stream received...
        this.session.on('streamCreated', ({ stream }) => {
            const subscriber = this.session.subscribe(stream);
            this.subscribers.push(subscriber);
        });

        // On every Stream destroyed...
        this.session.on('streamDestroyed', ({ stream }) => {
            const index = this.subscribers.indexOf(stream.streamManager, 0);
            if (index >= 0) {
                this.subscribers.splice(index, 1);
            }
        });

        // On every asynchronous exception...
        this.session.on('exception', ({ exception }) => {
            console.warn(exception);
        });

        // --- Connect to the session with a valid user token ---

        // 'getToken' method is simulating what your server-side should do.
        // 'token' parameter should be retrieved and returned by your own backend
        this.getToken(this.mySessionId).then(token => {
            this.session.connect(token, { clientData: this.myUserName })
                .then(() => {

                    // --- Get your own camera stream with the desired properties ---

                    let publisher = this.OV.initPublisher(undefined, {
                        audioSource: undefined, // The source of audio. If undefined default microphone
                        videoSource: undefined, // The source of video. If undefined default webcam
                        publishAudio: true,     // Whether you want to start publishing with your audio unmuted or not
                        publishVideo: true,     // Whether you want to start publishing with your video enabled or not
                        resolution: '640x480',  // The resolution of your video
                        frameRate: 30,         // The frame rate of your video
                        insertMode: 'APPEND',   // How the video is inserted in the target element 'video-container'
                        mirror: false          // Whether to mirror your local video or not
                    });

                    this.mainStreamManager = publisher;
                    this.publisher = publisher;

                    // --- Publish your stream ---

                    this.session.publish(this.publisher);

                })
                .catch(error => {
                    console.log('There was an error connecting to the session:', error.code, error.message);
                });
        });
        
        window.addEventListener('beforeunload', this.leaveSession)
    },
    blockUnblock (){
        var videoEnabled = this.block;
        this.publisher.publishVideo(videoEnabled);
    },
    muteUnmute (){
        var audioEnabled = this.mute;
        this.publisher.publishAudio(audioEnabled);
    },
    leaveSession () {
        // --- Leave the session by calling 'disconnect' method over the Session object ---
        if (this.session) this.session.disconnect();

        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;

        window.removeEventListener('beforeunload', this.leaveSession);
    },

    updateMainVideoStreamManager (stream) {
        if (this.mainStreamManager === stream) return;
        this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server   (POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken (mySessionId) {
        return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession (sessionId) {
        return new Promise((resolve, reject) => {
            axios
                .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
                    customSessionId: sessionId,
                }), {
                    auth: {
                        username: 'OPENVIDUAPP',
                        password: OPENVIDU_SERVER_SECRET,
                    },
                })
                .then(response => response.data)
                .then(data => resolve(data.id))
                .catch(error => {
                    if (error.response.status === 409) {
                        resolve(sessionId);
                    } else {
                        console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
                        if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
                            location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
                        }
                        reject(error.response);
                    }
                });
        });
    },

      // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken (sessionId) {
        return new Promise((resolve, reject) => {
            axios
                .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
                    auth: {
                        username: 'OPENVIDUAPP',
                        password: OPENVIDU_SERVER_SECRET,
                    },
                })
                .then(response => response.data)
                .then(data => resolve(data.token))
                .catch(error => reject(error.response));
        });
    },
    }
}
</script>
<style>
.room-name{
    width:80%;
}
.el-tag + .el-tag {
    margin-left: 10px;
}
.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}
.image-box {
    margin-top: 10px;
    padding-bottom: 5px;
    text-align: center;
}

.image-box input[type='file'] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

.image-box label {
    display: inline-block;
    padding: 10px 20px;
    background-color: #4F7178;
    color: #fff;
    vertical-align: middle;
    font-size: 15px;
    cursor: pointer;
    border-radius: 5px;
}

.el-dropdown {
    vertical-align: top;
}
.el-dropdown + .el-dropdown {
margin-left: 15px;
}
.el-icon-arrow-down {
font-size: 12px;
}

/*openvidu*/ 
html {
	position: relative;
	min-height: 100%;
}

nav {
	height: 50px;
	width: 100%;
	z-index: 1;
	background-color: #4d4d4d !important;
	border-color: #4d4d4d !important;
	border-top-right-radius: 0 !important;
	border-top-left-radius: 0 !important;
}

.navbar-header {
	width: 100%;
}

.nav-icon {
	padding: 5px 15px 5px 15px;
	float: right;
}

nav a {
	color: #ccc !important;
}

nav i.fa {
	font-size: 40px;
	color: #ccc;
}

nav a:hover {
	color: #a9a9a9 !important;
}

nav i.fa:hover {
	color: #a9a9a9;
}

#main-container {
	padding-bottom: 80px;
}

/*vertical-center {
	position: relative;
	top: 30%;
	left: 50%;
	transform: translate(-50%, -50%);
}*/

.horizontal-center {
	margin: 0 auto;
}

.form-control {
	color: #0088aa;
	font-weight: bold;
}

.form-control:focus {
	border-color: #0088aa;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(0, 136, 170, 0.6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(0, 136, 170, 0.6);
}

input.btn {
	font-weight: bold;
}

.btn {
	font-weight: bold !important;
}

.btn-success {
	background-color: #06d362 !important;
	border-color: #06d362;
}

.btn-success:hover {
	background-color: #1abd61 !important;
	border-color: #1abd61;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 60px;
	background-color: #4d4d4d;
}

.footer .text-muted {
	margin: 20px 0;
	float: left;
	color: #ccc;
}

.openvidu-logo {
	height: 35px;
	float: right;
	margin: 12px 0;
	-webkit-transition: all 0.1s ease-in-out;
	-moz-transition: all 0.1s ease-in-out;
	-o-transition: all 0.1s ease-in-out;
	transition: all 0.1s ease-in-out;
}

.openvidu-logo:hover {
	-webkit-filter: grayscale(0.5);
	filter: grayscale(0.5);
}

.demo-logo {
	margin: 0;
	height: 22px;
	float: left;
	padding-right: 8px;
}

a:hover .demo-logo {
	-webkit-filter: brightness(0.7);
	filter: brightness(0.7);
}

#join-dialog {
	margin-left: auto;
    margin-right: auto;
    max-width: 70%;
}

#join-dialog h1 {
	color: #4d4d4d;
	font-weight: bold;
	text-align: center;
}

#img-div {
	text-align: center;
	margin-top: 3em;
	margin-bottom: 3em;
	/*position: relative;
	top: 20%;
	left: 50%;
	transform: translate(-50%, -50%);*/
}

#img-div img {
	height: 15%;
}

#join-dialog label {
	color: #0088aa;
}

#join-dialog input.btn {
	margin-top: 15px;
}

#session-header {
	margin-bottom: 20px;
}

#session-title {
	display: inline-block;
    background-color: #e9e6c4;
}

#buttonLeaveSession {
	float: right;
	margin-top: 20px;
}

#video-container video {
	position: relative;
	float: left;
	width: 50%;
	cursor: pointer;
}

#video-container video + div {
	float: left;
	width: 50%;
	position: relative;
	margin-left: -50%;
}

#video-container p {
	display: inline-block;
	background: #f8f8f8;
	padding-left: 5px;
	padding-right: 5px;
	color: #777777;
	font-weight: bold;
	border-bottom-right-radius: 4px;
}

video {
	width: 100%;
	height: auto;
}

#main-video p {
	position: absolute;
	display: inline-block;
	background: #f8f8f8;
	padding-left: 5px;
	padding-right: 5px;
	font-size: 22px;
	color: #777777;
	font-weight: bold;
	border-bottom-right-radius: 4px;
}

#session img {
	width: 100%;
	height: auto;
	display: inline-block;
	object-fit: contain;
	vertical-align: baseline;
}

#session #video-container img {
	position: relative;
	float: left;
	width: 50%;
	cursor: pointer;
	object-fit: cover;
	height: 180px;
}

</style>
