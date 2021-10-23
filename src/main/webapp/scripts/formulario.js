const formulario = document.getElementById('form');
const inputs = document.querySelectorAll('#form input');
const expresiones = {
	user: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
	userName: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	password: /^.{4,12}$/, // 4 a 12 digitos.
	userEmail: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
	userIdCard: /^\d{1,15}$/ // 7 a 15 numeros.
}

const campos = {
	user: false, 
	userName: false,
	password: false,
	userEmail: false,
	userIdCard: false
}


const validarFormulario = (e) => {
	//console.log(e.target.name)	// funcion para comprobar campos
	switch(e.target.name){			// Comprueba cada vez q se oprime una tecla y se suelta
		case 'userIdCard':		
			validarCampo(expresiones.userIdCard, e.target, e.target.id);		
		break;
		
		case 'userName':
			validarCampo(expresiones.userName, e.target, e.target.id);		
		break;
		
		case 'userEmail':
			validarCampo(expresiones.userEmail, e.target, e.target.id);		
		break;
		
		case 'user':
			validarCampo(expresiones.user, e.target, e.target.id);		
		break;
		
		case 'password':
			validarCampo(expresiones.password, e.target, e.target.id);		
		break;
		
	}		
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`${campo}`).classList.add('formulario__grupo-correcto');
		//document.querySelector(`${campo}.formulario__input-error`).classList.remove('formulario__input-error-activo');
		
	}else{
		document.getElementById(`${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`${campo}`).classList.remove('formulario__grupo-correcto');
		//document.querySelector(`grupo__${campo}.formulario__input-error`).classList.add('formulario__input-error-activo');
	}
}

//const validarFormulario = () =>{
	//console.log('Tecla levantada');	// comprobar en consola q funcione
//}

inputs.forEach((input) =>{
	input.addEventListener('keyup', validarFormulario);		// se oprime una tecla y se deja de oprimir
	//input.addEventListener('blur', validarFormulario);		//  cuando esta escribiendo y da clic por fuera hace la comprobacion
});
			
	


form.addEventListener('submit', (e) =>{			// cuando presione enviar valida, envia y borra
	e.preventDefault();							// si no pasa la validacion no hace nada
});