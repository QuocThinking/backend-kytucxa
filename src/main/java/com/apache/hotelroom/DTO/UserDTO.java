package com.apache.hotelroom.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.apache.hotelroom.model.UserRole;

@Data
@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String tenNhanVien;
    private String userName;
    private UserRole rules;

}
