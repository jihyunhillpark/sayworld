<template>
  <div id="session" v-if="state.session">
    <div id="session-header">
      <h1 id="session-title">{{mySessionId}}</h1>
      <input class="btn btn-large btn-danger" type="checkbox" id="switchBlock" @click="blockUnblock" v-model="block"> 비디오중지
      <input class="btn btn-large btn-danger" type="checkbox" id="switchMute" @click="muteUnmute" v-model="mute"> 음소거
      <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="[deleteRoom(), leaveSession]" value="Leave session">
    </div>
    <!-- <div id="main-video" class="col-md-6">
      <user-video :stream-manager="mainStreamManager"/>
    </div> -->
    <div class="container">
    <div class="messaging">
        <div class="inbox_msg">
          <div class="video_section">
            <div id="video-container" class="col-md-6">
              <user-video :stream-manager="state.publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
              <user-video v-for="sub in state.subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
            </div>
          </div>
          <div class="mesgs">
            <h2>SayWorld</h2>
            <div ref="chatDisplay" class="msg_history">
              <div v-for="(chat,index) in state.chats" :key="index">
                <div v-if="chat.nickname !== userInfo.nickname" class="incoming_msg">
                  <div class="incoming_msg_img">
                    <div class="incoming_msg_img_wrap">
                      <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
                    </div>
                    <span class="participant_name">{{ chat.nickname }} </span>
                  </div>
                  <div class="received_msg">
                    <div class="received_withd_msg">
                      <p>{{ chat.message }}</p>
                      <span class="time_date"> {{ state.recvDate}} </span></div>
                  </div>
                </div>
                <div v-else class="outgoing_msg">
                  <div class="sent_msg">
                    <p>{{ chat.message }}</p>
                    <span class="time_date"> {{ state.nowDate }}       </span> </div>
                </div>
              </div>
            </div>
            <div class="type_msg">
              <div class="input_msg_write">
                <input type="text" v-model="state.sendMsg" class="write_msg" placeholder="메세지를 입력해주세요" @keydown.enter="submitMsg"/>
                <button class="msg_send_btn" type="button"><i class="fa el-icon-s-promotion" aria-hidden="true"></i></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, useStore } from 'vuex'
import { computed, onMounted, reactive } from 'vue'
import UserVideo from '@/components/webrtc/UserVideo'
import { OpenVidu } from 'openvidu-browser';

