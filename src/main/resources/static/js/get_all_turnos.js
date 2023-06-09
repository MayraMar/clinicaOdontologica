window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      console.log(data);
      //recorremos la colección del JSON
         var table = document.getElementById("turnoTable");
         var tableBody = document.getElementById("turnoTableBody");

         for(turno of data){
         tableBody.innerHtml+=turno.fecha;
            //por cada paciente armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula

            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

// creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un paciente
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            // creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar al odontologo que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick=" findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            let pacienteString= turno.paciente.apellido.toUpperCase()+', '+turno.paciente.nombre.toUpperCase()+' - DNI: '+turno.paciente.dni;
            let odontologoString= turno.odontologo.apellido.toUpperCase()+', '+turno.odontologo.nombre.toUpperCase()+' - Matrícula: '+turno.odontologo.matricula;

              turnoRow.innerHTML ='<td>' + updateButton + '</td>' +

                    '<td class=\"td_paciente\">' + pacienteString + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                    '<td class=\"td_odontologo\">' + odontologoString + '</td>'+

                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_all_turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })