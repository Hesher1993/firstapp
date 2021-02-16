package ru.geekbrains.hesher.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.hesher.servlet.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "TestServlet", urlPatterns = "/hello")
public class TestServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TestServlet.class);

    private transient ServletConfig config;

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("send txt to client");
        String response = ProductService.getProducts(10).toString();
        resp.setContentType("text/html");
        resp.getWriter().printf("<h1>Hello, buddy! List of products that you requested: %s </h1>", response);
        resp.getWriter().close();
        //resp.getWriter().println(ProductService.getProducts(5));

//        PrintWriter out = resp.getWriter();
//        resp.setContentType("text/html");
//        out.println("<html><body><h1>" + infoParam + "</h1></body></html>");
//        out.close();
    }
}

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        logger.info("New Get request with includes");
//
//        getServletContext().getRequestDispatcher("/header.html").include(req, resp);
//        resp.getWriter().println("<p>Response body from servlet</p>");
//        getServletContext().getRequestDispatcher("/footer.html").include(req, resp);
//    }
//}
