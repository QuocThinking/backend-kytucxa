package com.apache.hotelroom.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "hinh_anh_phong")
@Data
@Getter
@Setter
public class HinhAnhPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "phong_id", nullable = false)
    private Phongcanbo phongCanBo;

    @Column(name = "url", nullable = false)
    private String url;
}
