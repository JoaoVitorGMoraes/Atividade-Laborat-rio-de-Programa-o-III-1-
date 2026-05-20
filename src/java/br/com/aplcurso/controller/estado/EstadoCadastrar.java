package br.com.aplcurso.controller.estado;

import br.com.aplcurso.dao.EstadoDAO;
import br.com.aplcurso.model.Estado;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "EstadoCadastrar",
        urlPatterns = {"/EstadoCadastrar"})

public class EstadoCadastrar extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            EstadoDAO dao =
                    new EstadoDAO();

            int id = 0;

            if (request.getParameter("id") != null
                    && !request.getParameter("id").isBlank()) {

                id = Integer.parseInt(
                        request.getParameter("id")
                );
            }

            String nomeEstado =
                    request.getParameter(
                            "nomeestado"
                    );

            String siglaEstado =
                    request.getParameter(
                            "siglaestado"
                    );

          
            if (nomeEstado == null
                    || nomeEstado.isBlank()
                    || siglaEstado == null
                    || siglaEstado.isBlank()) {

                response.getWriter().write(
                        "Preencha todos os campos!"
                );

                return;
            }

          
            if (siglaEstado.length() != 2) {

                response.getWriter().write(
                        "Sigla deve ter 2 caracteres!"
                );

                return;
            }

       
            siglaEstado =
                    siglaEstado.toUpperCase();

      
            if (id == 0
                    && dao.nomeExiste(nomeEstado)) {

                response.getWriter().write(
                        "Nome do estado já cadastrado!"
                );

                return;
            }


            if (id > 0
                    && dao.nomeExisteOutro(
                            nomeEstado,
                            id)) {

                response.getWriter().write(
                        "Nome do estado já existe em outro cadastro!"
                );

                return;
            }

       
            if (id == 0
                    && dao.siglaExiste(siglaEstado)) {

                response.getWriter().write(
                        "Sigla já cadastrada!"
                );

                return;
            }

          
            if (id > 0
                    && dao.siglaExisteOutro(
                            siglaEstado,
                            id)) {

                response.getWriter().write(
                        "Sigla já cadastrada em outro estado!"
                );

                return;
            }

            Estado estado = new Estado();

            estado.setId(id);

            estado.setNomeEstado(
                    nomeEstado
            );

            estado.setSiglaEstado(
                    siglaEstado
            );

            if (dao.cadastrar(estado)) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/EstadoListar"
                );

            } else {

                response.getWriter().write(
                        "Erro ao salvar estado!"
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            response.getWriter().write(
                    "Erro: "
                    + ex.getMessage()
            );
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}