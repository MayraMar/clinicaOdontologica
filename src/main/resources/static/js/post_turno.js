window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo paciente
    const formulario = document.querySelector('#add_new_turno');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

       //creamos un JSON que tendrá los datos del nuevo turno
        const formData = {
            paciente:{
            id: document.querySelector('#idP').value,
            apellido:document.querySelector('#apellidoP').value,
            dni:document.querySelector('#dni').value,
            },

            fecha: document.querySelector('#fecha').value,

            odontologo:{
                id: document.querySelector('#idO').value,
                apellido:document.querySelector('#apellidoO').value,

            }
        };
        //invocamos utilizando la función fetch la API con el método POST que guardará
        //al odontologo que enviaremos en formato JSON
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que el odontologo
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno Registrado con éxito </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                console.log(error)
                    //Si hay algun error se muestra un mensaje diciendo que la pelicula
                    //no se pudo guardar y se intente nuevamente
                    /*let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";*/
                     //se dejan todos los campos vacíos por si se quiere ingresar otra pelicula
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#titulo').value = "";
        document.querySelector('#categoria').value = "";
        document.querySelector('#premios').value = "";

    }

    /*(function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/post_turno.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })()*/;
});