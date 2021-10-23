let reportsTabeData = [];
let reportsData = [];
let reportsTable;
let customerName;
searchCustomer(123)

$( document ).ready(function() {
    
    $.when(getUsers().done(function(){
        //console.log(reportsData);
        getDataTables(reportsData) 
    }));
 
});

function getUsers(){
    var urlLink = "http://localhost:8080/sales/"
    return $.ajax({
        type: "POST",
        url: urlLink + "list", //ruta de la API consultaremos.

        success: function(data) {
          reportsData = data;
      }});
}

function getDataTables(data) {
    const dataTables = new Map();
    $(document).ajaxStop(function () {
    for (const row of data) {
        const table = dataTables.get(row.customerIdCard);
        if (table) {
            table.total += row.saleValue;
        } else {
            $.when(searchCustomer(row.customerIdCard).done(function(){
                dataTables.set(row.customerIdCard, {
                customerIdCard: row.customerIdCard,
                customerName: customerName,
                total: row.saleValue,
                    });
               }));
        }
    }
        reportsTabeData = Array.from(dataTables, ([key, val]) => val);
        if (reportsTabeData.length > 0) {
            //console.log(reportsTabeData);
            createTable(reportsTabeData)

        }

    });
    //return dataTables;    

}

function searchCustomer(customerIdCard){
    var urlLink = "http://localhost:8080/customers/" 
    return $.ajax({
        type: "GET",
        url: urlLink + customerIdCard.toString(),

        error: function(){
        console.log("Error en la petición");
        }
    }).done(function(data){
       
        customerName = data.customerName;
        //console.log(customerName + "aaaaaaaaaaaaaaaaaa")
    });
}

function createTable(data){
    tableHead = document.querySelector(".table-head")
    tableHead.innerHTML = ''
    tableHead.insertAdjacentHTML('afterbegin',
    '<tr> <th>Cédula</th> <th width="165">Nombre</th> <th>Valor Total Ventas</th> </tr>')
    
    var reportsTable = $("#product_table").DataTable( {
        data,
        columns: [
            { data: 'customerIdCard' },
            { data: 'customerName' },
            { data: 'total' }
        ],
        language: {
            "decimal":        "",
            "emptyTable":     "No hay datos disponibles en la tabla",
            "info":           "Mostrando _START_ a _END_ de _TOTAL_ entradas",
            "infoEmpty":      "Mostrando 0 a 0 de 0 entradas",
            "infoFiltered":   "(filtrado de _MAX_ entradas totales)",
            "infoPostFix":    "",
            "thousands":      ",",
            "lengthMenu":     "Mostrar _MENU_ entradas",
            "loadingRecords": "Cargando...",
            "processing":     "Procesando...",
            "search":         "Buscar:",
            "zeroRecords":    "No se encontró ningun elemento",
            "paginate": {
                "first":      "Primera",
                "last":       "Última",
                "next":       "Siguiente",
                "previous":   "Anterior"
            },
            "aria": {
                "sortAscending":  ": activate to sort column ascending",
                "sortDescending": ": activate to sort column descending"
            }
        }
    });
   
}
    
const formatter = new Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD',
  maximumFractionDigits: 0,

  // These options are needed to round to whole numbers if that's what you want.
  //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
  //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
});