export default {
  name: 'MeetingRoom',
  components: {
    UserVideo,
  },
  computed: {
    ...mapGetters(['getUserInfo']),
  },
  setup() {
    const store = useStore()
    const state = reactive({
      OV: undefined,
      ovToken: null,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      sendMsg: undefined,
      chats: [],
      recvDate: "",
      nowDate: "",
    })
    const mySessionId = store.state.root.mySessionId
    const userInfo = store.state.root.userInfo

    console.log(userInfo.nickname);
    const block = false
    const mute = false

    const blockUnblock = () => {
      var videoEnabled = block
      state.publisher.publishVideo(videoEnabled)
    }
    const muteUnmute = () => {
      var audioEnabled = mute
      state.publisher.publishAudio(audioEnabled)
    }
    const updateMainVideoStreamManager = (stream) => {
      if (state.mainStreamManager === stream) return;
      state.mainStreamManager = stream;
    }

    const joinSession = () => {
      // --- Get an OpenVidu object ---
      state.OV = new OpenVidu()

      // --- Init a session ---
      state.session = state.OV.initSession()
      // Newly added
      // --- Specify the actions when events take place in the session ---
      // RECEIVE PART = 'chat' 시그널 type을 SUBSCRIBE
      state.session.on('signal:chat', event => {
        state.chats.push(JSON.parse(event.data));
        let date = new Date();
        state.recvDate = date.getHours() + ":" + date.getMinutes();
        setTimeout(this.chat_on_scroll, 10);
      });

      // On every new Stream received...
      state.session.on('streamCreated', ({ stream }) => {
        const subscriber = state.session.subscribe(stream)
        state.subscribers.push(subscriber)
      })

      // On every Stream destroyed...
      state.session.on('streamDestroyed', ({ stream }) => {
        const index = state.subscribers.indexOf(stream.streamManager, 0)
        if (index >= 0) {
          state.subscribers.splice(index, 1)
        }
      })
      // On every asynchronous exception...
      state.session.on('exception', ({ exception }) => {
          console.warn(exception)
      })

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      store.dispatch('root/getToken', mySessionId)
      .then(token => {
        console.log(token)
        state.session.connect(token, { clientData: store.state.root.userInfo.nickname })
        // session.connect(token)
        .then(() => {
          // --- Get your own camera stream with the desired properties ---
          let publisher = state.OV.initPublisher(undefined, {
            audioSource: undefined, // The source of audio. If undefined default microphone
            videoSource: undefined, // The source of video. If undefined default webcam
            publishAudio: true,     // Whether you want to start publishing with your audio unmuted or not
            publishVideo: true,     // Whether you want to start publishing with your video enabled or not
            resolution: '640x480',  // The resolution of your video
            frameRate: 30,         // The frame rate of your video
            insertMode: 'APPEND',   // How the video is inserted in the target element 'video-container'
            mirror: false          // Whether to mirror your local video or not
          })

          state.mainStreamManager = publisher;
          state.publisher = publisher;

          // --- Publish your stream ---
          state.session.publish(publisher)

        })
        .catch(error => {
          console.log('There was an error connecting to the session:', error.code, error.message);
        })
      })

      window.addEventListener('beforeunload', leaveSession)
    }

    const chat_on_scroll = () => {
      this.$refs.chatDisplay.scrollTop = this.$refs.chatDisplay.scrollHeight;
    }

    // Newly added - OpenVidu에 REST API로 채팅관련 전송 요청
    const submitMsg = () => {
      console.log('USER NICKNAME = ', userInfo.nickname);
      if (state.sendMsg.trim === '') return;
      const sendData = {
        userId: userInfo.id,
        // profileImage: this.getUserInfo.profileImage,
        nickname: userInfo.nickname,
        message: state.sendMsg,
      };
      let date = new Date();
      state.nowDate = date.getHours() + ":" + date.getMinutes();
      state.sendMsg = '';
      state.session
        .signal({ // SEND PART = chat 시그널로 data PUBLISH
          data: JSON.stringify(sendData), // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: 'chat', // The type of message (optional)
        })
        .then(() => {
          console.log('Message successfully sent');
        })
        .catch(error => {
          console.error(error);
        });
    }
    const leaveSession = () => {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (state.session) state.session.disconnect()

      state.session = undefined
      state.mainStreamManager = undefined
      state.publisher = undefined
      state.subscribers = []
      state.OV = undefined

      window.removeEventListener('beforeunload', leaveSession);
    }

    onMounted(() => {
      console.log('123', mySessionId)
      // store.dispatch('root/joinSession', mySessionId)
      joinSession()
    })

    return { state, block, mute, mySessionId, userInfo,
    blockUnblock, muteUnmute, updateMainVideoStreamManager, leaveSession, chat_on_scroll, submitMsg}
  }
}
</script>

<style>
.container{max-width:1170px; margin:auto;}
img{ max-width:100%;}
.video_section {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 70%; border-right:1px solid #c4c4c4;
  box-sizing: border-box;
}
.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 40%; border-right:1px solid #c4c4c4;
}
.inbox_msg {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}
.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:40%;}
.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}
.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}
.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}

.incoming_msg_img {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
  text-align: left;
}
.incoming_msg_img_wrap {
  float: left;
  width: 7%;
}
.incoming_msg_img > .participant_name{
  margin-left: 10px;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
  text-align: left;
 }
 .received_withd_msg p {
  /* background: #ebebeb none repeat scroll 0 0; */
  background: #252C34 none repeat scroll 0 0;
  border-radius: 10px;
  /* color: #646464; */
  color: #c9c9c9;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
  word-break: break-all;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 7px;
  margin: 0 0 0 10px;
}
.received_withd_msg { width: 57%;}
.mesgs {
  float: left;
  /* padding: 30px 15px 0 25px; */
  width: 30%;
  box-sizing: border-box;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 10px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  /* width:100%; */
  word-break: break-all;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  margin-right: 10px;
  float: right;
  width: 46%;
  text-align: right;
}
.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  /* background-color: whitesmoke; */
  background-color: #1C2128;
  height: 400px;
  overflow-y: scroll;
}
</style>
