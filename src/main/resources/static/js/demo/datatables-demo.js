// Call the dataTables jQuery plugin
$(document).ready(function() {
	$('#dataTable').DataTable({
		language: {
			decimal: ",",
			thousands: ".",
			lengthMenu: "Mostrar _MENU_ registros por página",
			zeroRecords: "No se encontraron resultados",
			info: "Mostrando página _PAGE_ de _PAGES_",
			infoEmpty: "No hay registros disponibles",
			infoFiltered: "(filtrado de _MAX_ registros totales)",
			search: "Buscar:",
			paginate: {
				first: "Primero",
				previous: "Anterior",
				next: "Siguiente",
				last: "Último"
			}
		},
		paging: true,
		searching: true,
		ordering: true,
		info: true
	});
});
