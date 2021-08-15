<template>
  <div id="session" v-if="state.session">
    <div id="session-header">
      <h1 id="session-title">{{$route.params.roomName}}</h1>
      <div>
        <el-checkbox v-model="state.block" @click="blockUnblock()">비디오중지</el-checkbox>
        <el-checkbox v-model="state.mute" @click="muteUnmute()">음소거</el-checkbox>
      </div>
        <el-button type="primary" size="small"  @click="[leaveSession()]">나가기</el-button>
      <!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="[leaveSession()]" value="Leave session"> -->
    </div>
    <!-- <div id="main-video" class="col-md-6">
      <user-video :stream-manager="mainStreamManager"/>
    </div> -->
    <div id="video-container" class="col-md-6">
      <user-video :stream-manager="state.publisher" @click.native="updateMainVideoStreamManager(publisher)"/>
      <user-video v-for="sub in state.subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import { useRouter } from "vue-router"
import { computed, onMounted, reactive } from 'vue'
import UserVideo from '@/components/webrtc/UserVideo'
import { OpenVidu } from 'openvidu-browser';

export default {
  name: 'MeetingRoom',
  components: {
    UserVideo,
  },
  setup() {
    const store = useStore()
    const route = useRoute()
    const router = useRouter()
    const state = reactive({
      OV: undefined,
      ovToken: null,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      block: 'false',
      mute: 'false',
    })
    // const mySessionId = store.state.root.mySessionId
    // const mySessionId = computed(() => route.params.roomName)
    const mySessionId = route.params.roomName

    const blockUnblock = () => {
      var videoEnabled = state.block
      state.publisher.publishVideo(videoEnabled)
    }
    const muteUnmute = () => {
      var audioEnabled = state.mute
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
      // --- Specify the actions when events take place in the session ---

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

    const leaveSession = (e) => {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      console.log("leaveRoom");
      if(!window.confirm("정말 나가시겠습니까?")) {
      	e.preventDefault();
      }
      if (state.session) state.session.disconnect()

      delete state.session;
      delete state.OV;
      delete state.publisher;
      state.subscribers = [];

      router.push({ name: "Home" })
      // if (state.session) state.session.disconnect()

      // delete state.session;
      // delete state.OV;
      // delete state.publisher;
      // state.subscribers = [];


      // state.session = undefined
      // state.mainStreamManager = undefined
      // state.publisher = undefined
      // state.subscribers = []
      // state.OV = undefined

      window.removeEventListener('beforeunload', leaveSession);
    }

    onMounted(() => {
      console.log('123', mySessionId)
      console.log(route.params.roomName)
      // mySessionId = route.params.roomName
      store.dispatch('root/joinSession', mySessionId)
      joinSession()
    })

    return { state, mySessionId, blockUnblock, muteUnmute, updateMainVideoStreamManager, leaveSession }
  }
}
</script>

<style>

</style>
