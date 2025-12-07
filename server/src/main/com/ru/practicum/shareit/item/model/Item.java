package ru.practicum.shareit.item.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name")
    @NotBlank(message = "Должно быть указано название")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Должно быть указано описание")
    private String description;

    @Column(name = "available")
    @NotNull(message = "Должен быть указан доступ")
    private Boolean available;
}
