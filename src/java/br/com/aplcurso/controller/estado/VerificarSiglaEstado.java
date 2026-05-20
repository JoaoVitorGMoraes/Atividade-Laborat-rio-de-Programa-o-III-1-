package br.com.aplcurso.controller.estado;

import br.com.aplcurso.dao.EstadoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "VerificarSiglaEstado",
        urlPatterns = {"/VerificarSiglaEstado"})

public class VerificarSiglaEstado extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String sigla =
                    request.getParameter(
                            "siglaestado"
                    );

            EstadoDAO dao =
                    new EstadoDAO();

            if (dao.siglaExiste(sigla)) {

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