$('document').ready(function(){
    $('.new-template-button').click(function(){
        showNewTemplateForm();
    });

    $('.new-room-button').click(function(){
        showNewRoomForm();
    });

    $('.new-space-button').click(function(){
        createNewSpace();
    })
});

function showNewTemplateForm(){
    setTimeout(function(){
        $(".create-template-form-block").fadeTo(0, 0).css('display', 'flex').fadeTo(0, 1);
        $('.cancel-create-template-button').click(function(){
            closeNewTemplateForm();
        });
        $('.submit-create-template-button').click(function () {
            var templateName = $('.template-name').val();
            var spaceType = $('.space-type').is(':checked') ? 0 :1;
            var fullWeek = $('.full-week').is(':checked');
            var fixed = $('.fixed-place').is(':checked');
            var countOfPlaces = $('.count-of-places').val();
            var basePrice = $('.base-price').val();
            $.ajax({
                    type : "POST",
                    url : "createTemplateJson",
                    data: {
                        'space-type': spaceType,
                        'fixed' : fixed,
                        'full-week': fullWeek ,
                        'count-of-places' : countOfPlaces,
                        "base-price" : basePrice,
                        'name' : templateName
                    },
                    success : function () {
                        location.reload();
                    }
                }
            )
        })

    }, 300);
}

function closeNewTemplateForm(){
    $(".create-template-form-block").fadeTo(0.3, 0);
    setTimeout(function(){$(".create-template-form-block").hide()}, 300);
}

function showNewRoomForm(){
    setTimeout(function(){
        $(".create-room-form-block").fadeTo(0, 0).css('display', 'flex').fadeTo(0, 1);
        $('.cancel-create-room-button').click(function(){
            closeNewRoomForm();
        });
        $('.submit-create-room-button').click(function () {
            var countOfPlaces = $('.room-count').val();
            var spaceType = 0;
            $.ajax({
                    type : "POST",
                    url : "createSpaceJson",
                    data: {
                        'space-type': spaceType,
                        'count-of-places' : countOfPlaces,
                    },
                    success : function () {
                        location.reload();
                    }
                }
            )

        })

    }, 300);
}

function closeNewRoomForm(){
    $(".create-room-form-block").fadeTo(0.3, 0);
    setTimeout(function(){$(".create-room-form-block").hide()}, 300);
}

function createNewSpace(){
    setTimeout(function(){
            $.ajax({
                    type : "POST",
                    url : "createSpaceJson",
                    data: {
                        'space-type': 1,
                        'count-of-places' : 1,
                    },
                    success : function () {
                        location.reload();
                    }
                }
            )

    }, 300);
}

