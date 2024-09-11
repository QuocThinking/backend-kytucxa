package com.apache.hotelroom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "canbo_id")
    private Canbo canBo;

    @OneToMany(mappedBy = "phongCanBo")
    @JsonManagedReference
    private List<HinhAnhPhong> hinhAnhPhongs;

    @ManyToOne
    @JoinColumn(name = "tang_id")
    @JsonBackReference
    private Tang tang;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonBackReference
    private Building building;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status = RoomStatus.VACANT;

    // Getters and Setters
}

