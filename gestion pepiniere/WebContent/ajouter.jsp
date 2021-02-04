<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pépinière</title>
<script><%@include file="/js/jquery.min.js"%></script>
<style><%@include file="/css/bootstrap.min.css"%></style>
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
				<img id="imgAvatar" class="rounded mx-auto d-block" style="width: 470px; height: 370px; margin-bottom: 30px;">
			</div>
			<div class="form-row mb-3" style="display: flex;">
				<div class="form-group col-md-5">
					<label>Nom Article</label> 
					<input type="text" class="form-control" name="nom" id="nom" minlength="4" required >
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
		
	<script><%@include file="/js/popper.min.js"%></script>
	<script><%@include file="/js/bootstrap.min.js"%></script>

</body>
</html>