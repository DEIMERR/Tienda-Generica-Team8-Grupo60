var urlLink = "http://localhost:8080/users/user/"

$('.login').on('click', '.login-button', function(){
    userByName();
    
})


//Para buscar a un usuario
function userByName(){
  user = document.getElementById("input1").value;
  $.ajax({
    type: "GET",
    url: urlLink + user,

    error: function(){
      console.log("Error en la petición");
    }
  }).done(function(data){
    if (data.userIdCard == undefined){
      alert("El usuario no existe")
    }
    else{
      console.log(data);
    }
  })
}
