<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../template/layout.jsp"></jsp:include>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>

	<div class="container">
		<h3>Vizualizar pessoa</h3>
		<br>
		<form:form method="post" modelAttribute="person">
			
		  <div style="height: 400px; overflow-y: auto">
		
		   <input type="text" id="typeId" name="typeId" value="${person.typeId}" style="display: none;">
		
		   <div id="pf" class="table-responsive">
		   		<fieldset>
		   			<legend>Dados Gerais</legend>
		   			
		   			<table>
						<tr>
							<td>Tipo:</td>
							<td>${person.type.initial}</td>
						</tr>
						<tr>
							<td>Nome:</td>
							<td>${person.name}</td>
						</tr>
						<tr>
							<td>Gênero:</td>
							<td>${person.gender.name}</td>
						</tr>
						<tr>
							<td>Data nascimento: </td>
							<td>${person.birthDate}</td>
						</tr>
						<tr>
							<td>CPF:</td>
							<td>${person.document1}</td>
						</tr>
						<tr>
							<td>Identidade:</td>
							<td>${person.document2}</td>
						</tr>
						<tr>
							<td>Nome da Mãe:</td>
							<td>${person.motherName}</td>
						</tr>
						<tr>
							<td>Nome do Pai:</td>
							<td>${person.fatherName}</td>
						</tr>
					</table>
		   		</fieldset>
			
		  </div>
		  
		  <div id="pj" class="table-responsive">
		  		<fieldset>
		   			<legend>Dados Gerais</legend>
		   			
					<table>
						<tr>
							<td>Tipo:</td>
							<td>${person.type.initial}</td>
						</tr>
						<tr>
							<td>Nome:</td>
							<td>${person.name}</td>
						</tr>
						<tr>
							<td>Razão Social:</td>
							<td>${person.socialName}</td>
						</tr>
						<tr>
							<td>CNPJ:</td>
							<td>${person.document1}</td>
						</tr>
						<tr>
							<td>Inscrição Estadual:</td>
							<td>${person.document2}</td>
						</tr>
						<tr>
							<td>Inscrição Municipal:</td>
							<td>${person.document3}</td>
						</tr>
					</table>
				</fieldset>
		  </div>
		  <br>
		  <div id="address">
		  		<fieldset>
		   			<legend>Endereço</legend>
		   			
		   			<table>
						<tr>
							<td>Logradoro:</td>
							<td>${person.address.name}</td>
						</tr>
						<tr>
							<td>Número:</td>
							<td>${person.address.number}</td>
						</tr>
						<tr>
							<td>Complemento:</td>
							<td>${person.address.complement}</td>
						</tr>
						<tr>
							<td>Bairro:</td>
							<td>${person.address.district}</td>
						</tr>
						<tr>
							<td>Cidade:</td>
							<td>${person.address.city}</td>
						</tr>
						<tr>
							<td>Estado:</td>
							<td>${person.address.state}</td>
						</tr>
					</table>
		   		</fieldset>
		  </div>
		  
		 </div> 	
		</form:form>
		
		<br>
		<a href="../../person/" class="btn btn-default btn-sm">Voltar</a>

	</div>
	
	<script type="text/javascript">
		function load(){
			var type = document.getElementById("typeId").value;
			//console.log(type);

			if(type == 1){
				document.getElementById("pf").style.display = 'block';
				document.getElementById("pj").style.display = 'none';
			}
			else if(type == 2){
				document.getElementById("pf").style.display = 'none';
				document.getElementById("pj").style.display = 'block';
			}
		}
		load();
	</script>

</body>
</html>