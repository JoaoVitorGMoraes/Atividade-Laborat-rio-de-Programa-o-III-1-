
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Cadastro de Usuário</title>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>

</head>

<body>

<h1>Cadastro de Usuário</h1>

<form action="${pageContext.request.contextPath}/UsuarioCadastrar" method="post">

    <input type="hidden" name="id" value="${usuario.id}">

    <div>
        Nome:<br>
        <input type="text" id="nome" name="nome" value="${usuario.nome}">
    </div>

    <br>

    <div>
        Data Nascimento:<br>
        <input type="date" id="datanascimento" name="datanascimento" value="${usuario.dataNascimento}">
    </div>

    <br>

    <div>
        CPF:<br>
        <input type="text" id="cpf" name="cpf" value="${usuario.cpf}">
    </div>

    <br>

    <div>
        E-mail:<br>
        <input type="email" id="email" name="email" value="${usuario.email}">
    </div>

    <br>

    <div>
        Senha:<br>
        <input type="password" id="senha" name="senha" value="${usuario.senha}">
    </div>

    <br>

    <div>
        Salário:<br>
        <input type="text" id="salario" name="salario" value="${usuario.salario}">
    </div>

    <br>

    <button type="button" onclick="validarCampos()">
        Salvar
    </button>

</form>

<br>

<a href="${pageContext.request.contextPath}/UsuarioListar">
    Voltar
</a>

<script>

    $(document).ready(function () {

        $('#cpf').mask('000.000.000-00');

        $('#salario').maskMoney({
            prefix: 'R$ ',
            thousands: '.',
            decimal: ',',
            allowZero: false
        });

    });

    function validarCampos() {

        let nome = $('#nome').val();
        let datanascimento = $('#datanascimento').val();
        let cpf = $('#cpf').val();
        let email = $('#email').val();
        let senha = $('#senha').val();
        let salario = $('#salario').val();

        if (nome === '') {

            Swal.fire({
                icon: 'warning',
                title: 'Campo obrigatório',
                text: 'Informe o nome!'
            });

            return;
        }

        if (datanascimento === '') {

            Swal.fire({
                icon: 'warning',
                title: 'Campo obrigatório',
                text: 'Informe a data de nascimento!'
            });

            return;
        }

        if (cpf === '') {

            Swal.fire({
                icon: 'warning',
                title: 'Campo obrigatório',
                text: 'Informe o CPF!'
            });

            return;
        }

        if (email === '') {

            Swal.fire({
                icon: 'warning',
                title: 'Campo obrigatório',
                text: 'Informe o e-mail!'
            });

            return;
        }

        if (senha === '') {

            Swal.fire({
                icon: 'warning',
                title: 'Campo obrigatório',
                text: 'Informe a senha!'
            });

            return;
        }

        if (salario === '') {

            Swal.fire({
                icon: 'warning',
                title: 'Campo obrigatório',
                text: 'Informe o salário!'
            });

            return;
        }

        document.forms[0].submit();
    }

</script>

</body>
</html>
