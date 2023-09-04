package com.softeem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 扣减商品库存的Vo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeductGoodsInventory {
    private Long goodsId;
    private Long count;

}
