package br.com.aplcurso.controller.usuario;

import br.com.aplcurso.dao.UsuarioDAO;
import br.com.aplcurso.model.Usuario;
import br.com.aplcurso.utils.DocumentoValidador;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "UsuarioCadastrar",
        urlPatterns = {"/UsuarioCadastrar"}
)

public class UsuarioCadastrar extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {

        response.setContentType(
                "text/html;charset=UTF-8"
        );

        try {

            UsuarioDAO dao = new UsuarioDAO();

            int id = 0;

            if (request.getParameter("id") != null
                    && !request.getParameter("id").isBlank()) {

                id = Integer.parseInt(
                        request.getParameter("id")
                );
            }

            String nome =
                    request.getParameter("nome");

            String dataStr =
                    request.getParameter(
                            "datanascimento"
                    );

            String cpf =
                    request.getParameter("cpf")
                            .replace(".", "")
                            .replace("-", "");

            String email =
                    request.getParameter("email");

            String senha =
                    request.getParameter("senha");

            String salarioStr =
                    request.getParameter("salario");

            if (nome == null || nome.isBlank()
                    || dataStr == null || dataStr.isBlank()
                    || cpf == null || cpf.isBlank()
                    || email == null || email.isBlank()
                    || senha == null || senha.isBlank()
                    || salarioStr == null || salarioStr.isBlank()) {

                response.getWriter().write(
                        "Preencha todos os campos!"
                );

                return;
            }

            
            Date dataNascimento =
                    java.sql.Date.valueOf(dataStr);

            
            salarioStr = salarioStr
                    .replace("R$ ", "")
                    .replace(".", "")
                    .replace(",", ".")
                    .trim();

            double salario =
                    Double.parseDouble(salarioStr);

          
            if (!DocumentoValidador.isCPF(cpf)) {

                response.getWriter().write(
                        "CPF inválido!"
                );

                return;
            }

           
            if (dao.cpfExiste(cpf)
                    && id == 0) {

                response.getWriter().write(
                        "CPF já cadastrado!"
                );

                return;
            }

            
            if (!DocumentoValidador
                    .isEmailValido(email)) {

                response.getWriter().write(
                        "E-mail inválido!"
                );

                return;
            }

            /*
             VALIDAR EMAIL DUPLICADO
             */
            if (dao.emailExiste(email)
                    && id == 0) {

                response.getWriter().write(
                        "E-mail já cadastrado!"
                );

                return;
            }

          
            if (senha.length() < 6) {

                response.getWriter().write(
                        "A senha deve ter "
                        + "no mínimo 6 caracteres!"
                );

                return;
            }

          
            if (salario <= 0) {

                response.getWriter().write(
                        "Salário inválido!"
                );

                return;
            }

            
            Usuario oUsuario = new Usuario();

            oUsuario.setId(id);
            oUsuario.setNome(nome);
            oUsuario.setCpf(cpf);
            oUsuario.setDataNascimento(
                    dataNascimento
            );
            oUsuario.setEmail(email);
            oUsuario.setSenha(senha);
            oUsuario.setSalario(salario);

            if (dao.cadastrar(oUsuario)) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/UsuarioListar"
                );

            } else {

                response.getWriter().write(
                        "Erro ao salvar usuário!"
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            response.getWriter().write(
                    "Erro no cadastro: "
                    + ex.getMessage()
            );
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {

        return "Servlet Cadastro Usuário";
    }
}