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

    <div class="card shadow">

        <div class="card-body">

            <form action="EstadoCadastrar"
                  method="post">

                <input type="hidden"
                       name="id"
                       value="${estado.id}">

                <div class="form-group">

                    <label>Nome Estado</label>

                    <input type="text"
                           name="nomeestado"
                           value="${estado.nomeEstado}"
                           required
                           class="form-control">

                </div>

                <div class="form-group">

                    <label>Sigla</label>

                    <input type="text"
                           name="siglaestado"
                           id="siglaestado"
                           maxlength="2"
                           value="${estado.siglaEstado}"
                           required
                           class="form-control">

                </div>

                <button type="submit"
                        class="btn btn-success">

                    Salvar

                </button>

            </form>

        </div>

    </div>

</div>

<script>

$("#siglaestado").blur(function () {

    let sigla =
            $("#siglaestado").val();

    $.ajax({

        url: "VerificarSiglaEstado",

        method: "GET",

        data: {
            siglaestado: sigla
        },

        success: function (resposta) {

            if (resposta === "EXISTE") {

                alert(
                    "Sigla já cadastrada!"
                );

                $("#siglaestado").val("");

                $("#siglaestado").focus();
            }
        }
    });

});

</script>

<%@include file="/footer.jsp"%>