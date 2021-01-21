package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDAO;

public class InserirCliente extends HttpServlet {

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
            out.println("<title>Servlet InserirCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            
            try {
                String nome = request.getParameter("nome_completo");
                String email = request.getParameter("email");
                String cpf = request.getParameter("cpf");
                String tel_contato = request.getParameter("tel_contato");
                String tel_whatsapp = request.getParameter("tel_whatsapp");
                String endereco = request.getParameter("endereco");
                String cep = request.getParameter("cep");
                                
                Cliente c = new Cliente();                
                c.setNomeCompleto(nome);
                c.setEmail(email);                
                c.setCpf(cpf);
                c.setTelefoneContato(tel_contato);
                c.setTelefoneWhatsapp(tel_whatsapp);
                c.setEndereco(endereco);
                c.setCep(cep);
                                
                ClienteDAO cDAO = new ClienteDAO();
                cDAO.inserir(c);
                
                response.sendRedirect("listar_cliente.jsp");                
            } catch (Exception e) {
                out.println("ERRO: " + e);
            }            
            
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
