importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');

firebase.initializeApp({
  'messagingSenderId': '881872227111'
});

const messaging = firebase.messaging();

self.addEventListener('push', function(event) {
  const payload = event.data.json();

  const title = payload.notification.title;
  const options = {
    body: payload.notification.body,
    icon: payload.notification.icon,
    data: payload.data
  };


  event.waitUntil(self.registration.showNotification(title, options));
});

self.addEventListener('notificationclick', function(event) {

  event.notification.close();

  event.waitUntil(
    clients.openWindow(event.notification.data["gcm.notification.data"])
  );
});
