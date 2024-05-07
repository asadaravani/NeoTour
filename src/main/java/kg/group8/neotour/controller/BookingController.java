package kg.group8.neotour.controller;

import kg.group8.neotour.dto.BookingDto;
import kg.group8.neotour.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping()
    public ResponseEntity<?> book(@RequestBody BookingDto bookingDTO) {
        return ResponseEntity.ok(bookingService.book(bookingDTO));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBooking(@RequestParam Long id) {
        return ResponseEntity.ok().body(bookingService.deleteBooking(id));
    }
}
