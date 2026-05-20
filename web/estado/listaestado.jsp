<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html"
        pageEncoding="iso-8859-1"%>

<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">
        Estados
    </h1>

    <p class="mb-4">
        Cadastro de Estados
    </p>

    <a class="btn btn-success mb-4"
       href="${pageContext.request.contextPath}/EstadoNovo">

        Novo

    </a>

    <div class="card shadow">

        <div class="card-body">

            <table id="datatable"
                   class="display">

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Nome Estado</th>
                        <th>Sigla</th>
                        <th>Ações</th>

                    </tr>

                </thead>

                <tbody>

                    <c:forEach var="estado"
                               items="${estados}">

                        <tr>

                            <td>${estado.id}</td>

                            <td>
                                ${estado.nomeEstado}
                            </td>

                            <td>
                                ${estado.siglaEstado}
                            </td>

                            <td>

                                <a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/EstadoNovo?id=${estado.id}">

                                    Alterar

                                </a>

                                <a class="btn btn-danger"
                                   href="${pageContext.request.contextPath}/EstadoExcluir?id=${estado.id}">

                                    Excluir

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

    padding: 15px 30px;
    text-align: center;
}

#datatable th {

    background-color: #f8f9fc;
    font-weight: bold;
}

</style>

<%@include file="/footer.jsp"%>