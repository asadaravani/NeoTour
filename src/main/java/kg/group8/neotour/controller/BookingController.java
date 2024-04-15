package kg.group8.neotour.controller;

import kg.group8.neotour.DTO.BookingDTO;
import kg.group8.neotour.entity.Booking;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("")
@RequestMapping("api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok().body(bookingService.getAllBookings());
    }

    @PostMapping()
    public ResponseEntity<?> book(@RequestBody BookingDTO bookingDTO) throws EmptyFieldException {
        try{
            return ResponseEntity.ok().body(bookingService.book(bookingDTO));
        }catch (EmptyFieldException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping()
    public ResponseEntity<?> deleteBooking(Long id) throws ProductNotFoundException {
        try{
            return ResponseEntity.ok().body(bookingService.deleteBooking(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
