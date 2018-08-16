<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../template/layout.jsp"></jsp:include>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	
	<div class="container">
		<h3>Consultar pessoa</h3>
		
		<div id="form" style="height: 400px; overflow-y: auto">
			<form:form  action="find" method="post">
				<label>Tipo:</label>
				<input type="radio" name="rbType" id="name" 	value="1" onclick="validate(this.value)">Nome</input> &nbsp;
	            <input type="radio" name="rbType" id="document" value="2" onclick="validate(this.value)">CPF/CNPJ</input><br/><br/>
	            <input type="text" id="typeId" name="typeId" style="display:none"/>
	            
            	<label id="lblTipo"></label>
				<input type="text" id="txtFind" name="txtFind" required="required" maxlength="50" autocomplete="off"/>
				<br/>
				
				<div id="findControl">
					<button type="submit" class="btn btn-default btn-sm">Consultar</button> <a href="../person/" class="btn btn-default btn-sm">Voltar</a>
				</div>
				
				<span id="btnBack"><a href="../person/" class="btn btn-default btn-sm">Voltar</a></span>
				
			</form:form>
			
		</div>
	</div>
	
	<script type="text/javascript">
		function load(){
			document.getElementById("txtFind").style.display = 'none';
			document.getElementById("findControl").style.display = 'none';
		}
		load();
	
		function validate(type){
			//console.log(type);
			if(type == 1){
				document.getElementById("lblTipo").innerHTML= "Por Nome:";
				document.getElementById("typeId").value = 1;
			}
			else if(type == 2){
				document.getElementById("lblTipo").innerHTML= "Por CPF/CNPJ:";
				document.getElementById("typeId").value = 2;
			}

			document.getElementById("txtFind").style.display = 'block';
			document.getElementById("findControl").style.display = 'block';
			document.getElementById("btnBack").style.display = 'none';
			
		}
	</script>
	
</body>
</html>