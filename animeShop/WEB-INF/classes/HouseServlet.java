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

@WebServlet("/home") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class HouseServlet extends HttpServlet {

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
        out.write("<link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>");
        //<!--Inter UI font-->
        //out.write("<link href='https://rsms.me/inter/inter-ui.css' rel='stylesheet'>");

        //<!--vendors styles-->
        //out.write("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        //out.write("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css'>");
        //out.write("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css'>");

        //<!-- Bootstrap CSS / Color Scheme -->
        //out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() +  "/default.css' id='theme-color' />");

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
                /*
                out.println("<section class='py-7 py-md-0 bg-hero' id='home'>");
                 out.println("<img class='w3-image' class='w3-opacity' src='https://i.pinimg.com/originals/a1/f5/af/a1f5af8548c57b5c31eb8e5345d27ae8.png' alt='anime' width='1500' height='800'>");

                out.println("<div class='container'>");
                out.println("<div class='row vh-md-100'>");
                out.println("<div class='col-md-8 col-sm-10 col-12 mx-auto my-auto text-center'>");
                out.println("<h1 class='heading-black text-capitalize'>WELCOME TO THE ANIME SHOP!</h1>");
                out.println("<p class='lead py-3'>Your Second Home to All Your Favourite Anime Collectables</p>");
                out.println("<button class='btn btn-primary d-inline-flex flex-row align-items-center'>Order now!<em class='ml-2' data-feather='arrow-right'></em>");
                out.println("</button>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</section>");


                
                out.println("<header class='w3-display-container w3-content w3-wide' style='max-width:1500px' id='home'>");
                out.println("<img class='w3-image' class='w3-opacity' src='https://i.pinimg.com/originals/a1/f5/af/a1f5af8548c57b5c31eb8e5345d27ae8.png' alt='anime' width='1500' height='800'>");
                out.println("<div class='w3-display-middle w3-margin-top w3-center'>");
                out.println("<h1 class='w3-jumbo w3-text-white w3-monospace'> <span class='w3-hide-small w3-text-light-grey'><strong>WELCOME TO THE ANIME SHOP!</strong></span></h1>");
                out.println("<h4 class='w3-xxlarge w3-text-aqua w3-monospace'> <br> Your Second Home to Your Favourite Anime Collection! </br></h4>");

                out.println("</div>");
                out.println("</header>");
*/
                out.println("<header class='w3-display-container w3-content w3-wide' style='max-width:1500px' id='home'>");
                out.println("<img class='w3-image' class='w3-opacity' src='https://i.pinimg.com/originals/a1/f5/af/a1f5af8548c57b5c31eb8e5345d27ae8.png' alt='anime' width='1500' height='800'>");
                out.println("<div class='w3-display-middle w3-margin-top w3-center'>");
                out.println("<h1 class='display-3 text-capitalize w3-text-white' style='font=300%'><strong>WELCOME TO THE <br> ANIME SHOP! </br></strong></h1>");
                out.println("<h4 class= 'w3-text-aqua'>  Your Second Home To Your Favourite Anime Collection! </h4>");
                out.println("<p class='lead py-3 w3-text-white'> What Are You Waiting For? <br> Start Ordering Now! </b> </p>");

                //out.println("<button class='btn btn-light btn-primary btn-lg d-inline-flex flex-row align-items-center w3-ripple >Order now!<em class='ml-2'></em>");
                out.println("<a class='nav-link active' aria-current='page' href=\"http://localhost:9999/animeShop/start\"</a>");
                out.println("<button type='button' class='btn btn-light btn-outline-primary btn-lg d-inline-flex flex-row align-items-center w3-ripple'>Click Here!</button>");

                out.println("</div>");
                out.println("</header>");



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