package br.com.aplcurso.controller.usuario;

import br.com.aplcurso.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VerificarEmail",
        urlPatterns = {"/VerificarEmail"})

public class VerificarEmail extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String email =
                    request.getParameter("email");

            UsuarioDAO dao = new UsuarioDAO();

            if (dao.emailExiste(email)) {

                response.getWriter()
                        .write("EXISTE");

            } else {

                response.getWriter()
                        .write("OK");
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            response.getWriter()
                    .write("ERRO");
        }
    }
}