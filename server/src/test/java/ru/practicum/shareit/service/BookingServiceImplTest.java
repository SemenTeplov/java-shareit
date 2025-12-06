//package src.ru.practicum.shareit.service;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import ru.practicum.shareit.booking.dao.BookingRepository;
//import ru.practicum.shareit.booking.dto.BookingDataDto;
//import ru.practicum.shareit.booking.dto.BookingDto;
//import ru.practicum.shareit.booking.service.BookingServiceImpl;
//import ru.practicum.shareit.item.service.ItemService;
//
//import ru.practicum.shareit.user.service.UserService;
//import src.ru.practicum.shareit.TestData;
//
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class BookingServiceImplTest {
//    @Mock
//    private BookingRepository bookingRepository;
//
//    @Mock
//    private ItemService itemService;
//
//    @Mock
//    UserService userService;
//
//    @InjectMocks
//    private BookingServiceImpl service;
//
//    @Test
//    void addBooking_ShouldSuccessfullyCreateBooking() {
//        BookingDataDto bookingDto = TestData.getBookingDataDto();
//
//        when(userService.get(1L)).thenReturn(TestData.getUserDto());
//        when(itemService.get(1L)).thenReturn(TestData.getItemDto());
//        when(bookingRepository.save(TestData.getBookingWithoutId())).thenReturn(TestData.getBookingWithoutId());
//        when(userService.get(1L)).thenReturn(TestData.getUserDto());
//        when(itemService.get(1L)).thenReturn(TestData.getItemDto());
//
//        BookingDto returnBookingDto = service.create(bookingDto, 1L);
//
//        Assertions.assertEquals(bookingDto.getItemId(), returnBookingDto.getItem().getId());
//        Assertions.assertEquals(bookingDto.getStatus(), returnBookingDto.getStatus());
//
//        verify(userService, times(2)).get(1L);
//        verify(itemService, times(2)).get(1L);
//        verify(bookingRepository, times(1)).save(TestData.getBookingWithoutId());
//    }
//}
