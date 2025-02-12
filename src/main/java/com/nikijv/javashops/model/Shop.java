package com.nikijv.javashops.model;

import com.nikijv.javashops.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String site;
    private Category category;
    private String description;
}
