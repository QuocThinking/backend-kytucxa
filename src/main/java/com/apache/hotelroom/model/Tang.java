package com.apache.hotelroom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "tang")
public class Tang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_tang")
    private String tenTang;

    @OneToMany(mappedBy = "tang")
    @JsonManagedReference
    private List<Phongcanbo> phongCanBos;
}
