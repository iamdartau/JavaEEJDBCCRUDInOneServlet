package controllers;

import dao.DbManager;
import models.User;
import java.util.regex.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/main")
public class MainServlet extends javax.servlet.http.HttpServlet {

    private DbManager dbManager;

    public void init() throws ServletException {
        dbManager = new DbManager();
        dbManager.connect();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String act = request.getParameter("act");
        String uri ="";

        if (act.equals("adduser")){
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");

            dbManager.addUserToDB(new User(null,login,password,fullName));
            uri = "?page=adduser&success=1";
        }else if (act.equals("edituser")){

            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            Integer id = Integer.valueOf(request.getParameter("id"));
            dbManager.updateUserById(id, login, password, fullName);
            uri = "?page=detail.jsp?id=" + id +"&success=1";
        } else if (act.equals("delete")){
            Integer id = Integer.parseInt(request.getParameter("id"));
            dbManager.deleteUserFromDB(id);
        }

        response.sendRedirect("/" + uri);
//        request.getRequestDispatcher("/" + uri);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String page = request.getParameter("page");
        String jspPage = "home";

        if (page != null) {
            if (page.equals("home")) {
                jspPage ="home";
            } else if(page.equals("adduser")) {
                jspPage = "adduser";
            } else if(page.contains("detail?id")){
                jspPage = "detail";
            }
        }

        if (jspPage.equals("home")){
            ArrayList<User>users = dbManager.getAllUsers();
            request.setAttribute("users", users);

        }else if(jspPage.equals("detail")){
            int id = 0;
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(page);
            int start = 0;
            while (matcher.find(start)) {
                String value = page.substring(matcher.start(), matcher.end());
                id = Integer.parseInt(value);
                start = matcher.end();
            }

            User user = dbManager.getUserById(id);
            request.setAttribute("user", user);

            request.getRequestDispatcher("/views/" + jspPage + ".jsp?id=").forward(request,response);
        }

        request.getRequestDispatcher("/views/" + jspPage + ".jsp").forward(request,response);


    }
}
