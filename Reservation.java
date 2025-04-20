package entity;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int customerId;
    private int vehicleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    public Reservation(int reservationId, int customerId, int vehicleId, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    public Reservation() {}

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
