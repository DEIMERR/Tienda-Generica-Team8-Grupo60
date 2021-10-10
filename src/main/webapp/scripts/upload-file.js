var file = document.getElementById("products-file");
var uploadText = document.getElementById("upload-text");

file.addEventListener("change", function(){
    if (file.value) {
        uploadText.innerHTML = file.value.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
    }
    else{
        uploadText.innerHTML = "Seleccionar archivo"
    }
});

