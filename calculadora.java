/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package upiicsa.carlos.servlet;

import com.sun.istack.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class calculadora extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet calcula edad</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edad Calculada </h1>");
            out.println("<p>");
            out.println("En: " + request.getContextPath());
            out.println("</p>");
            out.println("<p>");
            if(request.getAttribute("edad") != null){
                out.print("Edad =" + request.getAttribute("edad"));
            }
            out.println("</p>");
            out.println("<p>" + this.getServletInfo() + "</p>");
            out.println("</body>");
            out.println("</html>");
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
    String strfecha = request.getParameter("fechanacimiento");
    if (strfecha == null) {
        // Si no hay fecha proporcionada, muestra el formulario
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calcular Edad</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ingrese su fecha de nacimiento</h1>");
            out.println("<form action='' method='GET'>"); // El formulario se envía a sí mismo
            out.println("Fecha de nacimiento (dd/MM/yyyy): <input type='text' name='fechanacimiento'>");
            out.println("<input type='submit' value='Calcular Edad'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    } else {
        // Si hay una fecha, procesa la solicitud como antes
        Date fechaNac = null;
        try {
            fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(strfecha);
        } catch (ParseException ex) {
            Logger.getLogger(calculadora.class.getName(), calculadora.class).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        if(fechaNac != null){
            Calendar fechaNacimiento = Calendar.getInstance();
            Calendar fechaActual = Calendar.getInstance();
            fechaNacimiento.setTime(fechaNac);
            
            int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
            if (fechaNacimiento.get(Calendar.DAY_OF_YEAR) > fechaActual.get(Calendar.DAY_OF_YEAR)) {
                edad--; // Ajusta la edad si el cumpleaños no ha pasado este año
            }
            
            request.setAttribute("edad", edad);
        }
        processRequest(request, response);
    }
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
        return "Servlet que calcula la edad, pasando como parámetro la fecha de nacimiento (fecha=dd/MM/yyyy) = Carlos Abraham Saldaña Enriquez";
    }// </editor-fold>


}