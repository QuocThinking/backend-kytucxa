package com.apache.hotelroom.repository;

import com.apache.hotelroom.model.Canbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanBoRepository extends JpaRepository<Canbo, Integer> {
    Optional<Canbo> findByTenCanBo(String tenCanBo);
}
