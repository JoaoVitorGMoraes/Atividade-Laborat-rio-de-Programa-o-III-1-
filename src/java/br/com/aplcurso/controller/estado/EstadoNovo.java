package br.com.aplcurso.controller.estado;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "EstadoNovo",
        urlPatterns = {"/EstadoNovo"})

public class EstadoNovo extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/estado/formestado.jsp"
        ).forward(request, response);
    }
}