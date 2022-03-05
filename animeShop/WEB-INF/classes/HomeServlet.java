import java.io.*;
import java.sql.*;
import jakarta.servlet.*; // Tomcat 10
import jakarta.servlet.http.*; // Tomcat 10
import jakarta.servlet.annotation.*; // Tomcat 10
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.*;

@WebServlet("/login") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class HomeServlet extends HttpServlet {

    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Set the response MIME type of the response message
        response.setContentType("text/html");
        // Allocate a output writer to write the response message into the network
        // socket
        PrintWriter out = response.getWriter();

        // Write the response message, in an HTML page
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
                out.println("<a class='nav-link active' aria-current='page' href=\"http://localhost:9999/animeShop/start\" >Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
                out.println("<a class='nav-link' href='#''>Link</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
                out.println("<a class='nav-link active' aria-current='page' href=\"http://localhost:9999/animeShop/contactus\">Contact Us</a>");
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
                
                out.println("<section class='vh-100' style='background-color: #9A616D'>");
                out.println("<div class='container py-5 h-100'>");
                out.println("<div class='row d-flex justify-content-center align-items-center h-100'>");
                out.println("<div class='col col-xl-10'>");   
                out.println("<div class='card' style='border-radius: 1rem'>");
                out.println("<div class='row g-0'>");
                out.println("<div class='col-md-6 col-lg-5 d-none d-md-block'>");
                out.println("<img src='https://i.pinimg.com/originals/be/86/b2/be86b28459ca07c5be01eeabe0c7d7fe.jpg' alt='login form' class='img-fluid' style='border-radius: 1rem 0 0 1rem'/>");
                out.println("</div>");
                out.println("<div class='col-md-6 col-lg-7 d-flex align-items-center'>");
                out.println("<div class='card-body p-4 p-lg-5 text-black'>");


                out.println("<form action='login.php' method='post'>");
                out.println("<div class='d-flex align-items-center mb-3 pb-1'>");
                out.println("<i class='fas fa-cubes fa-2x me-3' style='color: #ff6219'></i>");
                out.println("<span class='h1 fw-bold mb-0'>Welcome to Anime Shop!</span>");
                out.println("</div>");
                out.println("<h5 class='fw-normal mb-3 pb-3' style='letter-spacing: 1px '>Sign into your account</h5>");
                

                out.println("<div class='form-outline mb-4'>");
                out.println("<input type='email' id='email' class='form-control form-control-lg' required/>");
                out.println("<label class='form-label' for='email'>Email address</label>");
                out.println("</div>");
                out.println("<div class='form-outline mb-4'>");
                out.println("<input type='password' id='password' class='form-control form-control-lg' required/>");
                out.println("<label class='form-label' for='password'>Password</label>");
                out.println("</div>");
                out.println("<div class='pt-1 mb-4'>");
                //out.println("<a class='nav-link active' aria-current='page' href=\"http://localhost:9999/animeShop/start\"</a>");
                out.println("<button class='btn btn-dark btn-lg btn-block' type='button' name='login' value='login'>Login</button>");
                //out.println("<form method='get' action='http://localhost:9999/animeShop/start'> ");
                out.println("</div>");

                out.println("<a class='small text-muted' href='#!'>Forgot password?</a>");
                out.println("<p class='mb-5 pb-lg-2' style='color: #393f81'>Don't have an account? <a href='#!' style='color: #393f81'>Register here</a></p>");
                out.println("<a href='#!' class='small text-muted'>Terms of use.</a>");
                out.println("<a href='#!' class='small text-muted'>Privacy policy</a>");
                out.println("</form>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</section>");

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