$(function() {

	$("#save").click(function(e) {
		var uid = $("input[name='uid']").val();
		var latitude = $("input[name='latitude']").val();
		var longitude = $("input[name='longitude']").val();
		console.log(uid, latitude, longitude);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateSpotPro',
				cache : false,
				processData : true,
				dataType : "text",
				data : {
					id : id,
					uid : uid,
					latitude : latitude,
					longitude : longitude,
				},
				success : function(data) {
					location.reload();
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addSpotPro',
				cache : false,
				processData : true,
				dataType : "text",
				data : {
					uid : uid,
					latitude : latitude,
					longitude : longitude,
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
						url : 'deleteSpotPro',
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

		$("input[name='uid']").val("");
		$("input[name='latitude']").val("");
		$("input[name='longitude']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='uid']").val(dataset.uid);
		$("input[name='latitude']").val(dataset.latitude);
		$("input[name='longitude']").val(dataset.longitude);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});
});

	
