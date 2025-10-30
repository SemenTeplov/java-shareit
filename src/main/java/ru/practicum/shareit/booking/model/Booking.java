package ru.practicum.shareit.booking.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Booking {
    private Long id;
    private LocalDate date;
    private Boolean isBooking;
    private String feedback;
}
