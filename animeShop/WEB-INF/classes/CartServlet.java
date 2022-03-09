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

@WebServlet("/cart") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class CartServlet extends HttpServlet {

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
            int rsCount = 0;
            String sqlStr = "SELECT * FROM cart INNER JOIN product ON cart.productID=product.productID WHERE customerID=1;";
            boolean results = stmt.execute(sqlStr);

            ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

            // Step 4: Process the query result set
            if (results) {
                rsCount++;
            }
            out.println("<br>");
            out.println("<h1 class='text-center'>Cart</h1>");
            out.println("<br>");
            Double totalOrderValue = 0.0;
            Double orderValue = 0.0;
            Double totalOrderValueWShip = 0.0;
            out.println("<div class='container' style='border:1px solid #cecece;''>");
                out.println("<div class='row'>");
                    out.println("<div class='col-8 border'>");
                    while (rset.next()) {
                        out.println("<div class='row'>");
                             out.println("<div class='col'>");
                             out.println("<img id='cartImg' width='100%' height='180px' src='" + request.getContextPath() + "/assets/img/" + rset.getString("img") + "' alt='image'>");
                            out.println("</div>");
                            out.println("<div class='col-6'>");
                                out.println("<h6>" + rset.getString("name") + "</h6>");
                                out.println("<p> S$ " + rset.getString("price") + "</p>");
                            out.println("</div>");
                            out.println("<div class='col'>");
                            out.println("<form method='get' >");
                                out.println("<button type='submit' class='btn' id='trashBtn' name='cartID' value=" + "'" + rset.getInt("id") + "'"
                                + "> <i class='fa fa-trash'></i>          </button>");
                                out.println("<form />");
                                out.println("</div>");
                        out.println("</div>");
                        out.println("<br>");
                        orderValue =  Double.parseDouble(rset.getString("price"));
                        totalOrderValue = totalOrderValue + orderValue;
                    }
                    int count = 0;
                    if (request.getParameter("cartID") != null) {
                        String id = request.getParameter("cartID");

                        sqlStr = "DELETE FROM cart WHERE (ID = " + id + ")";
                        count = stmt.executeUpdate(sqlStr);
                        out.println("<script type=\"text/javascript\">");
                        out.println("location.replace('http://localhost:9999/animeShop/cart')");
                        out.println("</script>");
                    }


                         
                    out.println("</div>");
                    out.println("<div class='col'>");
                        out.println("<div style='background-color: white;'>");
                            out.println("<br>");
                            out.println("<div class='row'>");
                                out.println("<div class='col'>");
                                    out.println("<p class='text-muted'> Order Value </p>");
                                out.println("</div>");
                                out.println("<div class='col'>");
                                    out.println("<div class='c'>");
                                        out.println("<p class='text-muted'> S$ " +  totalOrderValue + "</p>");
                                    out.println("</div>");
                                out.println("</div>");
                            out.println("</div>");
                            out.println("<div class='row'>");
                                out.println("<div class='col'>");
                                    out.println("<p class='text-muted'>Delivery</p>");
                                out.println("</div>");
                                out.println("<div class='col'>");
                                    out.println("<div class='c'>");
                                    if (totalOrderValue >= 100){
                                        out.println("<p class='text-muted'> FREE </p>");
                                    }
                                    else{
                                        out.println("<p class='text-muted'> S$ 5 </p>");
                                    }
                                        
                                    out.println("</div>");
                                out.println("</div>");
                            out.println("</div>");
                            out.println("<hr>");
                            out.println("<div class='row'>");
                                out.println("<div class='col'>");
                                    out.println("<strong> Total </strong>");
                                out.println("</div>");
                                out.println("<div class='col'>");
                                    out.println("<div class='c'>");
                                    if (totalOrderValue >= 100){
                                        out.println("<strong> S$ " + totalOrderValue + "</strong>");
                                    }
                                    else{
                                        totalOrderValueWShip = totalOrderValue + 5;
                                        out.println("<strong> S$ " + totalOrderValueWShip + "</strong>");
                                    }
                                    out.println("</div>");
                                out.println("</div>");
                            out.println("</div>");
                            out.println("<br>");
                            out.println("<form method='get' >");
                                out.println("<button type='submit' class='btn btn-dark' style='width: 100%;' name='orderID'> Order </button>");
                                // out.println("<button type='submit' class='btn btn-dark' style='width: 100%;' name='orderID'><a href=\"http://localhost:9999/animeShop/thanks\" </a>  Order </button>");
                                out.println("<form />");

                                if (request.getParameter("orderID") != null) {
                                    out.println("<script type=\"text/javascript\">");
                                    out.println("location.replace('http://localhost:9999/animeShop/thanks')");
                                    // out.println("alert('Thank you for your purchase!');");
                                    out.println("</script>");
                                }


                            out.println("<br>");
                            out.println("<br>");
                            if (totalOrderValue >= 100){
                                out.println("<strong> You have hit the S$ 100 minimum amount to be eligible for free shipping! </strong>");
                            }
                            else{
                                totalOrderValueWShip = totalOrderValue + 5;
                                out.println("<strong> You have not hit the S$ 100 minimum amount to be eligible for free shipping.  </strong>");
                            }
                            out.println("<br>");
                            out.println("<br>");
                            out.println("<span> We accept </span>");
                            
                            out.println("<div class='container'>");
                                out.println("<div class='row'>");
                                    out.println("<div class='col'>");
                                    out.println("<img src='" + request.getContextPath() + "/assets/img/mastercard.jpg" + "' style='width: 45%; height:30px;' alt='image'>");
                                    out.println("</div>"); 
                                    out.println("<div class='col'>");
                                    out.println("<img src='" + request.getContextPath() + "/assets/img/visa.png" + "' style='width: 45%; height:30px;' alt='image'>");
                                    out.println("</div>"); 
                                    out.println("<div class='col'>");
                                    out.println("<img src='" + request.getContextPath() + "/assets/img/americanExpress.png" + "' style='width: 45%; height:30px;' alt='image'>");
                                    out.println("</div>"); 
                                    out.println("<div class='col'>");
                                    out.println("</div>"); 
                                out.println("</div>");
                            out.println("</div>");
                            out.println("<br>");
                            out.println("<span class='text-muted'> 30 days to return. </span>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("</div>");               
            out.println("</div>");



        } catch (Exception ex) {
            out.println("<p>Error: " + ex.getMessage() + "</p>");
            out.println("<p>Check Tomcat console for details.</p>");
            ex.printStackTrace();
        } // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK
          // 7)

        out.println("</body></html>");
        out.close();

    }

}