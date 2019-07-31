//쿠키 관련 서비

export default {

    setCookie(cookie_name, value, days){

      let exdate = new Date();

      exdate.setDate(exdate.getDate() + days);
      var cookie_value = escape(value) + ((days == null) ? '' : ';    expires=' + exdate.toUTCString());
      document.cookie = cookie_name + '=' + cookie_value;

    },

    getCookie(cookie_name){
      let x, y;
      let val = document.cookie.split(';');
      for(let i =0 ; i< val.length; i++){
        x = val[i].substr(0,val[i].indexOf('='));
        y = val[i].substr(val[i].indexOf('=')+1);
        x = x.replace(/^\s+|\s+$/g, '');
        if (x == cookie_name) {
           return unescape(y); // unescape로 디코딩 후 값 리턴
         }
      }
    },

    addCookie(id) {
      var items = this.getCookie('alarm_granted'); // 이미 저장된 값을 쿠키에서 가져오기
      
      var maxItemNum = 5; // 최대 저장 가능한 아이템개수
      var expire = 1; // 쿠키값을 저장할 기간
      if (items) {
        var itemArray = items.split(',');
        if (itemArray.indexOf(id) != -1) {
          // 이미 존재하는 경우 종료
          console.log('Already exists.');
        }
        else {
          // 새로운 값 저장 및 최대 개수 유지하기
          itemArray.unshift(id);
          if (itemArray.length > maxItemNum ) itemArray.length = 5;
          items = itemArray.join(',');
          this.setCookie('alarm_granted', items, expire);
        }
      }
      else {
        // 신규 id값 저장하기
        this.setCookie('alarm_granted', id, expire);
      }
    },
}
