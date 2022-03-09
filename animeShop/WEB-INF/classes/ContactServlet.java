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


@WebServlet("/contactus") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class ContactServlet extends HttpServlet {

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

        out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
        out.write("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");

        out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/style.css' />");
        out.write("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" ></script>\r\n");
        // out.write("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\" ></script>\r\n");
        
        out.write("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
        out.write("<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\"></script>\r\n");
        out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js\"></script>\r\n");

        out.write("    <link\r\n");
        out.write("      rel=\"stylesheet\"\r\n");
        out.write("      href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"\r\n");
        out.write("    />\r\n");




        out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/contact.css' />");
        out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/style.css' />");


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

                out.println("<div class='container'>");
                // out.println("<form action='action_page.php'>");
                out.println("<label for='fname'>First Name</label>");
                out.println("<input type='text' id='fname' name='firstname' placeholder='Your name..'>");
                out.println("<label for='lname'>Last Name</label>");
                out.println("<input type='text' id='lname' name='lastname' placeholder='Your last name..'>");
                out.println("<label for='region'>Region</label>");
                out.println("<select id='region' name='region'>");
                out.println("<option value='West'>West</option>");
                out.println("<option value='East'>East</option>");
                out.println("<option value='North'>North</option>");
                out.println("<option value='South'>South</option>");
                out.println("</select>");
                out.println("<label for='subject'>Subject</label>");
                out.println("<textarea id='subject' name='subject' placeholder='Write something..' style='height:200px'></textarea>");
                out.println("<form method='get' >");
                out.println("<button type='submit' class='btn btn-dark' style='width: 100%;' name='contactID'> Submit </button>");
                out.println("<form />");
                out.println("</div>");
                if (request.getParameter("contactID") != null) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Thank you for your enquiry! We will get back to you soon!');");
                    out.println("</script>");
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