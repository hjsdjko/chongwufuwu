package com.entity.model;

import com.entity.ChongwuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宠物领养订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ChongwuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String chongwuOrderUuidNumber;


    /**
     * 宠物
     */
    private Integer chongwuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 申请人姓名
     */
    private String chongwuOrderName;


    /**
     * 申请人手机号
     */
    private String chongwuOrderPhone;


    /**
     * 申请人身份证号
     */
    private String chongwuOrderIdNumber;


    /**
     * 申请人人像照片
     */
    private String chongwuOrderPhoto;


    /**
     * 申请人身份证正面照片
     */
    private String chongwuOrderZhengPhoto;


    /**
     * 申请人身份证反面照片
     */
    private String chongwuOrderFanPhoto;


    /**
     * 申请人信用照片
     */
    private String chongwuOrderXinyongPhoto;


    /**
     * 领养状态
     */
    private Integer chongwuOrderTypes;


    /**
     * 领养时间
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
    public String getChongwuOrderUuidNumber() {
        return chongwuOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setChongwuOrderUuidNumber(String chongwuOrderUuidNumber) {
        this.chongwuOrderUuidNumber = chongwuOrderUuidNumber;
    }
    /**
	 * 获取：宠物
	 */
    public Integer getChongwuId() {
        return chongwuId;
    }


    /**
	 * 设置：宠物
	 */
    public void setChongwuId(Integer chongwuId) {
        this.chongwuId = chongwuId;
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
	 * 获取：申请人姓名
	 */
    public String getChongwuOrderName() {
        return chongwuOrderName;
    }


    /**
	 * 设置：申请人姓名
	 */
    public void setChongwuOrderName(String chongwuOrderName) {
        this.chongwuOrderName = chongwuOrderName;
    }
    /**
	 * 获取：申请人手机号
	 */
    public String getChongwuOrderPhone() {
        return chongwuOrderPhone;
    }


    /**
	 * 设置：申请人手机号
	 */
    public void setChongwuOrderPhone(String chongwuOrderPhone) {
        this.chongwuOrderPhone = chongwuOrderPhone;
    }
    /**
	 * 获取：申请人身份证号
	 */
    public String getChongwuOrderIdNumber() {
        return chongwuOrderIdNumber;
    }


    /**
	 * 设置：申请人身份证号
	 */
    public void setChongwuOrderIdNumber(String chongwuOrderIdNumber) {
        this.chongwuOrderIdNumber = chongwuOrderIdNumber;
    }
    /**
	 * 获取：申请人人像照片
	 */
    public String getChongwuOrderPhoto() {
        return chongwuOrderPhoto;
    }


    /**
	 * 设置：申请人人像照片
	 */
    public void setChongwuOrderPhoto(String chongwuOrderPhoto) {
        this.chongwuOrderPhoto = chongwuOrderPhoto;
    }
    /**
	 * 获取：申请人身份证正面照片
	 */
    public String getChongwuOrderZhengPhoto() {
        return chongwuOrderZhengPhoto;
    }


    /**
	 * 设置：申请人身份证正面照片
	 */
    public void setChongwuOrderZhengPhoto(String chongwuOrderZhengPhoto) {
        this.chongwuOrderZhengPhoto = chongwuOrderZhengPhoto;
    }
    /**
	 * 获取：申请人身份证反面照片
	 */
    public String getChongwuOrderFanPhoto() {
        return chongwuOrderFanPhoto;
    }


    /**
	 * 设置：申请人身份证反面照片
	 */
    public void setChongwuOrderFanPhoto(String chongwuOrderFanPhoto) {
        this.chongwuOrderFanPhoto = chongwuOrderFanPhoto;
    }
    /**
	 * 获取：申请人信用照片
	 */
    public String getChongwuOrderXinyongPhoto() {
        return chongwuOrderXinyongPhoto;
    }


    /**
	 * 设置：申请人信用照片
	 */
    public void setChongwuOrderXinyongPhoto(String chongwuOrderXinyongPhoto) {
        this.chongwuOrderXinyongPhoto = chongwuOrderXinyongPhoto;
    }
    /**
	 * 获取：领养状态
	 */
    public Integer getChongwuOrderTypes() {
        return chongwuOrderTypes;
    }


    /**
	 * 设置：领养状态
	 */
    public void setChongwuOrderTypes(Integer chongwuOrderTypes) {
        this.chongwuOrderTypes = chongwuOrderTypes;
    }
    /**
	 * 获取：领养时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：领养时间
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
