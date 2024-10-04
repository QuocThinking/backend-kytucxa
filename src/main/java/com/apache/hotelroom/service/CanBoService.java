package com.apache.hotelroom.service;

import java.util.Optional;

import com.apache.hotelroom.DTO.CanBoDTO;
import com.apache.hotelroom.model.Canbo;

public interface CanBoService {
    // CanBoDTO addCanBoToPhong(String tenPhong, Integer tangId, Integer buildingId,
    // CanBoDTO canBoDTO);
    CanBoDTO addCanBoToPhong(Integer phongId, CanBoDTO canBoDTO);

    Optional<Canbo> getCanBoById(Integer id);
}
