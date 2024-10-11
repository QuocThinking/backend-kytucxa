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
    public ResponseEntity<Optional<Canbo>> getCanBoById(@PathVariable Integer canboId) {
        Optional<Canbo> canBo = canBoService.getCanBoById(canboId);

        if (canBo.isPresent()) {

            return ResponseEntity.ok(canBo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @DeleteMapping("/remove-room/{canboId}")
    // public ResponseEntity<?> removeCanBoFromPhong(@PathVariable Integer canboId)
    // {
    // try {
    // // Gọi phương thức trong service để xóa cán bộ và cập nhật trạng thái phòng
    // canBoService.removeCanBoFromPhong(canboId);
    // return ResponseEntity.ok().body(
    // Map.of("message", "Đã xóa cán bộ và cập nhật trạng thái phòng thành công")
    // );
    // } catch (RuntimeException ex) {
    // return ResponseEntity.badRequest().body(
    // Map.of("error", "Lỗi trong quá trình xóa cán bộ: " + ex.getMessage())
    // );
    // }
    // }
    @DeleteMapping("/delete/{canboId}")
    public ResponseEntity<?> deleteCanBo(@PathVariable Integer canboId) {
        try {
            canBoService.deleteCanBo(canboId);
            return ResponseEntity.ok().body(Map.of("message", "Cán bộ đã được xóa thành công."));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }
}
