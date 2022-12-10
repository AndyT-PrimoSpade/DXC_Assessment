// Create a function to change
// the hash value of the page
function changeLanguage(lang) {
    location.hash = lang;
    location.reload();
}

var language = {
    en: {
        welcome : "Welcome to Assessment Site",
        username : "Username: ",
        name : "Name: ",
        role : "Role: "
    },
    cn: {
        welcome : "欢迎来到评估网站",
        username : "用户名: ",
        name : "姓名: ",
        role : "角色: "
    }
};

if (window.location.hash) {

    if (window.location.hash == "#cn") {
        lang_welcome.textContent =
            language.cn.welcome;
        lang_username.textContent =
            language.cn.username;
        lang_name.textContent =
            language.cn.name;
        lang_role.textContent =
            language.cn.role;
    }
}