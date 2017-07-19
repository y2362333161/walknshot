$(function() {

	$("#save").click(function(e) {
		var uid = $("input[name='uid']").val();
		var passphrase = $("input[name='passphrase']").val();
		console.log(uid, passphrase);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateTokenPro',
				cache : false,
				processData : true,
				dataType : "text",
				data : {
					id : id,
					uid : uid,
					passphrase : passphrase,
				},
				success : function(data) {
					location.reload();
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addTokenPro',
				cache : false,
				processData : true,
				dataType : "text",
				data : {
					uid : uid,
					passphrase : passphrase,
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
						url : 'deleteTokenPro',
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
		$("input[name='passphrase']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='uid']").val(dataset.uid);
		$("input[name='passphrase']").val(dataset.passphrase);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});
});

	
