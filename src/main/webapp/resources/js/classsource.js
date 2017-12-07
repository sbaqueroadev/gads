
var classApp = angular.module('classApp',[]);
/************************BOARD***************************************/
const canvas = document.querySelector('#canvas');
const ctx = canvas.getContext('2d');
const clearButton = document.querySelector('#clear');
const localVideo = document.getElementById('localVideo');
const remoteVideo = document.getElementById('remoteVideo');
const videoButtons = document.getElementById('videoButtons');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;


classApp.controller('boardController',function($scope){
  $scope.stompClient = null;
  $scope.connect = function() {
       $scope.socket = new SockJS('http://localhost:8081/GASD/boardUpdate');
        $scope.stompClient = Stomp.over($scope.socket);
        $scope.stompClient.connect({}, function (frame) {
            //setConnected(true);
            console.log('Connected: ' + frame);
            $scope.stompClient.subscribe('/topic/updatingBoard', function (data) {
              console.log(data.body);
              data = JSON.parse(data.body);
              switch(data.type){
                case 'line':
                  ctx.lineWidth = data.width;
                  ctx.strokeStyle = data.color;
                  for(var i =1; i< data.points.length;i++){
                    ctx.beginPath();
                    ctx.moveTo(data.points[i-1][0], data.points[i-1][1]);
                    ctx.lineTo(data.points[i][0], data.points[i][1]);
                    ctx.stroke();
                  }
                break;
                case 'clear':
                  ctx.clearRect(0, 0, canvas.width, canvas.height);
                break;
                case 'cartesian':

                break;
              }
              
            });
        });
    };

    $scope.isconnect = function() {
        if ($scope.stompClient !== null) {
            $scope.stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    };

    $scope.sendInfo = function(data) {
        $scope.stompClient.send("/board/boardUpdate", {}, JSON.stringify(data));
    };

    
    ctx.strokeStyle = 'orange';
    ctx.lineCap = 'round';
    ctx.lineJoin = 'round';

    $scope.isDrawing = false;
    $scope.lastX = 0;
    $scope.lastY = 0;
    $scope.hue = 200;
    $scope.lineWidth = 10;
    $scope.lightness = '50%';
    ctx.globalCompositeOperation = 'normal';

    $scope.draw = function(e) {
      if (!$scope.isDrawing) {
        return;
      }

      ctx.strokeStyle = $scope.getColor();
      ctx.lineWidth = $scope.getlineWidth();

      ctx.beginPath();
      ctx.moveTo($scope.lastX, $scope.lastY);
      ctx.lineTo(e.offsetX, e.offsetY);
      ctx.stroke();
      if($scope.points)
        $scope.points[$scope.points.length] = [$scope.lastX,$scope.lastY];
      [$scope.lastX, $scope.lastY] = [e.offsetX, e.offsetY];

    };


    $scope.sendCanvas = function(e) {
      $scope.isDrawing = false;
      var dataURL = {type:'line',color:$scope.getColor(),width:$scope.getlineWidth(),points:$scope.points};
      $scope.sendInfo(dataURL);
      $scope.points = [];
    }


    $scope.getColor = function() {
      return 'hsl(' + $scope.hue +', 100%, '+$scope.lightness+')';
    }

    $scope.getlineWidth = function() {
      return $scope.lineWidth;
    }

    $scope.points = [];

    canvas.addEventListener('mousemove', $scope.draw);
    canvas.addEventListener('mousedown', (e) => {
      $scope.isDrawing = true;
      [$scope.lastX, $scope.lastY] = [e.offsetX, e.offsetY];
    });
    canvas.addEventListener('mouseup', $scope.sendCanvas);
    canvas.addEventListener('mouseout', () => $scope.isDrawing = false );

    $scope.clear = function() {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      $scope.sendInfo({type:'clear'});
    };

    clearButton.addEventListener('click', $scope.clear);
    $scope.connect();

    $('.tool').on('click',$scope.selectTool);

    $scope.selectTool = function(tool){
        switch(tool){
            case 'pencil':
                $scope.lineWidth = 5;
                $scope.hue = 0;
                $scope.lightness = '50%';
            break;
            case 'erase':
                $scope.lineWidth = 20;
                $scope.lightness = '100%';
            break;
        }
    };
}); 
/****************************************************************/
/*************************VIDEO**********************************/

classApp.controller('videoController',function($scope){
  $scope.peerConnection;
  $scope.uuid;
  $scope.sockjsVideo;
  $scope.localStream;

  $scope.peerConnectionConfig = {
      'iceServers': [
          {'urls': 'stun:stun.services.mozilla.com'},
          {'urls': 'stun:stun.l.google.com:19302'},
      ]
  };

  $scope.pageReady = function () {
      $scope.uuid = $scope.uuidF();
      $scope.sockjsVideo = new SockJS('http://localhost:8081/GASD/show');
      $scope.serverConnection = Stomp.over($scope.sockjsVideo);//new WebSocket('wss://' + window.location.hostname + ':8081');
      $scope.serverConnection.connect({}, function (frame) {
          //setConnected(true);
          console.log('Connected: ' + frame);
          $scope.serverConnection.subscribe('/topic/view', function (data) {
             gotMessageFromServer(data.body);
          });
      });

      $scope.constraints = {
          video: true,
          audio: true,
      };

      if(navigator.mediaDevices.getUserMedia) {
          navigator.mediaDevices.getUserMedia($scope.constraints).then($scope.getUserMediaSuccess).catch($scope.errorHandler);
      } else {
          alert('Your browser does not support getUserMedia API');
      }
  };

  $scope.getUserMediaSuccess = function (stream) {
      $scope.localStream = stream;
      localVideo.srcObject = stream;
      $scope.start(true);
  };

 $scope.start = function (isCaller) {
      $scope.peerConnection = new RTCPeerConnection($scope.peerConnectionConfig);
      $scope.peerConnection.onicecandidate = $scope.gotIceCandidate;
      $scope.peerConnection.onaddstream = $scope.gotRemoteStream;
      $scope.peerConnection.addStream($scope.localStream);

      if(isCaller) {
          $scope.peerConnection.createOffer().then($scope.createdDescription).catch($scope.errorHandler);
      }
  };

  $scope.gotMessageFromServer = function(message) {
      if(!peerConnection) start(false);

      var signal = JSON.parse(message.data);

      // Ignore messages from ourself
      if(signal.uuid == $scope.uuid) return;

      if(signal.sdp) {
          $scope.peerConnection.setRemoteDescription(new RTCSessionDescription(signal.sdp)).then(function() {
              // Only create answers in response to offers
              if(signal.sdp.type == 'offer') {
                  $scope.peerConnection.createAnswer().then($scope.createdDescription).catch($scope.errorHandler);
              }
          }).catch($scope.errorHandler);
      } else if(signal.ice) {
          $scope.peerConnection.addIceCandidate(new RTCIceCandidate(signal.ice)).catch($scope.errorHandler);
      }
  };

  $scope.gotIceCandidate = function(event) {
      if(event.candidate != null) {
          $scope.serverConnection.send("/video/show", {},JSON.stringify({'ice': event.candidate, 'uuid': $scope.uuid}));
      }
  };

  $scope.createdDescription = function (description) {
      console.log('got description');

      $scope.peerConnection.setLocalDescription(description).then(function() {
          $scope.serverConnection.send("/video/show", {},JSON.stringify({'sdp': $scope.peerConnection.localDescription, 'uuid': $scope.uuid}));
      }).catch($scope.errorHandler);
  };

  $scope.gotRemoteStream = function(event) {
      console.log('got remote stream');
      remoteVideo.srcObject = event.stream;
  };

  $scope.errorHandler = function(error) {
      console.log(error);
  };

  // Taken from http://stackoverflow.com/a/105074/515584
  // Strictly speaking, it's not a real UUID, but it gets the job done here
  $scope.uuidF = function() {
    function s4() {
      return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
    }

    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
  };

  $scope.openCloseVideo = function(){
    var newWidth = $(remoteVideo).width()>100?100:500;
    //var newOpacity = $(remoteVideo).width()>100?[1.0,0.0]:[0.0,1.0];
    $(remoteVideo).animate({
      width:newWidth//,
      //opacity:newOpacity[1]
    },1000);
    /*$(videoButtons).animate({
      opacity:newOpacity[0]
    },2000);*/
  };

  $scope.pageReady();
});

/******************************************************************************************/
