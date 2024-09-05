package com.apache.hotelroom.service.serviceImpl;

import com.apache.hotelroom.model.Phongcanbo;
import com.apache.hotelroom.service.PhongCanBoService;
import com.apache.hotelroom.repository.PhongCanBoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PhongCanBoImpl implements PhongCanBoService {
    @Autowired
    private PhongCanBoRepository phongCanBoRepository;
    @Override
    public List<Phongcanbo> getAllRooms() {
        return phongCanBoRepository.findAll();
    }
}
