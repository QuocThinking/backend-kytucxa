package com.apache.hotelroom.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.apache.hotelroom.model.Phongcanbo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class PhongCanBoDTO {
    private Integer id;
    private String tenPhong;
    private String loaiPhong;
    private String section;
    private String status;

    private Integer canBoId;
    private String tenCanBo; // Trích xuất từ Canbo nếu cần
    private String tenTang; // Trích xuất từ Tang
    private String tenBuilding;
    // Thêm danh sách URL của hình ảnh phòng
    private List<String> hinhAnhUrls;

    public static PhongCanBoDTO toDTO(Phongcanbo phongcanbo) {
        PhongCanBoDTO dto = new PhongCanBoDTO();
        dto.setId(phongcanbo.getId());
        dto.setTenPhong(phongcanbo.getTenPhong());
        dto.setLoaiPhong(phongcanbo.getLoaiPhong());
        dto.setSection(phongcanbo.getSection());
        dto.setStatus(phongcanbo.getStatus().toString());
        if (phongcanbo.getCanBo() != null) {
            dto.setCanBoId(phongcanbo.getCanBo().getId());
        }
        if (phongcanbo.getCanBo() != null) {
            dto.setTenCanBo(phongcanbo.getCanBo().getTenCanBo());
        }
        if (phongcanbo.getTang() != null) {
            dto.setTenTang(phongcanbo.getTang().getTenTang());
        }
        if (phongcanbo.getBuilding() != null) {
            dto.setTenBuilding(phongcanbo.getBuilding().getName());
        } // Lấy danh sách URL từ hinhAnhPhongs
        if (phongcanbo.getHinhAnhPhongs() != null) {
            List<String> hinhAnhUrls = phongcanbo.getHinhAnhPhongs().stream()
                    .map(hinhAnhPhong -> hinhAnhPhong.getUrl()) // Giả sử HinhAnhPhong có thuộc tính `url`
                    .collect(Collectors.toList());
            dto.setHinhAnhUrls(hinhAnhUrls);
        }

        return dto;
    }

}
