package controller;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ConnectionDB;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = new ConnectionDB().open();
            usuarioDAO = new UsuarioDAO(connection);
        } catch (Exception e) {
            System.out.println("Error de conexion");
        }
    }

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");

    try {
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), usuarios);
    } catch (Exception e) {
        throw new ServletException("Error al obtener los usuarios", e);
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
