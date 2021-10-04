var urlLink = "http://localhost:8080/users/" // Link para la conexión con el API rest

getUsers();

//Consulata con el Controlador
function getUsers(){
  $.ajax({
        type: "POST",
        url: urlLink + "list", //ruta de la API consultaremos.

        success: function(data) {
          printAll(data);
      }});
}

//Para buscar a un usuario
$('form').on('click', '.search-button', function(){
  userId = document.getElementById("input1").value;
  $.ajax({
    type: "GET",
    url: urlLink + userId,

    error: function(){
      console.log("Error en la petición");
      getUsers();
    }
  }).done(function(data){
    if (data.userIdCard == undefined){
      alert("El usuario no existe")
    }
    else{
      printOne(data);
    }
  })
})

//Crear un nuevo ususario
$('form').on('click', '.create-button', function(){
  var newUser = getUserData();
  $.ajax({
    type: "POST",
    url: urlLink + "create",
    headers: {
        'Accept': '*/*',
        'Content-Type': 'application/json'},
    data: JSON.stringify(newUser),
    dataType: 'json',
    success: function (responseData){
      console.log(responseData)
    },
    error: function(error){
      console.log("Error en la petición" + error);
      getUsers();
    }
  }).done(function(){
      getUsers();
      clearInputs();
    }
  )
})

//Actualizar un usuario
$('form').on('click', '.update-button', function(){
  var newUser = getUserData();
  $.ajax({
    type: "PUT",
    url: urlLink + "update",
    headers: {
        'Accept': '*/*',
        'Content-Type': 'application/json'},
    data: JSON.stringify(newUser),
    dataType: 'json',
    success: function (responseData){
      console.log(responseData)
    },
    error: function(error){
      console.log("Error en la petición" + error);
      getUsers();
    }
  }).done(function(){
      getUsers();
      clearInputs();
    }
  )
})

//Eliminar Usuario
$('form').on('click', '.delete-button', function(){
  userId = document.getElementById("input1").value;
  $.ajax({
    type: "DELETE",
    url: urlLink + userId,

    error: function(){
      alert("Error en la petición");
      getUsers();
    }
  }).done(function(){
    //alert("Se eliminó al usuario");
    getUsers();
    clearInputs();
  })
})

// Obtiene los datos del formulario y crea un objeto
function getUserData(){
  var newUser = new Object;
  newUser.userIdCard = +document.getElementById("input1").value;
  newUser.userName = document.getElementById("input2").value;
  newUser.userEmail = document.getElementById("input3").value;
  newUser.user = document.getElementById("input4").value;
  newUser.password = document.getElementById("input5").value;
  //console.log(newUser)
  return newUser;
  
}

// Limpia los datos del formulario
function clearInputs(){
  var elements = document.getElementsByClassName('input');
      for (var ii=0; ii < elements.length; ii++) {
          elements[ii].value = "";
      }
}



//Agrega todos los usuarios al HTML
function printAll(data){
  table = document.getElementById("user_table");
  lista = document.getElementById("user_table_body");
  lista.innerHTML = '';
  $.each(data, function(i, item) {
    var tr = document.createElement("tr");
    var trId = document.createAttribute("id");
    trId.value = item.userIdCard;
    tr.setAttributeNode(trId);
    var columna1 = document.createElement("td");
    columna1.innerHTML = item.userIdCard;
    var columna2 = document.createElement("td");
    columna2.innerHTML = item.userEmail;
    var columna3 = document.createElement("td");
    columna3.innerHTML = item.userName;
    var columna4 = document.createElement("td");
    columna4.innerHTML = item.password;
    var columna5 = document.createElement("td");
    columna5.innerHTML = item.user;
    

    lista.appendChild(tr);
    tr.appendChild(columna1);
    tr.appendChild(columna2);
    tr.appendChild(columna3);
    tr.appendChild(columna4);
    tr.appendChild(columna5);
  });


}

// Agrega solo un Usuario al HTML
function printOne(data){
  table = document.getElementById("user_table");
  lista = document.getElementById("user_table_body");
  lista.innerHTML = '';
  var tr = document.createElement("tr");
  var trId = document.createAttribute("id");
  trId.value = data.userIdCard;
  tr.setAttributeNode(trId);
  var columna1 = document.createElement("td");
  columna1.innerHTML = data.userIdCard;
  var columna2 = document.createElement("td");
  columna2.innerHTML = data.userEmail;
  var columna3 = document.createElement("td");
  columna3.innerHTML = data.userName;
  var columna4 = document.createElement("td");
  columna4.innerHTML = data.password;
  var columna5 = document.createElement("td");
  columna5.innerHTML = data.user;
  

  lista.appendChild(tr);
  tr.appendChild(columna1);
  tr.appendChild(columna2);
  tr.appendChild(columna3);
  tr.appendChild(columna4);
  tr.appendChild(columna5);

  
}

