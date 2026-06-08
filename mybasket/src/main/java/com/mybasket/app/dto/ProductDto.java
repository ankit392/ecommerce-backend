package com.mybasket.app.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;

    @NotBlank(message = "product title is required")
    @Size(min=5,message = "must be greater thenn 5 characters")
    private String title;

    @NotBlank(message = "desc is required")
    private String description;

    @NotBlank(message = "short-desc is required")
    @Size(max = 50,message = "Short desc should be less then 50 characters")
    private String short_description;

    @Min(value = 1,message = "Price must be greater then 1")
    private double price;

    private boolean live;
    private boolean outOfstock;
    private String imageUrl;
}
