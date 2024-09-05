package com.apache.hotelroom.service;

import com.apache.hotelroom.model.Phongcanbo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhongCanBoService
{
    List<Phongcanbo> getAllRooms();
}
