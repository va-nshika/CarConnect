package service;

import dao.ICustomerService;
import dao.IAdminService;
import entity.Customer;
import entity.Admin;
import exception.AuthenticationException;

public class AuthenticationService {
    private ICustomerService customerService;
    private IAdminService adminService;

    public AuthenticationService(ICustomerService customerService, IAdminService adminService) {
        this.customerService = customerService;
        this.adminService = adminService;
    }

    /**
     * Validates a customer’s credentials.
     * @throws AuthenticationException if username not found or password mismatch
     */
    public Customer authenticateCustomer(String username, String password) throws AuthenticationException {
        Customer cust = customerService.getCustomerByUsername(username);
        if (cust == null || !cust.authenticate(password)) {
            throw new AuthenticationException("Invalid customer username or password.");
        }
        return cust;
    }

    /**
     * Validates an admin’s credentials.
     * @throws AuthenticationException if username not found or password mismatch
     */
    public Admin authenticateAdmin(String username, String password) throws AuthenticationException {
        Admin adm = adminService.getAdminByUsername(username);
        if (adm == null || !adm.authenticate(password)) {
            throw new AuthenticationException("Invalid admin username or password.");
        }
        return adm;
    }
}
