<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">Usuários</h1>
    <p class="mb-4">Cadastro de Usuários</p>

    <a class="btn btn-success mb-4"
       href="${pageContext.request.contextPath}/UsuarioNovo">

        <i class="fas fa-sticky-note"></i>
        <strong>Novo</strong>

    </a>

    <div class="card shadow">

        <div class="card-body">

            <table id="datatable" class="display">

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Email</th>
                        <th>Nascimento</th>
                        <th>Salário</th>
                        <th>Excluir</th>
                        <th>Alterar</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="usuario" items="${usuarios}">

                        <tr>

                            <td>${usuario.id}</td>

                            <td>${usuario.nome}</td>

                            <td>${usuario.cpf}</td>

                            <td>${usuario.email}</td>

                            <td>
                                <fmt:formatDate
                                    pattern="dd/MM/yyyy"
                                    value="${usuario.dataNascimento}"/>
                            </td>

                            <td>
                                <fmt:formatNumber
                                    value="${usuario.salario}"
                                    type="currency"/>
                            </td>

                            <td align="center">

                                <a class="btn btn-danger btn-sm"
                                   href="${pageContext.request.contextPath}/UsuarioExcluir?id=${usuario.id}">

                                    Excluir

                                </a>

                            </td>

                            <td align="center">

                                <a class="btn btn-primary btn-sm"
                                   href="${pageContext.request.contextPath}/UsuarioCarregar?id=${usuario.id}">

                                    Alterar

                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </tbody>

            </table>

        </div>

    </div>

</div>

<style>

#datatable {

    width: 100%;
}

#datatable th,
#datatable td {

    padding: 12px 18px;
    text-align: center;
}

#datatable th {

    background-color: #f8f9fc;
    font-weight: bold;
}

#datatable td {

    vertical-align: middle;
}

.btn-sm {

    min-width: 80px;
}

</style>

<script>

$(document).ready(function () {

    $('#datatable').DataTable({

        "oLanguage": {
            "sProcessing": "Processando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "Nenhum registro encontrado.",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
            "sSearch": "Buscar:",
            "oPaginate": {
                "sFirst": "Primeiro",
                "sPrevious": "Anterior",
                "sNext": "Próximo",
                "sLast": "Último"
            }
        }

    });

});

</script>

<%@include file="/footer.jsp"%>