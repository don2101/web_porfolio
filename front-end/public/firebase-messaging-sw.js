// Import and configure the Firebase SDK
// These scripts are made available when the app is served or deployed on Firebase Hosting




/**
 * Here is is the code snippet to initialize Firebase Messaging in the Service
 * Worker when your app is not hosted on Firebase Hosting.
 // [START initialize_firebase_in_sw]
 // Give the service worker access to Firebase Messaging.
 // Note that you can only use Firebase Messaging here, other Firebase libraries
 // are not available in the service worker.
 **/
 importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
 importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');
 // import * as firebase from "firebase/app";
 // import "firebase/messaging";
 // Initialize the Firebase app in the service worker by passing in the
 // messagingSenderId.
 firebase.initializeApp({
   'messagingSenderId': '881872227111'
 });
 // Retrieve an instance of Firebase Messaging so that it can handle background
 // messages.
 const messaging = firebase.messaging();
 // [END initialize_firebase_in_sw]




// If you would like to customize notifications that are received in the
// background (Web app is closed or not in browser focus) then you should
// implement this optional method.
// [START background_handler]

// self.addEventListener('push', function(event) {
//   const payload = event.data.json();
//   const title = payload.notification.title;
//   const options = {
//     body: payload.notification.body,
//     icon: payload.notification.icon,
//     data: payload.notification.click_action
//   };
//
//   event.waitUntil(self.registration.showNotification(title, options)); });


messaging.setBackgroundMessageHandler(function(payload) {
  console.log('[firebase-messaging-sw.js] Received background message ', payload);
  // Customize notification here
  var notificationTitle = 'Background Message Title';
  var notificationOptions = {
    body: 'Background Message body.',
    icon: '/firebase-logo.png'
  };

  return self.registration.showNotification(notificationTitle,
    notificationOptions);
});
// [END background_handler]
