package com.apache.hotelroom.service.serviceImpl;

import com.apache.hotelroom.DTO.CanBoDTO;
import com.apache.hotelroom.exception.CanBoAlreadyExistsException;
import com.apache.hotelroom.model.Canbo;
import com.apache.hotelroom.model.Phongcanbo;
import com.apache.hotelroom.model.RoomStatus;
import com.apache.hotelroom.repository.CanBoRepository;
import com.apache.hotelroom.repository.PhongCanBoRepository;
import com.apache.hotelroom.service.CanBoService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanBoImpl implements CanBoService {

    @Autowired
    private CanBoRepository canBoRepository;

    @Autowired
    private PhongCanBoRepository phongCanBoRepository;

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
}
