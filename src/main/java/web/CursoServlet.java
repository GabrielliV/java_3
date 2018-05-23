package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CursoServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter saida = resp.getWriter();
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection("jdbc:firebirdsql:192.168.232.3/3050:/databases/erpcolegio.fdb?encoding=WIN1252", "SYSDBA", "masterkey");
            
            PreparedStatement p = connection.prepareStatement("select * from curso");
            ResultSet rs = p.executeQuery();
            saida.println("<html> <ul>");
            
            while (rs.next()) {
                saida.println("<li> " + rs.getString("nome") + "</li>");
            }
            saida.println("</ul> </html>");
            
            
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter saida = resp.getWriter();
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection connection = DriverManager.getConnection("jdbc:firebirdsql:192.168.232.3/3050:/databases/erpcolegio.fdb?encoding=WIN1252", "SYSDBA", "masterkey");
            
            PreparedStatement p = connection.prepareStatement("INSERT INTO CURSO VALUES (?, ?, ?, ?, ?)");
            p.setInt(1, (Integer.parseInt(req.getParameter("id"))));
            p.setString(2, req.getParameter("nome_curso"));
            p.setString(3, req.getParameter("turno"));
            p.setString(4, req.getParameter("quant_estudante"));
            p.setString(5, req.getParameter("data_cadastro"));
            p.execute();
      
            ResultSet rs = p.executeQuery();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        saida.write("Concluído!");
    }
    
    
}

//        int id = Integer.parseInt(req.getParameter("id"));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            data_cadastro = format.parse(req.getParameter("data_cadastro"));
//        } catch (ParseException ex) {
//            Logger.getLogger(CursoServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }