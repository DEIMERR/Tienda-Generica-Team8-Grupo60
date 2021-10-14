var urlLink = "http://localhost:8080/users/user/"
sessionStorage.setItem("session", "none")

$('.login').on('click', '.login-button', function(){
    userByName();
    
})

let tries = 0;

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
        if(isNaN(+data) == false){
          sessionStorage.setItem("session", data);
          if (sessionStorage.getItem("lastPage")){
             window.location.href = sessionStorage.getItem("lastPage");
          }
          else{
            window.location.href = "sales.html";
          }
        }
        else if(data == "incorrectName") {
          sessionStorage.setItem("session", "none")
          alert("El nombre de usuario es incorrecto");
          tries += 1;
          if (tries > 2) {
             forgotCrdentials();
          }
        }
        else if(data == "incorrectPassword"){
          sessionStorage.setItem("session", "none")
          alert("La contraseña es incorrecta")
          tries += 1;
     
          if (tries == 3) {
             forgotCrdentials();
          }
        }
        else{
          alert("Ocurrió un error");
        }
          
    
  })
 
    
}

function forgotCrdentials(){
  var urlLink = "http://localhost:8080/users/"
    $.ajax({
      type: "POST",
      url: urlLink +"list",

      error: function(){
        console.log("Error en la petición");
      }
    }).done(function(data){
      forgotLabel = document.getElementById("forgot-credentials");
      forgotLabel.insertAdjacentHTML('afterbegin', `<p>Si no sabes los datos intenta con:<br><b>Usuario:</b> ${data[0].user} <br><b>Contraseña:</b> ${data[0].password}</p>`)
      })
    }
