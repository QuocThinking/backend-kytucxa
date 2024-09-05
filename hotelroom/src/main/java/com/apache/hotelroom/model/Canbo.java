package com.apache.hotelroom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "can_bo")
@Data
@Getter
@Setter

public class Canbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_can_bo", nullable = false)
    private String tenCanBo;

    @Column(name = "ngay_den")
    private Date ngayDen;

    @Column(name = "ngay_di")
    private Date ngayDi;

    @ManyToOne
    @JoinColumn(name = "phong_id")
    private Phongcanbo phongCanBo;

    @OneToMany(mappedBy = "canBo")
    private List<HinhAnhCanBo> hinhAnhCanBos;
}
