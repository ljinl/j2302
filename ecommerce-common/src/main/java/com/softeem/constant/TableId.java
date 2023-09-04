package com.softeem.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author LJL
 * @Date 2023:08:22:16:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableId {

    private List<Id> ids;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id{
        private Long id;
    }
}
