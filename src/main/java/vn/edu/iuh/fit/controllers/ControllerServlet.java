package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Log;
import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/control"})
public class ControllerServlet extends HttpServlet {
    private AccountRepository accountRepository = new AccountRepository();
    private LogRepository logRepository_A = new LogRepository();

    private RoleRepository roleRepository_A = new RoleRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("create")){
            create(request,response);
        } else if (action.equalsIgnoreCase("update")) {
            update(request,response);
        } else if (action.equalsIgnoreCase("delete")) {
            delete(request,response);
        } else {
            response.sendRedirect("account.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("login")) {
            login(request, response);
        } else if (action.equalsIgnoreCase("logOut")) {
            logout(request, response);
        } else if (action.equalsIgnoreCase("ViewLog")) {
            viewLog(request, response);
        } else if (action.equalsIgnoreCase("ViewRole")) {
            viewRole(request,response);
        } else if (action.equalsIgnoreCase("readRole")) {
            RoleI(request,response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account_id = request.getParameter("account_id");
        String password = request.getParameter("password");
        Account loginAccount = accountRepository.login(account_id, password);

        if (loginAccount != null) {

            String accountID = loginAccount.getAccount_id();
            LogRepository logRepository = new LogRepository();
            logRepository.updateLogin(logRepository.findLogByID(accountID));

            GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
            List<GrantAccess> grantAccessList = grantAccessRepository.findByAccount(accountID);

            RoleRepository roleRepository = new RoleRepository();
            List<String> roleIds = roleRepository.getRoleIdsfromGrantAccess(grantAccessList);

            HttpSession session = request.getSession(true);
            session.setAttribute("account_id", loginAccount.getAccount_id());
            session.setAttribute("role_ids", roleIds);

            if (roleIds.contains("admin")) {
                List<Account> allAccounts = accountRepository.getAllAccounts();
                request.setAttribute("dashboard", allAccounts);
                RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(request, response);

            } else {
                Account account = accountRepository.get1AccountByID(account_id);
                request.setAttribute("Account", account);
                RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
                dispatcher.forward(request, response);

            }
        } else {
            response.getWriter().println("Login failed. Please check and try again~");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("account_id");
        logRepository_A.updatelogout(logRepository_A.findLogByID(accountId));
        response.sendRedirect("index.jsp");
    }

    private void viewLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> logList = logRepository_A.findAll();
        request.setAttribute("logList", logList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewLog.jsp");
        dispatcher.forward(request, response);
    }

    private void viewRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Role> roles = roleRepository_A.getAllRoles();
        request.setAttribute("listRoles", roles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewRole.jsp");
        dispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response)  {
        String account_id = request.getParameter("account_id");
        String fullName = request.getParameter("full_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int status = Integer.parseInt(request.getParameter("status"));
        Account account = new Account(account_id,fullName,password,email,phone,status);
        accountRepository.insert(account);

    }

    private void update(HttpServletRequest request, HttpServletResponse response)  {
        String account_id = request.getParameter("account_id");
        String fullName = request.getParameter("full_name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int status = Integer.parseInt(request.getParameter("status"));
        Account account = new Account(account_id,fullName,password,email,phone,status);
        accountRepository.update(account);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
      String account_id = request.getParameter("account_id");
      Account account = accountRepository.get1AccountByID(account_id);
      accountRepository.delete(account);
    }
    private void RoleI(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String selectedRole = request.getParameter("selectedRole");
        List<Account> roleI = accountRepository.getAccountByID(selectedRole);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request,response);
    }

}