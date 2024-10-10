package com.apache.hotelroom.repository;

import com.apache.hotelroom.model.HinhAnhCanBo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HinhAnhCanBoRepository extends JpaRepository<HinhAnhCanBo, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM HinhAnhCanBo h WHERE h.canBo.id = :canboId")
    void deleteAllByCanBoId(@Param("canboId") Integer canboId);
}
