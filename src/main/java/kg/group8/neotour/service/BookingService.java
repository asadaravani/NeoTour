package kg.group8.neotour.service;

import kg.group8.neotour.DTO.BookingDTO;
import kg.group8.neotour.entity.Booking;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    BookingRepository bookingRepository;
    public String book(BookingDTO bookingDTO) throws EmptyFieldException {
        if(isEmptyOrNull(bookingDTO.getPhoneNumber())){
            throw new EmptyFieldException("Phone number is required");
        }
        Booking booking = new Booking();
        booking.setPhoneNumber(bookingDTO.getPhoneNumber());
        return "Booking successful";
    }
    public String deleteBooking(Long id){
        Booking booking = new Booking();
        return "";
    }
    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }
}
