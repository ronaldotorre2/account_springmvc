<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../template/layout.jsp"></jsp:include>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	
	<div class="container">
		<h3>Lista de Pessoa</h3>
        <br>
        <div id="grid">
        	<a href="new" class="btn btn-default btn-sm">Novo</a>  <a href="find" class="btn btn-default btn-sm">Consultar</a>
        	<br><br>
        	<div style="width: 100%; height: auto; border:1px solid black;">
        	  <div class="table-responsive">
	            <c:if test="${not empty people}">
	                <table class="table">
	                    <tr>
	                        <th>Tipo</th> 
	                        <th>Nome</th> 
	                        <th>Documento 1</th>
	                        <th>Documento 2</th>
	                        <th>Cidade / UF</th>
	                        <th>Registro</th>
	                        <th>Atualização</th>
	                        <th> </th>
	                    </tr>
	                    <c:forEach var="list" items="${people}">
	                        <tr>
	                            <td>${list.type.initial}</td> 
	                            <td>${list.name}</td> 
	                            <td>${list.document1}</td>
	                            <td>${list.document2}</td>
	                            <td>${list.address.city} - ${list.address.state}</td>
	                            <td>${list.addDate}</td>
	                            <td>${list.updateDate}</td>
	                            <td>
	                            	<a href="edit/${list.id}">Edit</a>|<a href="delete/${list.id}">Delete</a>|<a href="view/${list.id}">View</a>
	                            </td>
	                        </tr>
	                    </c:forEach>
	                </table>
	             </c:if>
	           </div>  
	        </div>
        </div>
	</div>
	
</body>
</html>