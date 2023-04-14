function deleteBy(id)
{
          //con fetch invocamos a la API  con el método DELETE
          //pasandole el id en la URL
          const url = '/turnos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          let confirmation=confirm("Confirma la eliminación del turno con ID= "+id+"?");
          if (confirmation){
          fetch(url,settings)
          .then(response => response.json())


          let row_id = "#tr_" + id;
          document.querySelector(row_id).remove();
          }

}