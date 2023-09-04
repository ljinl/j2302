package com.softeem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInfo {
    private List<OrderItem> orderItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem{
        private Long goodsId;
        private Long count;

        public DeductGoodsInventory toDeductGoodsInventory(){
            return new DeductGoodsInventory(
                    this.goodsId,
                    this.count
            );
        }


    }
}
