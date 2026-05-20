package br.com.aplcurso.controller.estado;

import br.com.aplcurso.dao.EstadoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "EstadoExcluir",
        urlPatterns = {"/EstadoExcluir"})

public class EstadoExcluir extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            EstadoDAO dao =
                    new EstadoDAO();

            boolean retorno =
                    dao.excluir(id);

            if (retorno) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/EstadoListar"
                );

            } else {

                response.getWriter().write(
                        "Erro ao excluir!"
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