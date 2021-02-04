<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pépinière</title>
<style><%@include file="/css/bootstrap.min.css"%></style>
<body>
	<%@ include file="WEB-INF/menu.jsp"%>

	<div class="container mt-5">

		<c:if test="${ !empty erreur }">
			<p style="color: red;">
				<c:out value="${ erreur }" />
			</p>
		</c:if>

		<c:if test="${ !empty succes }">
			<p style="color: green;">
				<c:out value="${ succes }" />
			</p>
		</c:if>

		<c:if test="${empty articles}">
			<h1 style="text-align: center;" class="mt-5">Aucune article</h1>
		</c:if>

		<div class="row row-cols-1 row-cols-md-3 g-4">
			<c:forEach var="article" items="${ articles }" varStatus="status">
				<div class="col">
					<div class="card mx-2">
						<img src="data:image/jpeg;base64,${ images[status.index] }"
							class="card-img-top" alt="..." style="height: 300px">
						<div class="card-body">
							<h5 class="card-title">
								<c:out value="${ article.getNom() }" />
							</h5>
							<a href="detail?id=${ article.getId() }" class="btn btn-primary">Détail</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script><%@include file="/js/popper.min.js"%></script>
	<script><%@include file="/js/bootstrap.min.js"%></script>

</body>
</html>