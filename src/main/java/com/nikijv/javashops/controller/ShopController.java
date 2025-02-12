package com.nikijv.javashops.controller;

import com.nikijv.javashops.category.Category;
import com.nikijv.javashops.dto.ShopDto;
import com.nikijv.javashops.model.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {
    List<Shop> shops = new ArrayList<>(List.of(
            new Shop("N1", "A1", "T1", "E1", "S1", Category.Grocery, "D1"),
            new Shop("N2", "A2", "T2", "E2", "S2", Category.Hardware, "D2"),
            new Shop("N3", "A3", "T3", "E3", "S3", Category.Sport, "D3")
    ));
    List<ShopDto> dtos = new ArrayList<>(List.of(
            new ShopDto("N1", "T1", "E1", "S1", "A1", Category.Grocery),
            new ShopDto("N2", "T2", "E2", "S2", "A2", Category.Hardware),
            new ShopDto("N3", "T3", "E3", "S3", "A3", Category.Sport)
    ));

@GetMapping("/home")
public String home(Model model) {
    model.addAttribute("shops", dtos);
    return "home";
}

    @GetMapping("/addEntry")
    public String add() {
        return "add";
    }

    @PostMapping("/addToList")
    public String add(@RequestParam("name") String name
            , @RequestParam("address") String address
            , @RequestParam("phone") String phone
            , @RequestParam("email") String email
            , @RequestParam("site") String site
            , @RequestParam("category") Category category
            , @RequestParam("description") String description) throws Exception {
        if(shops.stream().anyMatch(s -> s.getEmail().equals(email))){
            throw new Exception("Such email is already occupied");
        }
        shops.add(new Shop(name, address, phone, email, site, category, description));
        dtos.add(new ShopDto(name, phone, email, site, address, category));
        return "redirect:/home";
    }

    @PostMapping("/select")
    public String select(@RequestParam("email") String email, Model model) {
        Shop selectedShop = shops.stream()
                .filter(b -> b.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        model.addAttribute("shop", selectedShop);
        return "selected";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("email") String email, Model model) {
        Shop selectedShop = shops.stream()
                .filter(b -> b.getEmail().equals(email))
                .findFirst()
                .orElse(null);

        if(selectedShop != null) {
            shops.remove(selectedShop);
        }

        dtos.stream()
                .filter(b -> b.getEmail().equals(email))
                .findFirst().ifPresent(selectedDto -> dtos.remove(selectedDto));

        model.addAttribute("shop", selectedShop);
        return "edited";
    }

    @PostMapping("/editToList")
    public String edit(@RequestParam("name") String name
            , @RequestParam("address") String address
            , @RequestParam("phone") String phone
            , @RequestParam("email") String email
            , @RequestParam("site") String site
            , @RequestParam("category") Category category
            , @RequestParam("description") String description) throws Exception {
        if(shops.stream().anyMatch(s -> s.getEmail().equals(email))){
            throw new Exception("Such email is already occupied");
        }
        shops.add(new Shop(name, address, phone, email, site, category, description));
        dtos.add(new ShopDto(name, phone, email, site, address, category));
        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("email") String email) {
        shops.stream()
                .filter(b -> b.getEmail().equals(email))
                .findFirst()
                .ifPresent(selectedShop -> shops.remove(selectedShop));

        dtos.stream()
                .filter(b -> b.getEmail().equals(email))
                .findFirst().ifPresent(selectedDto -> dtos.remove(selectedDto));

        return "redirect:/home";
    }

    @PostMapping("/filterByName")
    public String filterByName(@RequestParam("name") String name, Model model) {
        List<ShopDto> filteredDtos = dtos.stream()
                .filter(d -> d.getName().equals(name))
                .toList();
        model.addAttribute("shops", filteredDtos);
        return "home";
    }

    @PostMapping("/filterByCategory")
    public String filterByCategory(@RequestParam("category") Category category, Model model) {
        List<ShopDto> filteredDtos = dtos.stream()
                .filter(d -> d.getCategory() == category)
                .toList();
        model.addAttribute("shops", filteredDtos);
        return "home";
    }

    @PostMapping("/filterByAddress")
    public String filterByAddress(@RequestParam("address") String address, Model model) {
        List<ShopDto> filteredDtos = dtos.stream()
                .filter(d -> d.getAddress().equals(address))
                .toList();
        model.addAttribute("shops", filteredDtos);
        return "home";
    }
}


