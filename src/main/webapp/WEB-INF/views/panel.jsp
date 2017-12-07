<!DOCTYPE HTML>
<html>

<head>
  <meta charset="UTF-8" lang="es">
  <title>Class</title>
  <link rel="stylesheet" href="../resources/css/classstyle.css" type="text/css"/>
</head>
<body ng-app="classApp" width="100%" height="100%" ng-controller="boardController">
 
  <canvas id="canvas"></canvas>
  <div id="tools-div">
      <div class="tool" ng-click="selectTool('pencil')">pencil</div>
      <div class="tool" ng-click="selectTool('erase')">erase</div>
      <button id="clear">Clear</button>
  </div>
  <div ng-controller="videoController"> 
    <video id="remoteVideo" ng-click="openCloseVideo()" autoplay >remote video</video>
    <video id="localVideo" autoplay muted ></video>
    <div id="videoButtons" ng-click="openCloseVideo()"></div>
  </div>

  <script type="text/javascript" src="../webjars/jquery/3.2.1/jquery.js"></script>
  <script type="text/javascript" src="../webjars/sockjs-client/1.1.4/dist/sockjs.js"></script>
  <script type="text/javascript" src="../webjars/stomp-websocket/2.3.4/lib/stomp.js"></script>
  <script type="text/javascript" src="../webjars/angular/1.6.7-1/angular.js"></script>

  <script src="../resources/js/classsource.js" type="text/javascript"></script>
      
</body>