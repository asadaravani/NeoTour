package kg.group8.neotour.service;

import kg.group8.neotour.DTO.BookingDTO;
import kg.group8.neotour.entity.Booking;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.exception.ProductNotFoundException;
import kg.group8.neotour.repository.BookingRepository;
import kg.group8.neotour.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;
    ProductRepository productRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String book(BookingDTO bookingDTO) throws EmptyFieldException, ProductNotFoundException {
        if(bookingDTO.getPhoneNumber().isEmpty()){
            throw new EmptyFieldException("Phone number is required");
        }
        Optional<Product> optionalProduct = productRepository.findById(bookingDTO.getProductId());
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        Booking booking = new Booking();
        booking.setPhoneNumber(bookingDTO.getPhoneNumber());
        booking.setAmountOfPeople(bookingDTO.getAmountOfPeople());
        booking.setComment(bookingDTO.getComment());

        //set product
        booking.setProduct(product);

        //orderCount++;
        product.setOrderCount(
                product.getOrderCount() == null ? BigDecimal.ONE
                        : product.getOrderCount().add(BigDecimal.ONE)
        );

        bookingRepository.save(booking);
        productRepository.save(product);
        return "Booking successful";
    }
    public String deleteBooking(Long id){
        bookingRepository.deleteById(id);
        return "Booking successfully deleted";
    }
}
