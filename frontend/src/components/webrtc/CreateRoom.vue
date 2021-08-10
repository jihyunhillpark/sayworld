<template>
<el-button type="primary" icon="el-icon-folder-add" @click="dialogFormVisible = true" circle></el-button>
<el-dialog title="화상채팅방 생성" v-model="dialogFormVisible">
    <el-form :model="form">
    <el-form-item prop="roomName" label="방 이름" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" id="rName" value=""></el-input>
    </el-form-item>
    <el-form-item prop="keyword" label="키워드" id="kTag" :label-width="formLabelWidth">
        <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)">
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
    <el-form-item prop="roomTheme" label="테마" :label-width="formLabelWidth">
        <el-radio v-model="radio" label="book">Book</el-radio>
        <el-radio v-model="radio" label="movie">Movie</el-radio>
    </el-form-item>
    <el-form-item prop="roomLock" label="방 잠금" :label-width="formLabelWidth">
        <el-checkbox v-model="checkedLock" @change="handleCheckbox"></el-checkbox>
        <el-input v-model="form.pwd" v-show="isLocked" placeholder="비밀번호를 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호를 입력하세요.'"></el-input>
    </el-form-item>
    <div class="image-box">
        <label for="file">채팅방 사진 등록하기</label>
        <input type="file" id="file" ref="files" @change="imageUpload" multiple />
    </div>
    </el-form>
    <template #footer>
    <span class="dialog-footer">
        <el-button type="primary" @click="[formClose(),formRoom(),joinSession()]" >생성</el-button>
        <el-button @click="formClose()">취소</el-button>
    </span>
    </template>
</el-dialog>
    <div id="session" v-if="session">
        <div id="session-header">
            <h1 id="session-title">{{ mySessionId }}</h1>
            <input class="btn btn-large btn-danger" type="checkbox" id="switchBlock" @click="blockUnblock" v-model="block"> 비디오중지
            <input class="btn btn-large btn-danger" type="checkbox" id="switchMute" @click="muteUnmute" v-model="mute"> 음소거
            <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
        </div>
        <div id="main-video" class="col-md-6">
            <user-video :stream-manager="mainStreamManager"/>
        </div>
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

const store = useStore();

export default {
    name: 'CreateRoom',

    components: {
        UserVideo,
    },

    data() {
    return {
        dynamicTags: ['키워드를', '입력하세요'],
        inputVisible: false,
        inputValue: '',
        checkedLock: false,
        radio: 'book',
        num: 1,
        dialogFormVisible: false,
        form: {
            name: '',
            pwd:''
        },
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
    methods: {
    formClose() {
        this.dialogFormVisible = false;

        this.dynamicTags = ['키워드를', '입력하세요'];
        this.inputVisible = false
        this.inputValue = ''
        this.checkedLock = false
        this.radio = 'book'
        this.num=1
        this.form.name=''
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
        // var roomInfo = {
        //     roomName: this.form.name,
        //     //hostId: int,
        //     keywords :this.dynamicTags,
        //     limit: this.num,
        //     //bookCategory: name,
        //     //movieCategory: name,
        //     //roomInviteCode: String,
        //     //password: String,
        //     //thumbnailUrl: String,
        // }

        //store.dispatch('root/requestRoomInfo', roomInfo)
            const roomName= document.getElementById("rName");
            const hostId = 10000;
            const keywords = [];//document.getElementById("kTag");
            const limit = 1;//document.getElementById("pNum");
            const bookCategory = 0;
            const movieCategory = 1;
            const url = "";
            const password = "";
            axios({
                method:"POST",
                url: "rooms",
                data:{
                    roomName : roomName.value,
                    hostId: 10001,
                    keywords :this.dynamicTags,
                    limit: this.num,
                    bookCategory: 0,
                    movieCategory: 1,
                    //"roomInviteCode": String,
                    //password: String,
                    url: "src/assets/images/ssafy-logo.png",
                    //"email": email.value,
                    password: "123456",
                }
            }).then((res)=>{
                console.log(res);
            }).catch(error=>{
                console.log("에러 발생ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
                console.log(this.roomName);
                console.log(error);
                throw new Error(error);
            });
        // axios.post('/rooms',roomInfo)
        // .then((response)=>{
        //     console.log()
        //     if(response.data){
        //         alert('방생성');
        //     }else{
        //         alert('실패');
        //     }
        // })
        // .catch((response)=>
        //     console.log(response)
        // );
    },

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

/*openvidu*/
#session-header {
	margin-bottom: 20px;
}

#session-title {
	display: inline-block;
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
