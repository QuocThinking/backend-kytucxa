package com.apache.hotelroom.service;

import com.apache.hotelroom.DTO.PhongCanBoDTO;
import com.apache.hotelroom.exception.CanBoAlreadyExistsException;
import com.apache.hotelroom.model.Canbo;
import com.apache.hotelroom.model.Phongcanbo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PhongCanBoService {
    List<Phongcanbo> getAllRooms();

    Page<PhongCanBoDTO> findByTangId(long tangId, Pageable pageable);

    Optional<PhongCanBoDTO> findByRoomId(Integer id);

    Canbo addCanBoToPhong(int phongId, Canbo canbo) throws CanBoAlreadyExistsException;
}
