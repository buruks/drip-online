$(document).ready(function() {
    $("#zipCode").keyup(function(e){
        $('.city').text('');

        var zipCode = $(this).val();

        if(zipCode.length == 5 && $.isNumeric(zipCode)){
            var result = getCityAndState(zipCode);
            $('.city').html(result); 
            
        }
    })
});

function getCityAndState(zipCode){
    var requestURL = 'http://ziptasticapi.com/' + zipCode + '?callback=?';

    var result = '';

    $.getJSON(requestURL, null, function(data){
        if(data.city){
            result = '<span style="color:green;">'+data.city + ', '+ data.state+'</span>';
        }else{
           result = '<span style="color:red;">'+data.error+'</span>'; 
        }
    })

    return result;
}
