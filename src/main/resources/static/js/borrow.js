let currentPage = 1;
let pageSize = 5;
let totalPages = 1;
function searchBooks() {
    const username = document.getElementById('borrower_name').value;
    const title = document.getElementById('book_name').value;
    const tbody = document.getElementById('borrow_info');

    fetch(`/book/all?username=${encodeURIComponent(username)}&title=${encodeURIComponent(title)}&currentPage=${currentPage}&pageSize=${pageSize}`,
        {
            method: 'GET',
            headers: {
                'Authorization': `${sessionStorage.getItem('token')}`
            }
        }
        )
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            tbody.innerHTML = '';
            data.list.forEach(book => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${book.title}</td>
                    <td>${book.borrowDate ? book.borrowDate : '无'}</td>
                    <td>${book.returnDate ? book.returnDate : '无'}</td>
                    <td>${book.isBorrowed ? '已借阅' : '待借阅'}</td>
                    <td>${book.username ? book.username : '无'}</td>
                    <td>
                        <button onclick="borrowBook('${encodeURIComponent(JSON.stringify(book))}')" ${book.isBorrowed ? 'disabled' : ''}>借阅</button>
                        <button onclick="returnBook('${encodeURIComponent(JSON.stringify(book))}')"}>归还</button>
                    </td>
                `;
                tbody.appendChild(tr);
            });

            const totalRecords = data.total;
            currentPage = data.pageNum;
            totalPages = data.pages;
            //更新页码
            const pageStat = document.getElementById('page_stat');
            pageStat.textContent = `共 ${totalRecords} 条记录,当前第 ${currentPage} / ${totalPages} 页`;

        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}

document.addEventListener('DOMContentLoaded', function () {
    const searchButton = document.getElementById('search');
    searchButton.addEventListener('click', searchBooks);

    // 上一页
    const prevPageButton = document.getElementById('prev_page');
    prevPageButton.addEventListener('click', function () {
        if (currentPage > 1) {
            currentPage--;
            searchBooks();
        }
    });

    // 下一页
    const nextPageButton = document.getElementById('next_page');
    nextPageButton.addEventListener('click', function () {
        currentPage++;
        if (currentPage > totalPages) {
            console.error(currentPage);
            currentPage--;
        }
        searchBooks();
    });

    // 每页记录数
    const pageNoSelect = document.getElementById('page_no');
    pageNoSelect.addEventListener('change', function () {
        pageSize = parseInt(pageNoSelect.value);
        currentPage = 1;
        searchBooks();
    });

    // 跳转页数
    const jumpPageButton = document.getElementById('jump_page');
    const pageJumpInput = document.getElementById('page_jump');
    jumpPageButton.addEventListener('click', function () {
        const pageNo = parseInt(pageJumpInput.value);
        if (Number.isNaN(pageNo) || pageNo < 1) {
            alert("请输入有效的页码");
        } else {
            currentPage = pageNo;
            searchBooks();
        }
    });

    searchBooks();
});

// 借阅图书
function borrowBook(book) {
    fetch(`/book/borrow`, {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
            'Authorization': `${sessionStorage.getItem('token')}`
        },
        body: JSON.stringify(JSON.parse(decodeURIComponent(book)))
    })
        .then(response => {
            if (response.ok) {
                alert('借阅成功');
                searchBooks();
            } else {
                alert('借阅失败');
            }
        })
        .catch(error => {
            console.error('发生错误:', error);
            alert('借阅过程中出错');
        });
}

// 归还图书
function returnBook(book) {
    fetch(`/book/return`, {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
            'Authorization': `${sessionStorage.getItem('token')}`
        },
        body: JSON.stringify(JSON.parse(decodeURIComponent(book)))
    })
        .then(response => {
            if (response.ok) {
                alert('归还成功');
                searchBooks();
            } else {
                alert('归还失败');
            }
        })
        .catch(error => {
            console.error('发生错误:', error);
            alert('归还过程中出错');
        });
}