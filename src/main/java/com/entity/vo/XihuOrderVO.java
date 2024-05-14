package com.entity.vo;

import com.entity.XihuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 洗护订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xihu_order")
public class XihuOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "xihu_order_uuid_number")
    private String xihuOrderUuidNumber;


    /**
     * 洗护
     */

    @TableField(value = "xihu_id")
    private Integer xihuId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "xihu_order_true_price")
    private Double xihuOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "xihu_order_types")
    private Integer xihuOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "xihu_order_payment_types")
    private Integer xihuOrderPaymentTypes;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "yuyue_time")
    private Date yuyueTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getXihuOrderUuidNumber() {
        return xihuOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setXihuOrderUuidNumber(String xihuOrderUuidNumber) {
        this.xihuOrderUuidNumber = xihuOrderUuidNumber;
    }
    /**
	 * 设置：洗护
	 */
    public Integer getXihuId() {
        return xihuId;
    }


    /**
	 * 获取：洗护
	 */

    public void setXihuId(Integer xihuId) {
        this.xihuId = xihuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getXihuOrderTruePrice() {
        return xihuOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setXihuOrderTruePrice(Double xihuOrderTruePrice) {
        this.xihuOrderTruePrice = xihuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getXihuOrderTypes() {
        return xihuOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setXihuOrderTypes(Integer xihuOrderTypes) {
        this.xihuOrderTypes = xihuOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getXihuOrderPaymentTypes() {
        return xihuOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setXihuOrderPaymentTypes(Integer xihuOrderPaymentTypes) {
        this.xihuOrderPaymentTypes = xihuOrderPaymentTypes;
    }
    /**
	 * 设置：预约日期
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }


    /**
	 * 获取：预约日期
	 */

    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
