<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pépinière</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
	<%@ include file="WEB-INF/menu.jsp"%>
	
	<div class="container mt-5" >
	
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
		<c:forEach var="article" items="${ articles }"  varStatus="status">
			<div class="col">
				<div class="card mx-5">
					<img src="data:image/jpeg;base64,${ images[status.index] }" class="card-img-top" alt="..." style="height:200px">
					<div class="card-body">
						<h5 class="card-title"><c:out value="${ article.getNom() }" /></h5>
						<a href="detail?id=${ article.getId() }" class="btn btn-primary">Détail</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
	
</body>
</html>