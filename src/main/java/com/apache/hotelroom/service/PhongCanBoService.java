package com.apache.hotelroom.service;

import com.apache.hotelroom.DTO.PhongCanBoDTO;
import com.apache.hotelroom.model.Phongcanbo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhongCanBoService
{
    List<Phongcanbo> getAllRooms();
    Page<PhongCanBoDTO> findByTangId(long tangId, Pageable pageable);
}
