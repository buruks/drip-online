//ziptastic provides an API for retrieving city and state using the zipcode, and vice versa
function getCityAndState(selector,zipCode){
    var requestURL = 'http://ziptasticapi.com/' + zipCode + '?callback=?';

    $.ajax({
      url: requestURL,
      dataType: 'json',
      success: function (data) {
        if(data.city){
            $(selector).html('<span style="color:green;"><strong>'+data.city + ', '+ data.state+'</strong></span>');
        }else{
           $(selector).html('<span style="color:red;"><strong>'+data.error+'</strong></span>'); 
        }
      }
    });
}

$(document).ready(function() {
    $("#zipCode").keyup(function(e){
        
        var zipCode = $(this).val();

        $('.city').text('');

        if(zipCode.length == 5 && $.isNumeric(zipCode)){
            getCityAndState('.city',zipCode);
        }
    })
});


