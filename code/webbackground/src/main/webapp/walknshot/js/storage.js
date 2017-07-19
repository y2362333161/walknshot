$(function() {

	$("#save").click(function(e) {
		var filename = $("input[name='filename']").val();
		console.log(filename);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateStoragePro',
				cache : false,
				processData : true,
				dataType : "text",
				data : {
					id : id,
					filename : filename,
				},
				success : function(data) {
					location.reload();
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addStoragePro',
				cache : false,
				processData : true,
				dataType : "text",
				data : {
					filename : filename,
				},
				success : function(data) {
					location.reload();
				}
			})
		}

		$('#modal').modal('hide');
	});

	$(".delete").click(function(e) {
		bootbox.confirm({
			buttons : {
				confirm : {
					label : 'Delete'
				},
				cancel : {
					label : 'Cancel'
				}
			},
			message : 'Sure to delete?',
			callback : function(result) {
				if (result) {

					var dataset = e.currentTarget.dataset;
					var id = dataset.id;
					jQuery.ajax({
						url : 'deleteStoragePro',
						cache : false,
						processData : true,
						dataType : "text",
						data : {
							id : id
						},
						success : function(data) {						
							location.reload();
						}
					});

				}
			}
		});
	});

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("input[name='filename']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='filename']").val(dataset.filename);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});
});

	
