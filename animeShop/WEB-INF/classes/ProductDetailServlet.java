import java.io.*;
import java.sql.*;
import jakarta.servlet.*; // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.util.*;

@WebServlet("/productdetailquery") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class ProductDetailServlet extends HttpServlet {

    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the MIME type for the response message
        response.setContentType("text/html");
        // Get a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        // Print an HTML page as the output of the query
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Welcome to Anime Shop</title></head>");
        out.write("    <link\r\n");
        out.write("      href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\"\r\n");
        out.write("      rel=\"stylesheet\"\r\n");
        out.write("      integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\"\r\n");
        out.write("      crossorigin=\"anonymous\"\r\n");
        out.write("    />\r\n");
        out.write("    <link\r\n");
        out.write("      rel=\"stylesheet\"\r\n");
        out.write("      href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css\"\r\n");
        out.write("    />\r\n");

        out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/style.css' />");

        out.println("<body>");
        out.println("<div>");
        out.println("<a href=\"http://localhost:9999/animeShop/start\"> <img class='center' src='" + request.getContextPath()
                + "/bg.png' width = '150' height = '150' alt='image'>");
        out.println("</div>");

        out.println("<ul class='nav justify-content-center'>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href=\"http://localhost:9999/animeShop/start\" >Home</a>");
        out.println("</li>");
        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='#''>Link</a>");
        out.println("</li>");
        out.println("</ul>");

        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/animedb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "myuser", "xxxx"); // For MySQL
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username",
                // "password"

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();) {
            // Step 3: Execute a SQL SELECT query
            String sqlStr = "select * from product where productID = "
                    + "'" + request.getParameter("id") + "'"; // Single-quote SQL string

            ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

            // Step 4: Process the query result set
            int count = 0;
            while (rset.next()) {
                out.println(" <div class='container-fluid'>");
                out.println(" <div class='row'>");
                out.println("<div class='col-4'>");
                out.println(
                        "<img id='productImg' src=\"https://mikatan.goodsmile.info/en/wp-content/uploads/-000//1/o0515068313838584221.jpg\" alt='Responsive image'>");
                out.println("</div>");
                out.println("<div class='col-4'>");
                out.println(
                    "<img id='productImg' src=\"https://mikatan.goodsmile.info/en/wp-content/uploads/-000//1/o0515068313838584221.jpg\" alt='Responsive image'>");
                out.println("</div>");
                out.println("<div class='col-3'>");
                out.println("<div class='text-center'>");
                out.println("<br>");
                out.println("<h4>" + rset.getString("name") + "</h4>");
                out.println("<h4> $" + rset.getDouble("price") + "</h4>");
                out.println("<p>" + rset.getString("prod_desc") + "</p>");
                out.println("<br>");
                out.println("<form method='get' action='http://localhost:9999/animeShop/orderquery'> ");
                out.println("<button type='submit' class='btn btn-dark btn-block' name='id' value=" + "'" + 1 + "'"
                        + "> <i class='bi bi-bag'></i> Add to Cart </button>");
                        out.println("<form />");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            }

        } catch (Exception ex) {
            out.println("<p>Error: " + ex.getMessage() + "</p>");
            out.println("<p>Check Tomcat console for details.</p>");
            ex.printStackTrace();
        } // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK
          // 7)

        out.println("</body></html>");
        out.close();
    }

    // The new doPost() runs the doGet() too
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Re-direct POST request to doGet()
    }
}