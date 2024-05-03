package kg.group8.neotour.controller;

import kg.group8.neotour.DTO.BookingDTO;
import kg.group8.neotour.entity.Booking;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.service.BookingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/bookings")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping()
    public ResponseEntity<?> book(@RequestBody BookingDTO bookingDTO) {
        try {
            return ResponseEntity.ok(bookingService.book(bookingDTO));
        } catch (EmptyFieldException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBooking(Long id) {
        try {
            return ResponseEntity.ok().body(bookingService.deleteBooking(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
