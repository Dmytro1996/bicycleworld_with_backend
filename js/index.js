document.querySelector('.login-form input[type=submit]').addEventListener('click',login);
function login(e) {
  e.preventDefault();
  fetch('login', {
      method:'POST',
      headers: {
          'Content-Type':'application/json',
      },
      body:JSON.stringify({
          name:document.querySelector('.login-form input[name=subscriberName]').value,
          email:document.querySelector('.login-form input[name=subscriberEmail]').value
          })
          })
      .then(_=> document.querySelector('.login-form').reset());
}