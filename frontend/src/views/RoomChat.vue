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
    <div id="video-container" class="col-md-6">
      <user-video :stream-manager="state.publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
      <user-video v-for="sub in state.subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
    </div>
    <div class="chat-box">
      <div ref="chatDisplay" class="chat-display">
        <div v-for="(chat, index) in state.chats" :key="index" class="chat-line">
          <div v-if="chat.userId === userInfo.id" class="my-comment">
            <!-- <img :src="chat.profileImage" class="user-profile" /> -->
            <div>
              <span class="participant-name">{{ chat.nickname }} </span><span class="chat-msg">{{ chat.message }}</span>
            </div>
          </div>
          <div v-else class="other-comment">
            <!-- <img :src="chat.profileImage" class="user-profile" /> -->
            <div>
              <span class="participant-name other">{{ chat.nickname }} </span
              ><span class="chat-msg">{{ chat.message }}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="msg-wrapper">
        <div class="msg-guide">
          <!-- <img :src="getUserInfo.profileImage" class="user-profile" /> -->
          {{ userInfo.nickname }}
        </div>
        <input
          v-model="state.sendMsg"
          type="text"
          class="msg-input"
          placeholder="메세지를 입력해주세요"
          @keydown.enter="submitMsg"
        />
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
    })
    const mySessionId = store.state.root.mySessionId
    const userInfo = store.state.root.userInfo

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
</style>
