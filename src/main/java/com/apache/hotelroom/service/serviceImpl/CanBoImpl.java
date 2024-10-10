package com.apache.hotelroom.service.serviceImpl;

import com.apache.hotelroom.DTO.CanBoDTO;
import com.apache.hotelroom.exception.CanBoAlreadyExistsException;
import com.apache.hotelroom.model.Canbo;
import com.apache.hotelroom.model.Phongcanbo;
import com.apache.hotelroom.model.RoomStatus;
import com.apache.hotelroom.repository.CanBoRepository;
import com.apache.hotelroom.repository.HinhAnhCanBoRepository;
import com.apache.hotelroom.repository.PhongCanBoRepository;
import com.apache.hotelroom.service.CanBoService;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanBoImpl implements CanBoService {

    @Autowired
    private CanBoRepository canBoRepository;
    @Autowired
    private PhongCanBoRepository phongCanBoRepository;

    @Autowired
    private HinhAnhCanBoRepository hinhAnhCanBoRepository;
    // @Override
    // public CanBoDTO addCanBoToPhong(String tenPhong, Integer tangId, Integer
    // buildingId, CanBoDTO canBoDTO) {
    // if(canBoRepository.findByTenCanBo(canBoDTO.getTenCanBo()).isPresent()){
    // throw new CanBoAlreadyExistsException("Tên người dùng đã tồn tại");
    // }
    //
    // Phongcanbo phongcanbo =
    // phongCanBoRepository.findByTenPhongAndTang_IdAndBuilding_Id(tenPhong, tangId,
    // buildingId)
    // .orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));
    //
    // Canbo canbo = new Canbo();
    // canbo.setTenCanBo(canBoDTO.getTenCanBo());
    // canbo.setNgayDen(canBoDTO.getNgayDen());
    // canbo.setNgayDi(canBoDTO.getNgayDi());
    //
    // Canbo savedCanBo = canBoRepository.save(canbo);
    //
    // phongcanbo.setCanBo(savedCanBo);
    // phongCanBoRepository.save(phongcanbo);
    //
    // CanBoDTO responseDTO = new CanBoDTO();
    // responseDTO.setTenCanBo(savedCanBo.getTenCanBo());
    // responseDTO.setNgayDen(savedCanBo.getNgayDen());
    // responseDTO.setNgayDi(savedCanBo.getNgayDi());
    //
    // return responseDTO;
    // }

    @Override
    public CanBoDTO addCanBoToPhong(Integer id, CanBoDTO canBoDTO) {
        if (canBoRepository.findByTenCanBo(canBoDTO.getTenCanBo()).isPresent()) {
            throw new CanBoAlreadyExistsException("Tên người dùng đã tồn tại");
        }

        Phongcanbo phongcanbo = phongCanBoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phòng không tồn tại"));

        if (phongcanbo.getCanBo() != null) {
            throw new RuntimeException("Phòng đã có người ở rồi. Bạn hãy thêm cán bộ vào phòng khác nhé.");
        }

        Canbo canbo = new Canbo();
        canbo.setTenCanBo(canBoDTO.getTenCanBo());
        canbo.setNgayDen(canBoDTO.getNgayDen());
        canbo.setNgayDi(canBoDTO.getNgayDi());

        Canbo savedCanBo = canBoRepository.save(canbo);

        phongcanbo.setCanBo(savedCanBo);
        phongcanbo.setStatus(RoomStatus.OCCUPIED);
        phongCanBoRepository.save(phongcanbo);

        CanBoDTO responseDTO = new CanBoDTO();
        responseDTO.setTenCanBo(savedCanBo.getTenCanBo());
        responseDTO.setNgayDen(savedCanBo.getNgayDen());
        responseDTO.setNgayDi(savedCanBo.getNgayDi());

        return responseDTO;
    }

    @Override
    public Optional<Canbo> getCanBoById(Integer id) {
        return canBoRepository.findById(id);
    }

    @Override
    public Canbo findById(Integer id) {
        return canBoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCanBoById(Integer id) {
        canBoRepository.deleteById(id);
    }

//    @Transactional
//    @Override
//    public void removeCanBoFromPhong(Integer canboId) {
//        // Lấy Canbo từ database (nếu tồn tại)
//        Canbo canbo = canBoRepository.findById(canboId)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy cán bộ"));
//
//        // Lấy PhongCanBo từ Canbo (nếu có)
//        Phongcanbo phongCanBo = canbo.getPhongCanBo();
//
//        // Xóa tất cả hình ảnh liên quan đến cán bộ
//        hinhAnhCanBoRepository.deleteAllByCanBoId(canboId);
//
//        // Cập nhật trạng thái của PhongCanBo thành VACANT (nếu PhongCanBo không null)
//        if (phongCanBo != null) {
//            phongCanBo.setStatus(RoomStatus.VACANT);
//            phongCanBo.setCanBo(null); // Xóa liên kết cán bộ khỏi phòng
//            phongCanBoRepository.save(phongCanBo); // Lưu lại trạng thái đã cập nhật
//        }
//
//        // Xóa Canbo khỏi database
//        canBoRepository.delete(canbo); // Sử dụng delete đối tượng Canbo
//    }

    @Override
    @Transactional
    public void deleteCanBo(Integer canboId) {
        Phongcanbo phongCanBo = phongCanBoRepository.findByCanBoId(canboId);
        if (phongCanBo != null) {
            throw new RuntimeException("Thông tin cán bộ này chưa được xóa trong phòng đang ở.");
        }

        // Nếu không, xóa cán bộ
        canBoRepository.deleteById(canboId);
    }
}
