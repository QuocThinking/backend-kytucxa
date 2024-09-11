package com.apache.hotelroom.controller;

import com.apache.hotelroom.model.Phongcanbo;
import com.apache.hotelroom.service.PhongCanBoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phong")

public class PhongCanBoController {
    @Autowired
    private PhongCanBoService phongCanBoService;

    @GetMapping("/danhsach")
    public List<Phongcanbo> getAllRooms() {
        return phongCanBoService.getAllRooms();
    }
}
