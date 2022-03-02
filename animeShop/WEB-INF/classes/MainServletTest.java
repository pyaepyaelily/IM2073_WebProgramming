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

@WebServlet("/starttest") // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class MainServletTest extends HttpServlet {

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
        out.println("<head><title>Testing</title></head>");
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
        // out.write("<LINK REL='StyleSheet'
        // HREF='<%=request.getContextPath()%>/util/CSS/Style.css' TYPE='text/css'> ");

        out.println("<body>");
        out.println("<h1>Testing</h1>"); // says Hello

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
            String sqlStr = "select * from product";
            boolean results = stmt.execute(sqlStr);

            ResultSet rset = stmt.executeQuery(sqlStr); // Send the query to the server

            // Step 4: Process the query result set
            if (results) {
                rsCount++;
            }
            // getting sql data
            List<Object> dataItems = new ArrayList<Object>();
            List<Object> dataNames = new ArrayList<Object>();
            List<Object> dataDesc = new ArrayList<Object>();
            List<Object> dataPrice = new ArrayList<Object>();
            Dictionary geek = new Hashtable();
            while (rset.next()) {
                SQLData item = new SQLData(rset.getString("name"),
                        rset.getString("prod_desc"),
                        rset.getDouble("price"));
                dataItems.add(item);
                dataNames.add(rset.getString("name"));
                dataDesc.add(rset.getString("prod_desc"));
                dataPrice.add(rset.getDouble("price"));
            }

            // out.println("<div class='container' style='border:1px solid #cecece;'>");

            // int idNum = 0;
            // out.println("<form method='get'
            // action='http://localhost:9999/animeShop/query'> ");

            // out.println("<table class='table table-bordered'>");
            // out.println("<tr>");
            // for (int i = 0; i < dataItems.size(); i++) {
            // if (i >= 5 && i % 5 == 0) {
            // out.println("</tr><tr>");
            // }
            // idNum = i + 1;
            // out.println(" <td>" + dataItems.get(i)
            // + " <button type='submit' class='btn btn-outline-success' name='id' value=" +
            // "'" + idNum + "'"
            // + "> More Details</button> </td>");
            // }

            // out.println("</tr>");
            // out.println("</table>");
            // out.println("<form />");

            int idNum = 0;
            out.println("<form method='get' action='http://localhost:9999/animeShop/query'> ");

            // out.println("<table class='table table-bordered'>");
            out.println("<table>");
            out.println("<tr>");
            for (int i = 0; i < dataItems.size(); i++) {
                if (i >= 4 && i % 4 == 0) {
                    out.println("</tr><tr>");
                }
                idNum = i + 1;
                out.println("<td style='width: 16rem; padding: 23px;'>");
                out.println("<div class='card' style='width: 18rem;'");
                // out.println("<img src='suga.jpg' class='card-img-top' alt='photo'>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title'>" + dataNames.get(i) + "</h5>");
                out.println("<p class='card-text'>" + dataDesc.get(i) + "</p>");
                out.println("<p class='card-text'> $" + dataPrice.get(i) + "</p>");
                out.println("<button type='submit' class='btn btn-outline-success' name='id' value=" + "'" + idNum + "'"
                        + "> More Details</button>");
                out.println("</div>");
                out.println("</div>");
                out.println(" </td>");
            }

            out.println("</tr>");
            out.println("</table>");
            out.println("<form />");

            // WORKING ONE

            // out.println("<form method='get' action='eshoporder'>");
            // out.println("<table class='table table-bordered'>");
            // out.println("<tr>");
            // out.println("<th>Name</th>");
            // out.println("<th>PRICE</th>");
            // out.println("<th></th>");
            // out.println("</tr>");

            // while (rset.next()) {
            // out.println("<tr>");
            // out.println("<td> <p>" + rset.getString("name") + " </p> </td>");
            // out.println("<td>" + "$" + rset.getString("price") + "</p></td>");
            // out.print("<td> <button type='submit' class='btn btn-outline-success'
            // name='id' value=" + "'" + rset.getString("id") +"'" + "> More Details
            // </button></td>");
            // out.println("</tr>");
            // }
            // out.println("</table>");
            // out.println("</form>");

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