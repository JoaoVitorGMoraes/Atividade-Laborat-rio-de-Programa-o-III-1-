package br.com.aplcurso.controller.usuario;

import br.com.aplcurso.dao.GenericDAO;
import br.com.aplcurso.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioExcluir", urlPatterns = {"/UsuarioExcluir"})
public class UsuarioExcluir extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            GenericDAO usuarioDAO = new UsuarioDAO();

            if (usuarioDAO.excluir(id)) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/index.jsp"
                );

            } else {

                response.getWriter().write(
                        "Erro ao excluir usuário!"
                );
            }

        } catch (Exception ex) {

            System.out.println(
                    "Erro ao excluir usuário: "
                    + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}