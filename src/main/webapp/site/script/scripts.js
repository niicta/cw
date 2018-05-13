$('document').ready(function(){
    //setUserName();
    $('.login-welcome-button').click(function(){
        showLoginForm();
    });

    $('.cancel-login-button').click(function(){
        showMainForm();
    })

    $('.login-button').click(function () {
        login();
    })
});


function setUserName() {
    $.ajax({
            type : "POST",
            url : "userJson",
            success : function (user) {
                $('.user-name-text').text(user['user-name']);
            }
        }
    )
}

function showLoginForm(){
    $('.welcome-block').addClass('hidden');
    $('.login-block').removeClass('hidden');
}

function showMainForm(){
    $('.welcome-block').removeClass('hidden');
    $('.login-block').addClass('hidden');
    $('.sing-up-block').addClass('hidden');
}

function login(){
    var login = $('.login').val();
    var password = $('.password').val();
    $.ajax({
            type : "POST",
            url : "loginJson",
            data: {
                'login': login,
                'password' : password
            },
            success : function (loginResult) {
                performLoginResult(loginResult);
            }
        }
    )
}

function performLoginResult(loginResult){
    alert(loginResult);
}