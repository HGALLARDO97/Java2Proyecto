/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools  Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;

/**
 *
 * @author benja
 */
public class ControladorLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String opcion = "";
        if (request.getParameter("opcion") != null) {
            opcion = request.getParameter("opcion");
        }
        String usuario = "", clave = "";

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorLogin</title>");
            out.println("</head>");
            out.println("<body>");
            switch (opcion) {
                case "Ingresar":
                    usuario = request.getParameter("usuario");
                    clave = request.getParameter("clave");                    
                    boolean estado = new EmpleadoDAO().verificarDatos(usuario, clave);
                    if (estado) {
                        Empleado emp = new EmpleadoDAO().buscarDatos(usuario);
                        
                      String rutEmpleado = emp.getRut() + "";
                        String nombreCompleto = emp.getPrimerNombre()+" " + emp.getSegundoNombre()+" " + emp.getApellidoPaterno()+" " + emp.getApellidoMaterno() + "";
                        //   response.sendRedirect("menuPrincipal.jsp");
                        request.setAttribute("rutEmpleado", rutEmpleado);
                        request.setAttribute("nombreCompletoE", nombreCompleto);
                        request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
                       break;
                    } else {
                        response.sendRedirect("errorLogin.jsp");
                    }
            }
            out.println("<h1>Servlet ControladorLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
