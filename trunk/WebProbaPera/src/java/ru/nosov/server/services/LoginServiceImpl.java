/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nosov
 */
public class LoginServiceImpl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        // Print a simple HTML page including a <script> tag referencing your GWT module as the response
        PrintWriter writer = resp.getWriter();
        writer.append("<html><head>")
            .append("<script type=\"text/javascript\" src=\"sample/sample.nocache.js\"></script>")
            .append("</head><body><p>Hello, world!</p></body></html>");
   }
}