document.getElementById('loginForm').onsubmit = function(event) {
    event.preventDefault();
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var role = document.querySelector('input[name="role"]:checked').value;

    fetch('/index/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username: username, password: password, role: role })
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('登录失败');
            }
        })
        .then(token => {
            sessionStorage.setItem('token', token);
            alert('登录成功');
            window.location.href = './borrow.html';
        })
        .catch(error => {
            console.error('错误:', error);
            alert('登录失败');
        });
};