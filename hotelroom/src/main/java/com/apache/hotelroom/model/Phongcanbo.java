package com.apache.hotelroom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "phong_can_bo")
@Data
@Getter
@Setter
public class Phongcanbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_phong", nullable = false)
    private String tenPhong;

    @Column(name = "loai_phong")
    private String loaiPhong;

    @OneToMany(mappedBy = "phongCanBo")
    private List<Canbo> canBos;

    @OneToMany(mappedBy = "phongCanBo")
    private List<HinhAnhPhong> hinhAnhPhongs;

    @OneToOne(mappedBy = "phongCanBo")
    private Chitietphong chiTietPhong;

    // Getters and Setters
}

