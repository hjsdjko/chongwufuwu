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
 * 宠物洗护
 *
 * @author 
 * @email
 */
@TableName("xihu")
public class XihuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XihuEntity() {

	}

	public XihuEntity(T t) {
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
     * 洗护名称
     */
    @TableField(value = "xihu_name")

    private String xihuName;


    /**
     * 洗护照片
     */
    @TableField(value = "xihu_photo")

    private String xihuPhoto;


    /**
     * 洗护类型
     */
    @TableField(value = "xihu_types")

    private Integer xihuTypes;


    /**
     * 购买获得积分
     */
    @TableField(value = "xihu_price")

    private Integer xihuPrice;


    /**
     * 原价
     */
    @TableField(value = "xihu_old_money")

    private Double xihuOldMoney;


    /**
     * 现价/积分
     */
    @TableField(value = "xihu_new_money")

    private Double xihuNewMoney;


    /**
     * 商品库存
     */
    @TableField(value = "xihu_kucun_number")

    private Integer xihuKucunNumber;


    /**
     * 点击次数
     */
    @TableField(value = "xihu_clicknum")

    private Integer xihuClicknum;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "xihu_delete")

    private Integer xihuDelete;


    /**
     * 洗护介绍
     */
    @TableField(value = "xihu_content")

    private String xihuContent;


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
	 * 设置：洗护名称
	 */
    public String getXihuName() {
        return xihuName;
    }


    /**
	 * 获取：洗护名称
	 */

    public void setXihuName(String xihuName) {
        this.xihuName = xihuName;
    }
    /**
	 * 设置：洗护照片
	 */
    public String getXihuPhoto() {
        return xihuPhoto;
    }


    /**
	 * 获取：洗护照片
	 */

    public void setXihuPhoto(String xihuPhoto) {
        this.xihuPhoto = xihuPhoto;
    }
    /**
	 * 设置：洗护类型
	 */
    public Integer getXihuTypes() {
        return xihuTypes;
    }


    /**
	 * 获取：洗护类型
	 */

    public void setXihuTypes(Integer xihuTypes) {
        this.xihuTypes = xihuTypes;
    }
    /**
	 * 设置：购买获得积分
	 */
    public Integer getXihuPrice() {
        return xihuPrice;
    }


    /**
	 * 获取：购买获得积分
	 */

    public void setXihuPrice(Integer xihuPrice) {
        this.xihuPrice = xihuPrice;
    }
    /**
	 * 设置：原价
	 */
    public Double getXihuOldMoney() {
        return xihuOldMoney;
    }


    /**
	 * 获取：原价
	 */

    public void setXihuOldMoney(Double xihuOldMoney) {
        this.xihuOldMoney = xihuOldMoney;
    }
    /**
	 * 设置：现价/积分
	 */
    public Double getXihuNewMoney() {
        return xihuNewMoney;
    }


    /**
	 * 获取：现价/积分
	 */

    public void setXihuNewMoney(Double xihuNewMoney) {
        this.xihuNewMoney = xihuNewMoney;
    }
    /**
	 * 设置：商品库存
	 */
    public Integer getXihuKucunNumber() {
        return xihuKucunNumber;
    }


    /**
	 * 获取：商品库存
	 */

    public void setXihuKucunNumber(Integer xihuKucunNumber) {
        this.xihuKucunNumber = xihuKucunNumber;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getXihuClicknum() {
        return xihuClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setXihuClicknum(Integer xihuClicknum) {
        this.xihuClicknum = xihuClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getXihuDelete() {
        return xihuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setXihuDelete(Integer xihuDelete) {
        this.xihuDelete = xihuDelete;
    }
    /**
	 * 设置：洗护介绍
	 */
    public String getXihuContent() {
        return xihuContent;
    }


    /**
	 * 获取：洗护介绍
	 */

    public void setXihuContent(String xihuContent) {
        this.xihuContent = xihuContent;
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
        return "Xihu{" +
            "id=" + id +
            ", xihuName=" + xihuName +
            ", xihuPhoto=" + xihuPhoto +
            ", xihuTypes=" + xihuTypes +
            ", xihuPrice=" + xihuPrice +
            ", xihuOldMoney=" + xihuOldMoney +
            ", xihuNewMoney=" + xihuNewMoney +
            ", xihuKucunNumber=" + xihuKucunNumber +
            ", xihuClicknum=" + xihuClicknum +
            ", shangxiaTypes=" + shangxiaTypes +
            ", xihuDelete=" + xihuDelete +
            ", xihuContent=" + xihuContent +
            ", createTime=" + createTime +
        "}";
    }
}
