$('document').ready(function(){
    $('.new-template-button').click(function(){
        showNewTemplateForm();
    });

    $('.new-visit-button').click(function(){
        var orderId = $(this).attr('id');
        showNewVisitForm(orderId);
    });

    $('.delete-visit-button').click(function () {
       var visitId =  $(this).attr('data-visit-id');
       deleteVisit(visitId);
    });

    $('.new-room-button').click(function(){
        showNewRoomForm();
    });

    $('.new-space-button').click(function(){
        createNewSpace();
    });

    $('.submit-template-button').click(function () {
        var templateId = $(this).attr('data-template-id');
        createOrder(templateId);
    })
});

function deleteVisit(visitId) {
    $.ajax({
            type : "POST",
            url : "deleteVisitJson",
            data: {
                'visit-id': visitId
            },
            success : function () {
                location.reload();
            }
        }
    )
}

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

function showNewVisitForm(orderId){
    setTimeout(function(){
        $(".create-visit-form-block").fadeTo(0, 0).css('display', 'flex').fadeTo(0, 1);
        $('.cancel-create-visit-button').click(function(){
            closeNewVisitForm();
        });
        $('.submit-create-visit-button').click(function () {
            var visitDay = $('.visit-day').val();
            var visitMonth = $('.visit-month').val();
            var visitYear = $('.visit-year').val();
            var visitStartHour = $('.visit-start-hour').val();
            var visitEndHour = $('.visit-end-hour').val();
            $.ajax({
                    type : "POST",
                    url : "createVisitJson",
                    data: {
                        "start-year" : visitYear,
                        "end-year" : visitYear,
                        "start-month" : visitMonth,
                        "end-month" : visitMonth,
                        "start-day" : visitDay,
                        "end-day" : visitDay,
                        "start-hour" : visitStartHour,
                        "end-hour" : visitEndHour,
                        "order-id" : orderId
                    },
                    success : function (result) {
                        performCreateVisitResult(result);
                    }
                }
            )
         })

    }, 300);
}

function performCreateVisitResult(result) {
    if ('error-name' in result){
        showErrorForm(result['error-reason'])
    }
    else {
        location.reload();
    }
}
function showErrorForm(message) {
    setTimeout(function() {
        $(".error-form-block").fadeTo(0, 0).css('display', 'flex').fadeTo(0, 1);
        $(".error-text").empty().text(message)
        $('.submit-error-button').click(function () {
            closeErrorForm();
        });
    })
}
function closeErrorForm() {
    $(".error-form-block").fadeTo(0.3, 0);
    setTimeout(function(){$(".error-form-block").hide()}, 300);
}
function closeNewVisitForm(){
    $(".create-visit-form-block").fadeTo(0.3, 0);
    setTimeout(function(){$(".create-visit-form-block").hide()}, 300);
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
                        'count-of-places' : 1
                    },
                    success : function () {
                        location.reload();
                    }
                }
            )

    }, 300);
}

function createOrder(templateId) {
    setTimeout(function(){
        $.ajax({
                type : "POST",
                url : "createOrderJson",
                data: {
                    'template-id': templateId,
                },
                success : function () {
                    window.location.href = 'visits';
                }
            }
        )

    }, 300);
}

