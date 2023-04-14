function deleteBy(id)
{
          //con fetch invocamos a la API  con el método DELETE
          //pasandole el id en la URL
          const url = '/pacientes/'+ id;
          const settings = {
              method: 'DELETE'
          }
          let confirmation=confirm("Confirma la eliminación del pacienteget_all_odontologos.html con ID= "+id+"?");
          if (confirmation){
          fetch(url,settings)
          .then(response => response.json())

          //borrar la fila de la pelicula eliminada
          let row_id = "#tr_" + id;
          document.querySelector(row_id).remove();
          }

}