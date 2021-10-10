var urlLink = "http://localhost:8080/users/user/"

$('.login').on('click', '.login-button', function(){
    userByName();
    
})


//Para buscar a un usuario
function userByName(){
    user = document.getElementById("input1").value;
    password = document.getElementById("input2").value;
    $.ajax({
    type: "GET",
    url: urlLink + user +"/"+ password,

    error: function(){
      console.log("Error en la petición");
    }
  }).done(function(data){
    
    switch(data){
        case "CorrectCredentials":
            window.location.href = "users.html";
            break;
        case "incorrectName":
            alert("El nombre de usuario es incorrecto");
            break;
        case "incorrectPassword":
            alert("La contraseña es incorrecta")
            break;
        default:
            alert("Ocurrió un error");
            break;


    }
  })
}
