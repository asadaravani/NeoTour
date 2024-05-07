package kg.group8.neotour.service.Impl;

import kg.group8.neotour.dto.BookingDto;
import kg.group8.neotour.entity.Booking;
import kg.group8.neotour.entity.Product;
import kg.group8.neotour.exception.EmptyFieldException;
import kg.group8.neotour.repository.BookingRepository;
import kg.group8.neotour.service.BookingService;
import kg.group8.neotour.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;
    ProductService productService;

    @Override
    public List<BookingDto> getAllBookings() {
        return mapBookingListToDto(bookingRepository.findAll());
    }

    @Override
    public String book(BookingDto bookingDTO) {
        if (bookingDTO.getPhoneNumber().isEmpty()) {
            throw new EmptyFieldException("Phone number is required");
        }
        Product product = productService.getById(bookingDTO.getProductId());
        Booking booking = new Booking();
        booking.setPhoneNumber(bookingDTO.getPhoneNumber());
        booking.setAmountOfPeople(bookingDTO.getAmountOfPeople());
        booking.setComment(bookingDTO.getComment());
        booking.setProduct(product);
        product.setOrderCount(
                product.getOrderCount() == null ? BigDecimal.ONE
                        : product.getOrderCount().add(BigDecimal.ONE)
        );
        bookingRepository.save(booking);
        return "Booking successful";
    }

    @Override
    public String deleteBooking(Long id) {
        bookingRepository.deleteById(id);
        return "Booking successfully deleted";
    }
    private List<BookingDto> mapBookingListToDto(List<Booking> bookings) {
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDto bookingDto = new BookingDto();
            bookingDto.setProductId(booking.getProduct().getId());
            bookingDto.setAmountOfPeople(booking.getAmountOfPeople());
            bookingDto.setComment(booking.getComment());
            bookingDto.setPhoneNumber(booking.getPhoneNumber());
            bookingDto.setAmountOfPeople(booking.getAmountOfPeople());
            bookingDtos.add(bookingDto);
        }
        return bookingDtos;
    }
}
