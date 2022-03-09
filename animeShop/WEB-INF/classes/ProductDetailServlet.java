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

        out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n");
        out.write(
                "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n");

        out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/style.css' />");

        out.write(
                "    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\" ></script>\r\n");
        out.write(
                "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\" ></script>\r\n");

        out.write("<link rel='stylesheet' type='text/css' href='" + request.getContextPath() + "/alertStyle.css' />");
        // out.write("<script src='" + request.getContextPath() + "/alertJS.js' />");

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
            String sqlStr = "select * from product where productID = "
                    + "'" + request.getParameter("id") + "'"; // Single-quote SQL string

            ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

            // Step 4: Process the query result set
            int count = 0;
            while (rset.next()) {
                out.println(" <div class='container-fluid'>");
                out.println(" <div class='row'>");
                out.println("<div class='col-4'>");
                out.println("<img id='productImg' src='" + request.getContextPath() + "/assets/img/"
                        + rset.getString("img") + "' alt='Responsive image'>");

                out.println("</div>");
                out.println("<div class='col-4'>");
                out.println("<img id='productImg' src='" + request.getContextPath() + "/assets/img/"
                        + rset.getString("img2") + "' alt='Responsive image'>");

                out.println("</div>");
                out.println("<div class='col-3'>");
                out.println("<div class='text-center'>");
                out.println("<br>");
                out.println("<h4>" + rset.getString("name") + "</h4>");
                out.println("<h4> $" + rset.getDouble("price") + "</h4>");
                out.println("<p>" + rset.getString("prod_desc") + "</p>");
                out.println("<br>");
                // out.println("<form method='get'
                // action='http://localhost:9999/animeShop/orderquery'> ");
                out.println("<form method='post'> ");
                out.println(
                        "<button type='submit' class='btn btn-dark btn-block' name='productID' value=" + "'" + 1 + "'"
                                + "> <i class='bi bi-bag'></i> Add to Cart </button>");
                // out.println(
                // "<button type='button' class='btn btn-info btn-lg' data-toggle='modal'
                // data-target='#myModal'>Open Modal</button>");

                out.println("<form />");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");

            }



            if (request.getParameter("productID") != null) {
                String id = request.getParameter("id");
                sqlStr = "INSERT INTO cart (productID, customerID) VALUES (" + id + ", 1)";
                out.println("<p>" + sqlStr + "</p>"); // for debugging
                count = stmt.executeUpdate(sqlStr);

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Added to cart!');");
                out.println("</script>");

                // out.println("<div class='container'>");
                // out.println(" <div class='row'>");
                // out.println("<div class='col'>");
                // out.println(" 1 of 3");
                // out.println("</div>");
                // out.println("<div class='col-6'>");
                // out.println("<div class='alert alert-success alert-dismissible fade show'
                // role='alert'>");
                // out.println("<h4 class='alert-heading'>Well done!</h4>");
                // out.println("<a href='#'' class='close' data-dismiss='alert'
                // aria-label='close'>&times;</a>");
                // out.println("<p>Aww yeah, you successfully read this important alert
                // message</p>");
                // out.println("</div>");
                // out.println("</div>");
                // out.println("<div class='col'>");
                // out.println(" 3 of 3");
                // out.println("</div>");
                // out.println("</div>");
                // out.println("</div>");

                // out.println(
                // "<div class='modal fade' id='exampleModalCenter' tabindex='-1' role='dialog'
                // aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>");
                // out.println("<div class='modal-dialog modal-dialog-centered'
                // role='document'>");
                // out.println("<div class='modal-content'>");
                // out.println("<div class='modal-header'>");
                // out.println("<h5 class='modal-title' id='exampleModalLongTitle'>Modal
                // title</h5>");
                // out.println("<button type='button' class='close' data-dismiss='modal'
                // aria-label='Close'>");
                // out.println("<span aria-hidden='true'>&times;</span>");
                // out.println("</button>");
                // out.println("</div>");
                // out.println("<div class='modal-body'>");
                // out.println("</div>");
                // out.println(" <div class='modal-footer'>");
                // out.println("<button type='button' class='btn btn-secondary'
                // data-dismiss='modal'>Close</button>");
                // out.println("</div>");
                // out.println("</div>");
                // out.println("</div>");
                // out.println("</div>");

                // out.println("<div class='alert alert-success alert-dismissible fade show'
                // role='alert'>");
                // out.println("<h4 class='alert-heading'>Well done!</h4>");
                // out.println("<a href='#'' class='close' data-dismiss='alert'
                // aria-label='close'>&times;</a>");
                // out.println("<p>Aww yeah, you successfully read this important alert
                // message</p>");
                // out.println("<hr>");
                // out.println(
                // "<p class='mb-0'>Whenever you need to, be sure to use margin utilities to
                // keep things nice and tidy.</p>");
                // out.println("</div>");
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