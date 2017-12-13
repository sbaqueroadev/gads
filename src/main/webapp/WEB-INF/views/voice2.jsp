<html><head><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>capture microphone audio into buffer</title>


<script type="text/javascript" src="../webjars/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src="../webjars/sockjs-client/1.1.4/dist/sockjs.js"></script>
<script type="text/javascript" src="../webjars/stomp-websocket/2.3.4/lib/stomp.js"></script>


<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>
<!--<script src="webrtc.js"></script>-->



<script type="text/javascript">


/*if (navigator.mediaDevices) {
    var constrains = {audio: true,video:true};

    navigator.mediaDevices.getUserMedia(constrains).then(
        function (stream) {
            video.srcObject = stream;
            video.onloadedmetadata = function(e){
              video.play();
            }
            var context = new AudioContext();
            var source = context.createMediaStreamSource(stream);
            var biquadFilter = audioCtx.createBiquadFilter();
            biquadFilter.type = "lowshelf";
            biquadFilter.frequency.value = 1000;
            biquadFilter.gain.value = 0.5;
            source.connect(biquadFilter);
            biquadFilter.connect(context.destination);


        }
    ).catch( (error) => {
        console.error("couldn't get user media.");
    });
}*/


$(function (){

  var stompClient = null;

  function connect() {
      var socket = new SockJS('http://localhost:8081/GASD/write');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
          //setConnected(true);
          console.log('Connected: ' + frame);
          stompClient.subscribe('/topic/reading', function (data) {
             console.log(data.body);
          });
          sendName();
      });
  }

  function disconnect() {
      if (stompClient !== null) {
          stompClient.disconnect();
      }
      setConnected(false);
      console.log("Disconnected");
  }

  function sendName() {
      stompClient.send("/chat/write", {}, JSON.stringify({'name': "sergio"}));
  }

  connect();

});



/*******************************WEBRTC EXAM;PLE .js ************************************/


  var localVideo;
  var remoteVideo;
  var peerConnection;
  var uuid;
  var sockjsVideo;

  var peerConnectionConfig = {
      'iceServers': [
          {'urls': 'stun:stun.services.mozilla.com'},
          {'urls': 'stun:stun.l.google.com:19302'},
      ]
  };

  function pageReady() {
      uuid = uuid();

      localVideo = document.getElementById('localVideo');
      remoteVideo = document.getElementById('remoteVideo');

      sockjsVideo = new SockJS('http://localhost:8081/GASD/show');
      serverConnection = Stomp.over(sockjsVideo);//new WebSocket('wss://' + window.location.hostname + ':8081');
      serverConnection.connect({}, function (frame) {
          //setConnected(true);
          console.log('Connected: ' + frame);
          serverConnection.subscribe('/topic/view', function (data) {
             console.log(data.body);
          });
      });

      serverConnection.onmessage = gotMessageFromServer;

      var constraints = {
          video: true,
          audio: true,
      };

      if(navigator.mediaDevices.getUserMedia) {
          navigator.mediaDevices.getUserMedia(constraints).then(getUserMediaSuccess).catch(errorHandler);
      } else {
          alert('Your browser does not support getUserMedia API');
      }
  }

  function getUserMediaSuccess(stream) {
      localStream = stream;
      localVideo.srcObject = stream;
  }

  function start(isCaller) {
      peerConnection = new RTCPeerConnection(peerConnectionConfig);
      peerConnection.onicecandidate = gotIceCandidate;
      peerConnection.onaddstream = gotRemoteStream;
      peerConnection.addStream(localStream);

      if(isCaller) {
          peerConnection.createOffer().then(createdDescription).catch(errorHandler);
      }
  }

  function gotMessageFromServer(message) {
      if(!peerConnection) start(false);

      var signal = JSON.parse(message.data);

      // Ignore messages from ourself
      if(signal.uuid == uuid) return;

      if(signal.sdp) {
          peerConnection.setRemoteDescription(new RTCSessionDescription(signal.sdp)).then(function() {
              // Only create answers in response to offers
              if(signal.sdp.type == 'offer') {
                  peerConnection.createAnswer().then(createdDescription).catch(errorHandler);
              }
          }).catch(errorHandler);
      } else if(signal.ice) {
          peerConnection.addIceCandidate(new RTCIceCandidate(signal.ice)).catch(errorHandler);
      }
  }

  function gotIceCandidate(event) {
      if(event.candidate != null) {
          serverConnection.send("/video/show", {},JSON.stringify({'ice': event.candidate, 'uuid': uuid}));
      }
  }

  function createdDescription(description) {
      console.log('got description');

      peerConnection.setLocalDescription(description).then(function() {
          serverConnection.send("/video/show", {},JSON.stringify({'sdp': peerConnection.localDescription, 'uuid': uuid}));
      }).catch(errorHandler);
  }

  function gotRemoteStream(event) {
      console.log('got remote stream');
      remoteVideo.srcObject = event.stream;
  }

  function errorHandler(error) {
      console.log(error);
  }

  // Taken from http://stackoverflow.com/a/105074/515584
  // Strictly speaking, it's not a real UUID, but it gets the job done here
  function uuid() {
    function s4() {
      return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
    }

    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
  }

/******************************************************************************************/


</script>

</head>
 <body>
        <video id="localVideo" autoplay muted style="width:40%;"></video>
        <video id="remoteVideo" autoplay style="width:40%;">remote video</video>

        <br />

        <input type="button" id="start" onclick="start(true);" value="Start Video"></input>

        <script type="text/javascript">
            pageReady();
        </script>
    </body>
</html>