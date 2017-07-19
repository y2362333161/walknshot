$(function() {

	$("#save").click(function(e) {
		var spotid = $("input[name='spotid']").val();
		var storageid = $("input[name='storageid']").val();
		console.log(spotid, storageid);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updatePicturePro',
				cache:false,
				processData : true,
				dataType : "text",
				data : {
					id : id,
					spotid : spotid,
					storageid : storageid,
				},
				success : function(data) {
					location.reload();
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addPicturePro',
				cache:false,
				processData : true,
				dataType : "text",
				data : {
					spotid : spotid,
					storageid : storageid,
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
						url : 'deletePicturePro',
						cache:false,
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

		$("input[name='spotid']").val("");
		$("input[name='storageid']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='spotid']").val(dataset.spotid);
		$("input[name='storageid']").val(dataset.storageid);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});
});

	
