package com.apache.hotelroom.controller;

import com.apache.hotelroom.DTO.CanBoDTO;
import com.apache.hotelroom.DTO.UserDTO;
import com.apache.hotelroom.model.Canbo;
import com.apache.hotelroom.model.User;
import com.apache.hotelroom.service.CanBoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/canbo")
@CrossOrigin("*")
public class CanBoController {

        @Autowired
        private CanBoService canBoService;

        @PostMapping("/add-room/{phongId}")
        public ResponseEntity<?> addCanBoToPhong(
                        @PathVariable Integer phongId,
                        @RequestBody CanBoDTO canBoDTO) {
                try {
                        CanBoDTO addedCanBo = canBoService.addCanBoToPhong(phongId, canBoDTO);
                        return ResponseEntity.ok().body(
                                        Map.of(
                                                        "message", "Đã thêm cán bộ thành công",
                                                        "canBo", addedCanBo));
                } catch (RuntimeException ex) {
                        return ResponseEntity.badRequest().body(
                                        Map.of(
                                                        "error", ex.getMessage()));
                }
        }

        @GetMapping("/danhsach/{canboId}")
        public ResponseEntity<Optional<Canbo>> getUserById(@PathVariable Integer canboId) {
                Optional<Canbo> canBo = canBoService.getCanBoById(canboId);

                if (canBo.isPresent()) {

                        return ResponseEntity.ok(canBo);
                } else {
                        return ResponseEntity.notFound().build();
                }
        }
}
