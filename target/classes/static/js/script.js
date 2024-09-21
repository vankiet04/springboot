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

document.querySelector("form").addEventListener("submit", function(e) {
    if (!validateForm()) {
        e.preventDefault();
    }
});

function validateForm() {

    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmpassword").value;
    if(password==="" && confirmPassword===""){
        return true;
    }

    if(password==="" || confirmPassword===""){
        alert("Mật khẩu không được để trống");
        return false;
    }

    if (password !== confirmPassword) {
        alert("Mật khẩu không khớp");
        return false ;
    }

    else {
        document.getElementById("isPasswordChanged").value = "true";
    }

    // Set the value of the password field in the user object
    const passwordInput = document.querySelector(".password-content input[name='password']");
    passwordInput.setAttribute("th:field", "*{password}");
    document.querySelector("input[name='password']").value = password;
    return true;
}