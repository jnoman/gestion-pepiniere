<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pépinière</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>


	<%@ include file="WEB-INF/menu.jsp"%>

	<c:if test="${ !empty erreur }">
		<p style="color: red;" class="mt-3">
			<c:out value="${ erreur }" />
		</p>
	</c:if>
	
	<c:if test="${ !empty succes }">
		<p style="color: green;" class="mt-3">
			<c:out value="${ succes }" />
		</p>
	</c:if>

	<form method="post" action="ajouter" enctype="multipart/form-data">
		<script language="JavaScript">
			function showPreview(ele) {
				$("#imgAvatar").attr("src", ele.value); // for IE
				if (ele.files && ele.files[0]) {

					var reader = new FileReader();

					reader.onload = function(e) {
						$("#imgAvatar").attr("src", e.target.result);
					}

					reader.readAsDataURL(ele.files[0]);
				}
			}
		</script>
		<div class="container mt-3" >
			<div class="container">
				<input type="file" name="image" accept="image/*"
					OnChange="showPreview(this)" required>
				<hr>
				<img id="imgAvatar" class="rounded mx-auto d-block" style="width: 500px; height: 300px; margin-bottom: 30px;">
			</div>
			<div class="form-row mb-3" style="display: flex;">
				<div class="form-group col-md-5">
					<label>Nom Article</label> 
					<input type="text" class="form-control" name="nom" id="nom" minlength="4"pattern="[a-zA-Z]*$" required >
				</div>
				<div class="form-group col-md-5 ms-5">
					<label>Quantité</label> 
					<input class="form-control" name="quantite" type="number" min="0" max="100" required>
				</div>
			</div>
			<div class="form-group mb-3">
				<label for="description">Description</label>
				<textarea class="form-control" name="description" rows="3" minlength="20"></textarea>
			</div>
			<button type="submit" class="btn btn-info btn-lg">ajouter</button>
		</div>
	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
		integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
		integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj"
		crossorigin="anonymous"></script>

</body>
</html>