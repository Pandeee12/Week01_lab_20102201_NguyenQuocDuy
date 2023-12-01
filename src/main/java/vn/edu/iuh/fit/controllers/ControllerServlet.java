package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.models.Logs;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repositories.AccountRepositories;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/controller"})
public class ControllerServlet extends HttpServlet {
    private AccountRepositories AccRepo = new AccountRepositories();
    private GrantAccessRepository grantRepo = new GrantAccessRepository();
    private RoleRepository roleRepo = new RoleRepository();
    private LogRepository logRep = new LogRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            resp.getWriter().println("Action is missing!");
            return;
        }

        HttpSession session = req.getSession(false);

        if (session != null || "login".equals(action)) {
            switch (action) {
                case "login":
                    login(req, resp);
                    break;
                case "Create":
                    CRUD(req, resp, "Create");
                    break;
                case "Delete":
                    CRUD(req, resp, "Delete");
                    break;
                case "Permission":
                    permission(req, resp);
                    break;
                case "Decentralization":
                    decentralization(req, resp);
                    break;
                case "ViewRole":
                    viewRole(req, resp);
                    break;
                case "ViewLog":
                    viewLog(req, resp);
                    break;
                case "UpdateGrant":
                    CRUD(req, resp, "UpdateGrant");
                    break;
                case "ReadRole":
                    CRUD(req, resp, "ReadRole");
                    break;
                case "LogOut":
                    logOut(req, resp);
                    break;
                default:
                    resp.getWriter().println("Invalid action!");
            }
        } else {
            resp.getWriter().println("Please login!");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account_id = req.getParameter("account_id");
        String password = req.getParameter("password");
        Account authenticatedAccount = AccRepo.authenticateUser(account_id, password);

        if (authenticatedAccount != null) {
            String accountId = authenticatedAccount.getAccountId();
            LogRepository logRepo = new LogRepository();

            logRepo.logLogin(accountId);

            GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
            List<GrantAccess> grantAccessList = grantAccessRepository.findByAccount(accountId);

            List<String> roleIds = getRoleIdsFromGrantAccess(grantAccessList);

            HttpSession session = req.getSession(true);
            session.setAttribute("account_id", authenticatedAccount.getAccountId());
            session.setAttribute("role_ids", roleIds);

            if (roleIds.contains("admin")) {
                List<Account> allAccounts = AccRepo.getAllAccount();
                req.setAttribute("dashboard", allAccounts);
                RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(req, resp);
            } else {
                Account acc = AccRepo.getAccountById(accountId);
                req.setAttribute("ACCOUNT", acc);
                RequestDispatcher dispatcher = req.getRequestDispatcher("account.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            resp.getWriter().println("Login failed. Please check your credentials.");
        }
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String accountId = (String) session.getAttribute("account_id");

            LogRepository logRepo = new LogRepository();
            logRepo.logLogout(accountId);

            session.invalidate();
        }

        resp.sendRedirect("index.jsp");
    }


    private void permission(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
        List<GrantAccess> grantList = grantAccessRepository.getAllGrant();
        req.setAttribute("listGrant", grantList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("permission.jsp");
        dispatcher.forward(req, resp);
    }

    private void decentralization(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Account> accounts = AccRepo.getAllAccount();
        List<Role> roles = roleRepo.getAllRole();

        req.setAttribute("listAccounts", accounts);
        req.setAttribute("listRoles", roles);

        RequestDispatcher dispatcher = req.getRequestDispatcher("decentralization.jsp");
        dispatcher.forward(req, resp);
    }

    private void viewRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Role> roles = roleRepo.getAllRole();
        req.setAttribute("listRoles", roles);
        RequestDispatcher dispatcher = req.getRequestDispatcher("viewRole.jsp");
        dispatcher.forward(req, resp);
    }

    private void viewLog(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Logs> logList = logRep.getAllLogs();
        req.setAttribute("logList", logList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("viewLog.jsp");
        dispatcher.forward(req, resp);

    }

    private void CRUD(HttpServletRequest req, HttpServletResponse resp, String action) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        String selectedAcc = req.getParameter("selectedAcc");
        String selectedRole = req.getParameter("selectedRole");
        if (session != null) {
            switch (action) {
                case "Create":
                    String accountID = req.getParameter("account_id");
                    String fullName = req.getParameter("full_name");
                    String password = req.getParameter("password");
                    String email = req.getParameter("email");
                    String phone = req.getParameter("phone");
                    int status = Integer.parseInt(req.getParameter("status"));

                    Account acc_new = new Account(accountID, fullName, password, email, phone, status);

                    // Thử thêm tài khoản
                    Boolean result_add_account = AccRepo.addAccount(acc_new);

                    if (result_add_account) {
                        List<Account> allAccounts = AccRepo.getAllAccount();
                        req.setAttribute("dashboard", allAccounts);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        resp.getWriter().println("Create failed !!!");
                    }
                    break;

                case "Delete":
                    String uidToDelete = req.getParameter("account_id_delete");
                    Boolean resultDeleteAccount = AccRepo.deleteAccount(uidToDelete);
                    if (resultDeleteAccount) {
                        List<Account> allAccounts = AccRepo.getAllAccount();
                        req.setAttribute("dashboard", allAccounts);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        resp.getWriter().println("Deletion failed !!!");
                    }
                    break;

                case "UpdateGrant":
                    if (selectedAcc != null && selectedRole != null) {
                        boolean updateSuccessful = grantRepo.updateOrInsertGrantAccess(selectedAcc, selectedRole);
                        if (updateSuccessful) {
                            permission(req, resp);
                        } else {
                            resp.getWriter().println("Failed to update or insert grant access.");
                        }
                    } else {
                        resp.getWriter().println("Invalid input parameters.");
                    }
                    break;
                case "ReadRole":
                    if (selectedRole != null) {
                        List<Account> roleData = AccRepo.getAllAccountByRole_id(selectedRole);
                        req.setAttribute("roleData", roleData);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("roleData.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        resp.getWriter().println("Invalid input parameters.");
                    }
                    break;
                default:
                    resp.getWriter().println("Invalid action!");
                    break;

            }

        } else {
            resp.getWriter().println("Please login !!!");
        }
    }


    private List<String> getRoleIdsFromGrantAccess(List<GrantAccess> grantAccessList) {
        List<String> roleIds = new ArrayList<>();

        // Duyệt qua danh sách các đối tượng GrantAccess
        for (GrantAccess grantAccess : grantAccessList) {
            // Kiểm tra quy tắc để xác định roleId, ví dụ: nếu isGrant là 1, coi đó là vai trò admin
            if (grantAccess.getIsGrant() == 1) {
                roleIds.add(grantAccess.getRoleId());
            }
        }
        // Trả về danh sách roleId
        return roleIds;
    }
}
