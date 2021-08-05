<template>
<router-view></router-view>
<el-button type="primary" icon="el-icon-folder-add" @click="dialogFormVisible = true" circle></el-button>
<el-dialog title="화상채팅방 생성" v-model="dialogFormVisible">
    <el-form :model="form">
    <el-form-item prop="roomName" label="방 이름" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item prop="keyword" label="키워드" :label-width="formLabelWidth">
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
    <el-form-item prop="roomName" label="인원" :label-width="formLabelWidth">
        <el-input-number v-model="num" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
    </el-form-item>
    <el-form-item prop="roomTheme" label="테마" :label-width="formLabelWidth">
        <el-radio v-model="radio" label="book">Book</el-radio>
        <el-radio v-model="radio" label="movie">Movie</el-radio>
    </el-form-item>
    <el-form-item prop="roomLock" label="방" :label-width="formLabelWidth">
        <el-row>
        <el-checkbox v-model="checked" @change="handleCheckbox">잠금</el-checkbox>
        <el-input v-show="isLocked" placeholder="비밀번호를 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호를 입력하세요.'"></el-input>
        </el-row>
    </el-form-item>
    <el-form-item>
        <!--<input type="file" id="file" ref="files" @change="imageUpload" multiple />-->
    </el-form-item>
    <div class="image-box">
        <label for="file">채팅방 사진 등록</label>
        <input type="file" id="file" ref="files" @change="imageUpload" multiple />
    </div>
    </el-form>
    <template #footer>
    <span class="dialog-footer">
        <el-button type="primary" @click="[dialogFormVisible = false,joinSession()]" >생성</el-button>
        <el-button @click="dialogFormVisible = false">취소</el-button>
    </span>
    </template>
</el-dialog>
    <div id="session" v-if="session">
        <div id="session-header">
            <h1 id="session-title">{{ mySessionId }}</h1>
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

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
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
        checked: false,
        radio: 'book',
        num: 1,
        dialogFormVisible: false,
        form: {
            name: ''
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
    };
    },
    methods: {
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
    handleCheckbox(value) {
        this.isLocked= !this.isLocked;
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
                        publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                        publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                        resolution: '640x480',  // The resolution of your video
                        frameRate: 30,			// The frame rate of your video
                        insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                        mirror: false       	// Whether to mirror your local video or not
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
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
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
</style>