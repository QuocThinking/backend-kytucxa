package com.apache.hotelroom.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
public class CanBoDTO {

    private String tenCanBo;
    private Date ngayDen;
    private Date ngayDi;
    private List<String> hinhAnhCanBo;


}
