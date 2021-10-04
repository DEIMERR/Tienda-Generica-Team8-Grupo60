
var urlLink = "http://localhost:8080/providers/" // Link para la conexión con el API rest


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
    if (data.providerNit == undefined){
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
  newUser.providerNit = +document.getElementById("input1").value;
  newUser.providerCity = document.getElementById("input2").value;
  newUser.providerAddress = document.getElementById("input3").value;
  newUser.providerName = document.getElementById("input4").value;
  newUser.providerPhone = document.getElementById("input5").value;
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
  lista = document.getElementById("user_table_body");
  lista.innerHTML = '';
  $.each(data, function(i, item) {
    var tr = document.createElement("tr");
    var trId = document.createAttribute("id");
    trId.value = item.providerNit;
    tr.setAttributeNode(trId);
    var columna1 = document.createElement("td");
    columna1.innerHTML = item.providerNit;
    var columna2 = document.createElement("td");
    columna2.innerHTML = item.providerCity;
    var columna3 = document.createElement("td");
    columna3.innerHTML = item.providerAddress;
    var columna4 = document.createElement("td");
    columna4.innerHTML = item.providerName;
    var columna5 = document.createElement("td");
    columna5.innerHTML = item.providerPhone;
    

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
  lista = document.getElementById("user_table_body");
  lista.innerHTML = '';
  var tr = document.createElement("tr");
  var trId = document.createAttribute("id");
  trId.value = data.providerNit;
  tr.setAttributeNode(trId);
  var columna1 = document.createElement("td");
  columna1.innerHTML = data.providerNit;
  var columna2 = document.createElement("td");
  columna2.innerHTML = data.providerCity;
  var columna3 = document.createElement("td");
  columna3.innerHTML = data.providerAddress;
  var columna4 = document.createElement("td");
  columna4.innerHTML = data.providerName;
  var columna5 = document.createElement("td");
  columna5.innerHTML = data.providerPhone;
  

  lista.appendChild(tr);
  tr.appendChild(columna1);
  tr.appendChild(columna2);
  tr.appendChild(columna3);
  tr.appendChild(columna4);
  tr.appendChild(columna5);
}

