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
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                sessionStorage.setItem('token', data.content);
                alert('登录成功');
                window.location.href = './borrow.html';
            } else {
                alert(`登录失败: ${data.message}`);
            }
        })
        .catch(error => {
            console.error('错误:', error);
            alert('登录失败');
        });
};