package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 宠物领养订单
 *
 * @author 
 * @email
 */
@TableName("chongwu_order")
public class ChongwuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChongwuOrderEntity() {

	}

	public ChongwuOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "chongwu_order_uuid_number")

    private String chongwuOrderUuidNumber;


    /**
     * 宠物
     */
    @TableField(value = "chongwu_id")

    private Integer chongwuId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 申请人姓名
     */
    @TableField(value = "chongwu_order_name")

    private String chongwuOrderName;


    /**
     * 申请人手机号
     */
    @TableField(value = "chongwu_order_phone")

    private String chongwuOrderPhone;


    /**
     * 申请人身份证号
     */
    @TableField(value = "chongwu_order_id_number")

    private String chongwuOrderIdNumber;


    /**
     * 申请人人像照片
     */
    @TableField(value = "chongwu_order_photo")

    private String chongwuOrderPhoto;


    /**
     * 申请人身份证正面照片
     */
    @TableField(value = "chongwu_order_zheng_photo")

    private String chongwuOrderZhengPhoto;


    /**
     * 申请人身份证反面照片
     */
    @TableField(value = "chongwu_order_fan_photo")

    private String chongwuOrderFanPhoto;


    /**
     * 申请人信用照片
     */
    @TableField(value = "chongwu_order_xinyong_photo")

    private String chongwuOrderXinyongPhoto;


    /**
     * 领养状态
     */
    @TableField(value = "chongwu_order_types")

    private Integer chongwuOrderTypes;


    /**
     * 领养时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
    public String getChongwuOrderUuidNumber() {
        return chongwuOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setChongwuOrderUuidNumber(String chongwuOrderUuidNumber) {
        this.chongwuOrderUuidNumber = chongwuOrderUuidNumber;
    }
    /**
	 * 设置：宠物
	 */
    public Integer getChongwuId() {
        return chongwuId;
    }


    /**
	 * 获取：宠物
	 */

    public void setChongwuId(Integer chongwuId) {
        this.chongwuId = chongwuId;
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
	 * 设置：申请人姓名
	 */
    public String getChongwuOrderName() {
        return chongwuOrderName;
    }


    /**
	 * 获取：申请人姓名
	 */

    public void setChongwuOrderName(String chongwuOrderName) {
        this.chongwuOrderName = chongwuOrderName;
    }
    /**
	 * 设置：申请人手机号
	 */
    public String getChongwuOrderPhone() {
        return chongwuOrderPhone;
    }


    /**
	 * 获取：申请人手机号
	 */

    public void setChongwuOrderPhone(String chongwuOrderPhone) {
        this.chongwuOrderPhone = chongwuOrderPhone;
    }
    /**
	 * 设置：申请人身份证号
	 */
    public String getChongwuOrderIdNumber() {
        return chongwuOrderIdNumber;
    }


    /**
	 * 获取：申请人身份证号
	 */

    public void setChongwuOrderIdNumber(String chongwuOrderIdNumber) {
        this.chongwuOrderIdNumber = chongwuOrderIdNumber;
    }
    /**
	 * 设置：申请人人像照片
	 */
    public String getChongwuOrderPhoto() {
        return chongwuOrderPhoto;
    }


    /**
	 * 获取：申请人人像照片
	 */

    public void setChongwuOrderPhoto(String chongwuOrderPhoto) {
        this.chongwuOrderPhoto = chongwuOrderPhoto;
    }
    /**
	 * 设置：申请人身份证正面照片
	 */
    public String getChongwuOrderZhengPhoto() {
        return chongwuOrderZhengPhoto;
    }


    /**
	 * 获取：申请人身份证正面照片
	 */

    public void setChongwuOrderZhengPhoto(String chongwuOrderZhengPhoto) {
        this.chongwuOrderZhengPhoto = chongwuOrderZhengPhoto;
    }
    /**
	 * 设置：申请人身份证反面照片
	 */
    public String getChongwuOrderFanPhoto() {
        return chongwuOrderFanPhoto;
    }


    /**
	 * 获取：申请人身份证反面照片
	 */

    public void setChongwuOrderFanPhoto(String chongwuOrderFanPhoto) {
        this.chongwuOrderFanPhoto = chongwuOrderFanPhoto;
    }
    /**
	 * 设置：申请人信用照片
	 */
    public String getChongwuOrderXinyongPhoto() {
        return chongwuOrderXinyongPhoto;
    }


    /**
	 * 获取：申请人信用照片
	 */

    public void setChongwuOrderXinyongPhoto(String chongwuOrderXinyongPhoto) {
        this.chongwuOrderXinyongPhoto = chongwuOrderXinyongPhoto;
    }
    /**
	 * 设置：领养状态
	 */
    public Integer getChongwuOrderTypes() {
        return chongwuOrderTypes;
    }


    /**
	 * 获取：领养状态
	 */

    public void setChongwuOrderTypes(Integer chongwuOrderTypes) {
        this.chongwuOrderTypes = chongwuOrderTypes;
    }
    /**
	 * 设置：领养时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：领养时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChongwuOrder{" +
            "id=" + id +
            ", chongwuOrderUuidNumber=" + chongwuOrderUuidNumber +
            ", chongwuId=" + chongwuId +
            ", yonghuId=" + yonghuId +
            ", chongwuOrderName=" + chongwuOrderName +
            ", chongwuOrderPhone=" + chongwuOrderPhone +
            ", chongwuOrderIdNumber=" + chongwuOrderIdNumber +
            ", chongwuOrderPhoto=" + chongwuOrderPhoto +
            ", chongwuOrderZhengPhoto=" + chongwuOrderZhengPhoto +
            ", chongwuOrderFanPhoto=" + chongwuOrderFanPhoto +
            ", chongwuOrderXinyongPhoto=" + chongwuOrderXinyongPhoto +
            ", chongwuOrderTypes=" + chongwuOrderTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
