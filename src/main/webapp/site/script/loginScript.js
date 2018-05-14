$('document').ready(function(){
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