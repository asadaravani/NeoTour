package kg.group8.neotour.service;

import kg.group8.neotour.dto.BookingDto;

import java.util.List;

public interface BookingService {

    List<BookingDto> getAllBookings();

    String book(BookingDto bookingDTO);

    String deleteBooking(Long id);
}
