document.getElementById('loginForm').onsubmit = function(event) {
    event.preventDefault();
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    fetch('/index/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username: username, password: password })
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('登录失败');
            }
        })
        .then(userId => {
            // 将用户ID存储到localStorage中
            localStorage.setItem('userId', userId);
            alert('登录成功');
            window.location.href = './borrow.html';
        })
        .catch(error => {
            console.error('错误:', error);
            alert('登录失败');
        });
};