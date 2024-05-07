package kg.group8.neotour.service;

import kg.group8.neotour.DTO.BookingDTO;
import kg.group8.neotour.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();

    String book(BookingDTO bookingDTO);

    String deleteBooking(Long id);
}
