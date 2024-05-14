package com.entity.vo;

import com.entity.YonghuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 用户
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yonghu")
public class YonghuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 昵称
     */

    @TableField(value = "yonghu_name")
    private String yonghuName;


    /**
     * 余额
     */

    @TableField(value = "new_money")
    private Double newMoney;


    /**
     * 会员等级
     */

    @TableField(value = "huiyuandengji_types")
    private Integer huiyuandengjiTypes;


    /**
     * 总积分
     */

    @TableField(value = "yonghu_sum_jifen")
    private Double yonghuSumJifen;


    /**
     * 现积分
     */

    @TableField(value = "yonghu_new_jifen")
    private Double yonghuNewJifen;


    /**
     * 创建时间
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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：昵称
	 */
    public String getYonghuName() {
        return yonghuName;
    }


    /**
	 * 获取：昵称
	 */

    public void setYonghuName(String yonghuName) {
        this.yonghuName = yonghuName;
    }
    /**
	 * 设置：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 获取：余额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 设置：会员等级
	 */
    public Integer getHuiyuandengjiTypes() {
        return huiyuandengjiTypes;
    }


    /**
	 * 获取：会员等级
	 */

    public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
        this.huiyuandengjiTypes = huiyuandengjiTypes;
    }
    /**
	 * 设置：总积分
	 */
    public Double getYonghuSumJifen() {
        return yonghuSumJifen;
    }


    /**
	 * 获取：总积分
	 */

    public void setYonghuSumJifen(Double yonghuSumJifen) {
        this.yonghuSumJifen = yonghuSumJifen;
    }
    /**
	 * 设置：现积分
	 */
    public Double getYonghuNewJifen() {
        return yonghuNewJifen;
    }


    /**
	 * 获取：现积分
	 */

    public void setYonghuNewJifen(Double yonghuNewJifen) {
        this.yonghuNewJifen = yonghuNewJifen;
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

}
