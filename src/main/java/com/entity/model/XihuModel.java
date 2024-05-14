package com.entity.model;

import com.entity.XihuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 宠物洗护
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XihuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 洗护名称
     */
    private String xihuName;


    /**
     * 洗护照片
     */
    private String xihuPhoto;


    /**
     * 洗护类型
     */
    private Integer xihuTypes;


    /**
     * 购买获得积分
     */
    private Integer xihuPrice;


    /**
     * 原价
     */
    private Double xihuOldMoney;


    /**
     * 现价/积分
     */
    private Double xihuNewMoney;


    /**
     * 商品库存
     */
    private Integer xihuKucunNumber;


    /**
     * 点击次数
     */
    private Integer xihuClicknum;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer xihuDelete;


    /**
     * 洗护介绍
     */
    private String xihuContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：洗护名称
	 */
    public String getXihuName() {
        return xihuName;
    }


    /**
	 * 设置：洗护名称
	 */
    public void setXihuName(String xihuName) {
        this.xihuName = xihuName;
    }
    /**
	 * 获取：洗护照片
	 */
    public String getXihuPhoto() {
        return xihuPhoto;
    }


    /**
	 * 设置：洗护照片
	 */
    public void setXihuPhoto(String xihuPhoto) {
        this.xihuPhoto = xihuPhoto;
    }
    /**
	 * 获取：洗护类型
	 */
    public Integer getXihuTypes() {
        return xihuTypes;
    }


    /**
	 * 设置：洗护类型
	 */
    public void setXihuTypes(Integer xihuTypes) {
        this.xihuTypes = xihuTypes;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getXihuPrice() {
        return xihuPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setXihuPrice(Integer xihuPrice) {
        this.xihuPrice = xihuPrice;
    }
    /**
	 * 获取：原价
	 */
    public Double getXihuOldMoney() {
        return xihuOldMoney;
    }


    /**
	 * 设置：原价
	 */
    public void setXihuOldMoney(Double xihuOldMoney) {
        this.xihuOldMoney = xihuOldMoney;
    }
    /**
	 * 获取：现价/积分
	 */
    public Double getXihuNewMoney() {
        return xihuNewMoney;
    }


    /**
	 * 设置：现价/积分
	 */
    public void setXihuNewMoney(Double xihuNewMoney) {
        this.xihuNewMoney = xihuNewMoney;
    }
    /**
	 * 获取：商品库存
	 */
    public Integer getXihuKucunNumber() {
        return xihuKucunNumber;
    }


    /**
	 * 设置：商品库存
	 */
    public void setXihuKucunNumber(Integer xihuKucunNumber) {
        this.xihuKucunNumber = xihuKucunNumber;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getXihuClicknum() {
        return xihuClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setXihuClicknum(Integer xihuClicknum) {
        this.xihuClicknum = xihuClicknum;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getXihuDelete() {
        return xihuDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setXihuDelete(Integer xihuDelete) {
        this.xihuDelete = xihuDelete;
    }
    /**
	 * 获取：洗护介绍
	 */
    public String getXihuContent() {
        return xihuContent;
    }


    /**
	 * 设置：洗护介绍
	 */
    public void setXihuContent(String xihuContent) {
        this.xihuContent = xihuContent;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
