<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Storage"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>WalknShot</title>

<%
	String path = request.getContextPath();
%>
<link href="<%=path%>/walknshot/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/walknshot/css/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/walknshot/css/dataTables.responsive.css"
	rel="stylesheet">
<link href="<%=path%>/walknshot/css/walknshot.css" rel="stylesheet">
<link href="<%=path%>/walknshot/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>

<body>
	<%
		ArrayList<Storage> storageList = new ArrayList<Storage>();
		if (request.getAttribute("storages") != null) {
			storageList = (ArrayList<Storage>) request.getAttribute("storages");
		}
	%>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

		<div class="navbar-header">
			<a class="navbar-brand" href="#">WalknShot Manage</a>
		</div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="allUsersPro"><i class="fa fa-user fa-fw"></i>Users</a></li>
					<li><a href="allTokensPro"><i class="fa fa-random fa-fw"></i>Tokens</a></li>
					<li><a href="allSpotsPro"><i class="fa fa-location-arrow fa-fw"></i>Spots</a></li>
					<li><a href="allPicturesPro"><i class="fa fa-picture-o fa-fw"></i>Pictures</a></li>	
					<li><a href="allStoragesPro"><i class="fa fa-file-image-o fa-fw"></i>Storages</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Storages</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							add storage
							<button class="btn btn-default" type="button" id="add">
								<i class="fa fa-plus"></i>
							</button>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
										    <th>ID</th>
											<th>Filename</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < storageList.size(); i++) {
																				Storage storage = storageList.get(i);
										%>
										<tr>
										    <td><%=storage.getId()%></td>
											<td><%=storage.getFilename()%></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<%=storage.getId()%>">
													<i class="fa fa-trash"></i>
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<%=storage.getId()%>"
													data-filename="<%=storage.getFilename()%>">
													<i class="fa fa-edit"></i>
												</button>
											</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Filename</label> <input class="form-control" name="filename">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">Save</button>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=path%>/walknshot/js/jquery.min.js"></script>
	<script src="<%=path%>/walknshot/js/bootstrap.min.js"></script>
	<script src="<%=path%>/walknshot/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/walknshot/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/walknshot/js/walknshot.js"></script>
	<script src="<%=path%>/walknshot/js/bootbox.min.js"></script>

	<script src="<%=path%>/walknshot/js/storage.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>

</body>

</html>

