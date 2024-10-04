package com.apache.hotelroom.controller;

import com.apache.hotelroom.DTO.PhongCanBoDTO;
import com.apache.hotelroom.DTO.UserDTO;
import com.apache.hotelroom.model.Phongcanbo;
import com.apache.hotelroom.model.User;
import com.apache.hotelroom.service.PhongCanBoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/phong")
@CrossOrigin("*")
public class PhongCanBoController {
    @Autowired
    private PhongCanBoService phongCanBoService;

    @GetMapping("/danhsach")
    public List<Phongcanbo> getAllRooms() {
        return phongCanBoService.getAllRooms();
    }

    @GetMapping("/danhsach/{roomId}")
    public ResponseEntity<Optional<PhongCanBoDTO>> getUserById(@PathVariable Integer roomId) {
        Optional<PhongCanBoDTO> phongcanbo = phongCanBoService.findByRoomId(roomId);
        return ResponseEntity.ok(phongcanbo);

    }

    @GetMapping("/tang/{tangId}")
    public ResponseEntity<Page<PhongCanBoDTO>> getPhongByTangId(
            @PathVariable long tangId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PhongCanBoDTO> result = phongCanBoService.findByTangId(tangId, pageable);
        return ResponseEntity.ok(result);
    }
}
