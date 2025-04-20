package dao;

import entity.Reservation;
import exception.ReservationException;
import util.DatabaseContext;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements IReservationService {
    private DatabaseContext dbContext;

    public ReservationServiceImpl(DatabaseContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        Reservation reservation = null;
        String sql = "SELECT * FROM Reservation WHERE ReservationID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reservation = new Reservation();
                    reservation.setReservationId(rs.getInt("ReservationID"));
                    reservation.setCustomerId(rs.getInt("CustomerID"));
                    reservation.setVehicleId(rs.getInt("VehicleID"));
                    reservation.setStartDate(rs.getDate("StartDate").toLocalDate());
                    reservation.setEndDate(rs.getDate("EndDate").toLocalDate());
                    reservation.setTotalCost(rs.getDouble("TotalCost"));
                    reservation.setStatus(rs.getString("Status"));
                }
            }
        } catch (SQLException | IOException e) {
            throw new ReservationException("Error fetching reservation: " + e.getMessage());
        }
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsByCustomerId(int customerId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation WHERE CustomerID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setReservationId(rs.getInt("ReservationID"));
                    reservation.setCustomerId(rs.getInt("CustomerID"));
                    reservation.setVehicleId(rs.getInt("VehicleID"));
                    reservation.setStartDate(rs.getDate("StartDate").toLocalDate());
                    reservation.setEndDate(rs.getDate("EndDate").toLocalDate());
                    reservation.setTotalCost(rs.getDouble("TotalCost"));
                    reservation.setStatus(rs.getString("Status"));
                    reservations.add(reservation);
                }
            }
        } catch (SQLException | IOException e) {
            throw new ReservationException("Error fetching reservations: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("ReservationID"));
                reservation.setCustomerId(rs.getInt("CustomerID"));
                reservation.setVehicleId(rs.getInt("VehicleID"));
                reservation.setStartDate(rs.getDate("StartDate").toLocalDate());
                reservation.setEndDate(rs.getDate("EndDate").toLocalDate());
                reservation.setTotalCost(rs.getDouble("TotalCost"));
                reservation.setStatus(rs.getString("Status"));
                list.add(reservation);
            }
        } catch (SQLException | IOException e) {
            throw new ReservationException("Error fetching all reservations: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void createReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getCustomerId());
            stmt.setInt(2, reservation.getVehicleId());
            stmt.setDate(3, Date.valueOf(reservation.getStartDate()));
            stmt.setDate(4, Date.valueOf(reservation.getEndDate()));
            stmt.setDouble(5, reservation.getTotalCost());
            stmt.setString(6, reservation.getStatus());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new ReservationException("Error creating reservation: " + e.getMessage());
        }
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE Reservation SET CustomerID = ?, VehicleID = ?, StartDate = ?, EndDate = ?, TotalCost = ?, Status = ? WHERE ReservationID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getCustomerId());
            stmt.setInt(2, reservation.getVehicleId());
            stmt.setDate(3, Date.valueOf(reservation.getStartDate()));
            stmt.setDate(4, Date.valueOf(reservation.getEndDate()));
            stmt.setDouble(5, reservation.getTotalCost());
            stmt.setString(6, reservation.getStatus());
            stmt.setInt(7, reservation.getReservationId());
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new ReservationException("Error updating reservation: " + e.getMessage());
        }
    }

    @Override
    public void cancelReservation(int reservationId) {
        String sql = "DELETE FROM Reservation WHERE ReservationID = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new ReservationException("Error canceling reservation: " + e.getMessage());
        }
    }
}
