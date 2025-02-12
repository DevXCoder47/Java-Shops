package com.nikijv.javashops.dto;

import com.nikijv.javashops.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    private String name;
    private String phone;
    private String email;
    private String site;
    private String address;
    private Category category;
}
