package com.entity.model;

import com.entity.XihuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 洗护订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XihuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String xihuOrderUuidNumber;


    /**
     * 洗护
     */
    private Integer xihuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double xihuOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer xihuOrderTypes;


    /**
     * 支付类型
     */
    private Integer xihuOrderPaymentTypes;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yuyueTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getXihuOrderUuidNumber() {
        return xihuOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setXihuOrderUuidNumber(String xihuOrderUuidNumber) {
        this.xihuOrderUuidNumber = xihuOrderUuidNumber;
    }
    /**
	 * 获取：洗护
	 */
    public Integer getXihuId() {
        return xihuId;
    }


    /**
	 * 设置：洗护
	 */
    public void setXihuId(Integer xihuId) {
        this.xihuId = xihuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getXihuOrderTruePrice() {
        return xihuOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setXihuOrderTruePrice(Double xihuOrderTruePrice) {
        this.xihuOrderTruePrice = xihuOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getXihuOrderTypes() {
        return xihuOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setXihuOrderTypes(Integer xihuOrderTypes) {
        this.xihuOrderTypes = xihuOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getXihuOrderPaymentTypes() {
        return xihuOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setXihuOrderPaymentTypes(Integer xihuOrderPaymentTypes) {
        this.xihuOrderPaymentTypes = xihuOrderPaymentTypes;
    }
    /**
	 * 获取：预约日期
	 */
    public Date getYuyueTime() {
        return yuyueTime;
    }


    /**
	 * 设置：预约日期
	 */
    public void setYuyueTime(Date yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
