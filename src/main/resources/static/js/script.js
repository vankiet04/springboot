const bar = document.getElementById('bar');
const close = document.getElementById('close');
const nav = document.getElementById('navbar');

if (bar) {
    bar.addEventListener('click', () => {
        nav.classList.add('active');
    });
}

if (close) {
    close.addEventListener('click', () => {
        nav.classList.remove('active');
    });
}

document.getElementById("changepassword").addEventListener("click", function(e) {
    e.preventDefault();
    var passwordContent = document.querySelector(".password-content");
    if (passwordContent.style.display === "none" || passwordContent.style.display === "") {
        passwordContent.style.display = "block"; // Hiển thị password-content
    } else {
        passwordContent.style.display = "none"; // Ẩn lại nếu cần
    }
});