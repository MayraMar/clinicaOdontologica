window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;
           console.log("Escuche el submit del update form")
        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {

                       id:document.querySelector('#turno_id').value,

                       fecha: document.querySelector('#fecha').value,

                       paciente: {
                       id: document.querySelector('#idP').value,
                       apellido:document.querySelector('#apellidoP').value,
                       dni:document.querySelector('#dni').value,
                       },

                       odontologo:{
                           id: document.querySelector('#idO').value,
                           apellido:document.querySelector('#apellidoO').value,

                       }
                       };


        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value=turno.id;
              document.querySelector('#idP').value=turno.paciente.id;
              document.querySelector('#dni').value=turno.paciente.dni;
              document.querySelector('#apellidoP').value=turno.paciente.apellido;
              document.querySelector('#fecha').value= turno.fecha;
              document.querySelector('#idO').value=turno.odontologo.id
              document.querySelector('#apellidoO').value=turno.odontologo.apellido;



              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }