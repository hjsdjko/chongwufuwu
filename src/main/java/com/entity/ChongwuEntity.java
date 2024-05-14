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
 * 宠物领养
 *
 * @author 
 * @email
 */
@TableName("chongwu")
public class ChongwuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChongwuEntity() {

	}

	public ChongwuEntity(T t) {
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
     * 宠物唯一编号
     */
    @TableField(value = "chongwu_uuid_number")

    private String chongwuUuidNumber;


    /**
     * 宠物名称
     */
    @TableField(value = "chongwu_name")

    private String chongwuName;


    /**
     * 宠物照片
     */
    @TableField(value = "chongwu_photo")

    private String chongwuPhoto;


    /**
     * 宠物视频
     */
    @TableField(value = "chongwu_video")

    private String chongwuVideo;


    /**
     * 宠物类型
     */
    @TableField(value = "chongwu_types")

    private Integer chongwuTypes;


    /**
     * 是否被领养
     */
    @TableField(value = "chongwu_lingyang_types")

    private Integer chongwuLingyangTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "chongwu_delete")

    private Integer chongwuDelete;


    /**
     * 宠物介绍
     */
    @TableField(value = "chongwu_content")

    private String chongwuContent;


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
	 * 设置：宠物唯一编号
	 */
    public String getChongwuUuidNumber() {
        return chongwuUuidNumber;
    }


    /**
	 * 获取：宠物唯一编号
	 */

    public void setChongwuUuidNumber(String chongwuUuidNumber) {
        this.chongwuUuidNumber = chongwuUuidNumber;
    }
    /**
	 * 设置：宠物名称
	 */
    public String getChongwuName() {
        return chongwuName;
    }


    /**
	 * 获取：宠物名称
	 */

    public void setChongwuName(String chongwuName) {
        this.chongwuName = chongwuName;
    }
    /**
	 * 设置：宠物照片
	 */
    public String getChongwuPhoto() {
        return chongwuPhoto;
    }


    /**
	 * 获取：宠物照片
	 */

    public void setChongwuPhoto(String chongwuPhoto) {
        this.chongwuPhoto = chongwuPhoto;
    }
    /**
	 * 设置：宠物视频
	 */
    public String getChongwuVideo() {
        return chongwuVideo;
    }


    /**
	 * 获取：宠物视频
	 */

    public void setChongwuVideo(String chongwuVideo) {
        this.chongwuVideo = chongwuVideo;
    }
    /**
	 * 设置：宠物类型
	 */
    public Integer getChongwuTypes() {
        return chongwuTypes;
    }


    /**
	 * 获取：宠物类型
	 */

    public void setChongwuTypes(Integer chongwuTypes) {
        this.chongwuTypes = chongwuTypes;
    }
    /**
	 * 设置：是否被领养
	 */
    public Integer getChongwuLingyangTypes() {
        return chongwuLingyangTypes;
    }


    /**
	 * 获取：是否被领养
	 */

    public void setChongwuLingyangTypes(Integer chongwuLingyangTypes) {
        this.chongwuLingyangTypes = chongwuLingyangTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getChongwuDelete() {
        return chongwuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setChongwuDelete(Integer chongwuDelete) {
        this.chongwuDelete = chongwuDelete;
    }
    /**
	 * 设置：宠物介绍
	 */
    public String getChongwuContent() {
        return chongwuContent;
    }


    /**
	 * 获取：宠物介绍
	 */

    public void setChongwuContent(String chongwuContent) {
        this.chongwuContent = chongwuContent;
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
        return "Chongwu{" +
            "id=" + id +
            ", chongwuUuidNumber=" + chongwuUuidNumber +
            ", chongwuName=" + chongwuName +
            ", chongwuPhoto=" + chongwuPhoto +
            ", chongwuVideo=" + chongwuVideo +
            ", chongwuTypes=" + chongwuTypes +
            ", chongwuLingyangTypes=" + chongwuLingyangTypes +
            ", chongwuDelete=" + chongwuDelete +
            ", chongwuContent=" + chongwuContent +
            ", createTime=" + createTime +
        "}";
    }
}
