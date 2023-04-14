window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      console.log(data);
      //recorremos la colección del JSON
         var table = document.getElementById("pacientesTable");
         var tableBody = document.getElementById("pacienteTableBody");

         for(paciente of data){
         tableBody.innerHtml+=paciente.nombre;
            //por cada paciente armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula

            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

// creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un paciente
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            // creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar al odontologo que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick=" findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            let direccion="No declarado";
            if (paciente.domicilio !=null){
            direccion= paciente.domicilio.calle + ' '+paciente.domicilio.numero + ', '+paciente.domicilio.localidad + ', '+paciente.domicilio.provincia;
            }
              pacienteRow.innerHTML ='<td>' + updateButton + '</td>' +
                    //'<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_DNI\">' + paciente.dni + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>'+
                    '<td class=\"td_email\">' + paciente.email.toLowerCase() + '</td>'+
                    '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>' +
                    '<td class=\"td_domicilio\">' +direccion+ '</td>'+
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_all_pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })