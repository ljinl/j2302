package com.softeem.entity;

import com.softeem.model.vo.SimpleGoodsInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * <h1>商品表实体类定义</h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcommerceGoods {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 商品类型
     */
    private String goodsCategory;

    /**
     * 品牌分类
     */
    private String brandCategory;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品名称
     */
    private String goodsPic;

    /**
     * 商品描述信息
     */
    private String goodsDescription;

    /**
     * 商品状态
     */
    private Integer goodsStatus;

    /**
     * 商品价格: 单位: 分、厘
     */
    private Integer price;

    /**
     * 总供应量
     */
    private Long supply;

    /**
     * 库存
     */
    private Long inventory;

    /**
     * 商品属性, json 字符串存储
     */
    private String goodsProperty;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public SimpleGoodsInfo toSimple(){
        return new SimpleGoodsInfo(
                this.id,
                this.price,
                this.goodsName,
                this.inventory
        );
    }
}
