package com.apache.hotelroom.service;

import com.apache.hotelroom.DTO.CanBoDTO;

public interface CanBoService {
    CanBoDTO addCanBoToPhong(String tenPhong, Integer tangId, Integer buildingId, CanBoDTO canBoDTO);
}
