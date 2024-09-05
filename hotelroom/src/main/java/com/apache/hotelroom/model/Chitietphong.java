package com.apache.hotelroom.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chi_tiet_phong")
@Data
@Getter
@Setter
public class Chitietphong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "phong_id", nullable = false)
    private Phongcanbo phongCanBo;

    @Column(name = "giuong")
    private Integer giuong;

    @Column(name = "tivi")
    private Integer tivi;

    @Column(name = "tu")
    private Integer tu;

    @Column(name = "nha_tam")
    private Integer nhaTam;

    // Getters and Setters

}
