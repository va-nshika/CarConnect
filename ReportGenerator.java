package service;

import dao.IReservationService;
import dao.IVehicleService;
import entity.Reservation;
import entity.Vehicle;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportGenerator {
    private IReservationService reservationService;
    private IVehicleService vehicleService;

    public ReportGenerator(IReservationService reservationService, IVehicleService vehicleService) {
        this.reservationService = reservationService;
        this.vehicleService = vehicleService;
    }

    /** 
     * Fetches all reservations for a given customer.
     */
    public List<Reservation> getReservationHistory(int customerId) {
        return reservationService.getReservationsByCustomerId(customerId);
    }

    /**
     * Computes how many times each vehicle has been reserved.
     * <p>Note: Assumes your IReservationService has a method getAllReservations().</p>
     */
    public Map<Integer, Long> getVehicleUtilization() {
        List<Reservation> allRes = reservationService.getAllReservations();
        // Map vehicleId → number of reservations
        return allRes.stream()
                     .collect(Collectors.groupingBy(Reservation::getVehicleId, Collectors.counting()));
    }

    /**
     * Sums up total revenue across all reservations.
     * <p>Note: Assumes your IReservationService has a method getAllReservations().</p>
     */
    public double calculateTotalRevenue() {
        return reservationService.getAllReservations()
                                 .stream()
                                 .mapToDouble(Reservation::getTotalCost)
                                 .sum();
    }
}
