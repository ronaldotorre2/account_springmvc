<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../template/layout.jsp"></jsp:include>
<body>
	<jsp:include page="../template/header.jsp"></jsp:include>
	
	<div class="container">
		<h3><label id="lblTitle"></label></h3>
		
		<div id="form" style="height: 400px; overflow-y: auto">
		
			<form:form  action="save" method="post" modelAttribute= "person">
            
            	<form:input type="text" path="id" id="txtId" style="display:none"/>
            
                <label>Tipo:</label>
                <input type="radio" name="rbType" id="pf" value="F" onclick="ValidatePerson(this)">F�sica</input> &nbsp;
                <input type="radio" name="rbType" id="pj" value="J" onclick="ValidatePerson(this)">Jur�dica</input><br/><br/>
                <form:input type="text" path="TypeId" id="typeId" style="display:none"/>

                <div id="rpf" style="display: none;">
                    <fieldset>
                        <legend>Dados Gerais</legend>
                        
                        <label>Nome: </label> <form:input type="text" path="name" id="txtName" name="txtName" required="required" maxlength="50" autocomplete="off"/><br/>
                        <label>Data Nasc.: </label><form:input type="text" path="birthDate" id="txtBirthdate" name="txtBirthdate" required="required" maxlength="11" autocomplete="off" /><br/>
                        <label>G�nero: </label>
                        <form:select path="genderId" id="ddlGender" name="ddlGender" required="required">
                            <option value=""></option>
                            <option value="1">Feminino</option>
                            <option value="2">Masculino</option>
                        </form:select><br/>
                        <label>CPF: </label><form:input type="text" path="document1" id="txtDoc1" name="txtDoc1" required="required" maxlength="15" autocomplete="off"/><br/>
                        <label>RG: </label><form:input type="text" path="document2" id="txtDoc2" name="txtDoc2" required="required" maxlength="13" autocomplete="off" /><br/>
                        <label>Nome da M�e: </label><form:input type="text" path="motherName" id="txtMotherName" name="txtMotherName" autocomplete="off"/><br/>
                        <label>Nome do Pai: </label><form:input type="text" path="fatherName" id="txtFatherName" name="txtFatherName" autocomplete="off"/><br/>
                    </fieldset>
                </div>

                <div id="rpj" style="display: none;">
                    <fieldset>
                        <legend>Dados Gerais</legend>
                        
                    </fieldset>
                </div>
                <br>
                <div id="raddress" style="display: none;">
                    <fieldset>
                        <legend>Endere�o</legend>
                        
                        <label>Tipo: </label>
                        <form:select path="address.typeId" id="ddlAddressType" name="ddlAddressType" required="required">
                            <option value=""></option>
                            <option value="0">Comercial</option>
                            <option value="1">Entrega</option>
                            <option value="2">Residecial</option>
                            <option value="3">Outros</option>
                        </form:select><br/>
                        <label>CEP: </label><form:input type="text" path="address.zipCode" id="txtZipCode" name="txtZipCode" required="required" maxlength="9" autocomplete="off"/><br/>
                        <label>Lograduouro: </label><form:input type="text" path="address.name" id="txtAddress" name="txtAddress" required="required" maxlength="100" autocomplete="off" /><br/>
                        <label>N�mero: </label><form:input type="text" path="address.number" id="txtNumber" name="txtNumber" required="required" size="5" maxlength="6" autocomplete="off"/><br/>
                        <label>Complemento: </label><form:input type="text" path="address.complement" id="txtComplement" name="txtComplement" autocomplete="off"/><br/>
                        <label>Bairro: </label><form:input type="text" path="address.district" id="txtDistrict" name="txtDistrict" required="required" autocomplete="off"/><br/>
                        <label>Cidade: </label><form:input type="text" path="address.city" id="txtCity" name="txtCity" required="required" autocomplete="off"/><br/>
                        <label>Estado: </label><form:input type="text" path="address.state" id="txtState" name="txtState" size="5" required="required" maxlength="2" autocomplete="off"/><br/>
                        
                    </fieldset>
                    <br/>
                    
                    <span id="btnBack1" style="display: none">
                    	<form:button class="btn btn-default btn-sm">Salvar</form:button> <a href="../person/" class="btn btn-default btn-sm">Voltar</a>
                    </span>
                    
                    <span id="btnBack2" style="display: none">
                    	<form:button class="btn btn-default btn-sm">Salvar</form:button> <a href="../../person/" class="btn btn-default btn-sm">Voltar</a>
                    </span>
                    
                </div>
             
            </form:form>
        </div>
		
	</div>
	
	<script type="text/javascript">
            function ValidatePerson(type) {
                if(type.value == "F") {
                    document.getElementById("rpf").style.display = 'block';
                    document.getElementById("rpj").style.display = 'none';
                    document.getElementById("typeId").value = 1;
                } 
                else if(type.value == "J") {
                    document.getElementById("rpj").style.display = 'block';
                    document.getElementById("rpf").style.display = 'none';
                    document.getElementById("typeId").value = 2;
                }

                document.getElementById("raddress").style.display = 'block';
                document.getElementById("btnBack1").style.display = 'block';
            }

            function loadPersonType(){
				var type = document.getElementById("typeId"); 

				if(type.value == 1){
					document.getElementById("rpf").style.display = 'block';
                    document.getElementById("rpj").style.display = 'none';
                    document.getElementById("pf").checked = true;
				}
				else if(type == 2){
					document.getElementById("rpj").style.display = 'block';
                    document.getElementById("rpf").style.display = 'none';
                    document.getElementById("pj").checked = true;
				}

				document.getElementById("raddress").style.display = 'block';
            }

            function load(){
				var item = document.getElementById("txtId");
				
				if(item.value != ""){
					document.getElementById("lblTitle").innerHTML = "Editar pessoa";
					document.getElementById("btnBack2").style.display = 'block';
					
					loadPersonType();
				}	
				else{
					document.getElementById("lblTitle").innerHTML = "Nova pessoa";
				}
            }
            load();
        </script>
	
</body>
</html>