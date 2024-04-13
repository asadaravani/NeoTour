package kg.group8.neotour.controller;

import kg.group8.neotour.DTO.BookingDTO;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@RequestMapping("api/booking")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> book(BookingDTO bookingDTO) throws EmptyFieldException {
        try{
            return ResponseEntity.ok().body(bookingService.book(bookingDTO));
        }catch (EmptyFieldException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("deleteBooking")
    public ResponseEntity<?> deleteBooking(Long id) throws ProductNotFoundException {
        try{
            return ResponseEntity.ok().body(bookingService.deleteBooking(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
