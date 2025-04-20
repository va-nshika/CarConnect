package main;

import dao.*;
import entity.Admin;
import entity.Customer;
import service.AuthenticationService;
import service.ReportGenerator;
import util.DatabaseContext;

import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseContext dbContext = new DatabaseContext("db.properties");

        // Initialize services
        ICustomerService customerService = new CustomerServiceImpl(dbContext);
        IVehicleService vehicleService = new VehicleServiceImpl(dbContext);
        IReservationService reservationService = new ReservationServiceImpl(dbContext);
        IAdminService adminService = new AdminServiceImpl(dbContext);

        // Auth & Report services
        AuthenticationService authService = new AuthenticationService(customerService, adminService);
        ReportGenerator reportGenerator = new ReportGenerator(reservationService, vehicleService);

        System.out.println("=== Welcome to CarConnect ===");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Customer Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Username: ");
                        String cUser = scanner.nextLine();
                        System.out.print("Password: ");
                        String cPass = scanner.nextLine();
                        Customer customer = authService.authenticateCustomer(cUser, cPass);
                        System.out.println("Welcome, " + customer.getName() + "!");
                        // TODO: Add customer features menu here
                    }
                    case 2 -> {
                        System.out.print("Username: ");
                        String aUser = scanner.nextLine();
                        System.out.print("Password: ");
                        String aPass = scanner.nextLine();
                        Admin admin = authService.authenticateAdmin(aUser, aPass);
                        System.out.println("Welcome Admin: " + admin.getFirstName() + "!");
                        // TODO: Add admin features menu here
                    }
                    case 3 -> {
                        System.out.println("Thank you for using CarConnect. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
