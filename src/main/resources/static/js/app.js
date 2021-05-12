$(document).ready(function(){
alert("working");
  function validatePassword(){
    //confirm password
    let password = $('#password').val();
    let confirmPassword = $('#confirm-password').val();
    if(password != confirmPassword)
        document.getElementById('confirm-password').setCustomValidity("Passwords Don't Match");

    else
        document.getElementById('confirm-password').setCustomValidity("");
}

$('#password').change(validatePassword);
$('#confirm-password').keyup(validatePassword);

});