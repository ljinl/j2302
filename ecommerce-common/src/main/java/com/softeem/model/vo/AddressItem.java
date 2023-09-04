package com.softeem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LJL
 * @Date 2023:08:22:16:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressItem {
    private Long userId;
    private String username;
    private String phone;
    private String addressDetail;

}
