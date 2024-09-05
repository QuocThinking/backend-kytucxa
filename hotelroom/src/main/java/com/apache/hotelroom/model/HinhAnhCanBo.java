package com.apache.hotelroom.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hinh_anh_can_bo")
@Data
@Getter
@Setter
public class HinhAnhCanBo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "canbo_id", nullable = false)
    private Canbo canBo;

    @Column(name = "url", nullable = false)
    private String url;
}
