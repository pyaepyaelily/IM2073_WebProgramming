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


@WebServlet("/thanks") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class ThankServlet extends HttpServlet {

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
        
        out.write("<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>");

        out.println("<body>");
            out.println("<div>");
                out.println("<button type='submit' class='btn' id='cartBtn'><a href=\"http://localhost:9999/animeShop/cart\" </a> <i class='bi bi-cart'></i> </button>");
            out.println("</div>");

            out.println("<div>");
                out.println("<a href=\"http://localhost:9999/animeShop/home\"> <img class='center' src='" + request.getContextPath() + "/assets/logo.png' width = '130' height = '130' alt='image'>");
            out.println("</div>");
        

        out.println("<ul class='nav justify-content-center'>");
            out.println("<li class='nav-item'>");
                out.println("<a class='nav-link active' aria-current='page' href=\"http://localhost:9999/animeShop/home\" >Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
                out.println("<a class='nav-link active' aria-current='page' href=\"http://localhost:9999/animeShop/start\">Products</a>");
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
            out.println("<header class='w3-display-container w3-content w3-wide' style='max-width:1500px' id='thank'>");
            out.println("<img class='w3-image' class='w3-opacity' src='" + request.getContextPath() + "/assets/fairy2.jpg" + "' alt='fairy' width='1500' height='800'>");
            //out.println("<img class='w3-image' class='w3-opacity' src='https://www.baltana.com/files/wallpapers-14/Fairy-Tail-High-Definition-Wallpaper-37364.jpg' alt='fairy' width='1500' height='800'>");
            out.println("<div class='w3-display-middle w3-margin-top w3-center'>");
            out.println("<h1 class='display-3 text-capitalize w3-text-white' style='font=300%'><strong>THANK YOU FOR YOUR PURCHASE!</strong></h1>");
            out.println("<hr class='w3-border-white' style='margin:auto;width:40%'>");
            out.println("<h4 class= 'w3-text-sand'>  Leave a feedback! </h4>");
            out.println("<br>");
            //out.println("<button type='submit' class='btn btn-light btn-outline-primary btn-lg d-inline-flex flex-row align-items-center w3-ripple' id='cartBtn'><a href=\"http://localhost:9999/animeShop/contactus\" </a>Feedback</button>");
            out.println("<button type='submit' class='btn btn-light btn-outline-primary' ><a href=\"http://localhost:9999/animeShop/contactus\" </a> Feedback </button>");

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