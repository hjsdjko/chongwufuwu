package com.entity.model;

import com.entity.YonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 用户
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YonghuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 昵称
     */
    private String yonghuName;


    /**
     * 余额
     */
    private Double newMoney;


    /**
     * 会员等级
     */
    private Integer huiyuandengjiTypes;


    /**
     * 总积分
     */
    private Double yonghuSumJifen;


    /**
     * 现积分
     */
    private Double yonghuNewJifen;


    /**
     * 创建时间
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：昵称
	 */
    public String getYonghuName() {
        return yonghuName;
    }


    /**
	 * 设置：昵称
	 */
    public void setYonghuName(String yonghuName) {
        this.yonghuName = yonghuName;
    }
    /**
	 * 获取：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 设置：余额
	 */
    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 获取：会员等级
	 */
    public Integer getHuiyuandengjiTypes() {
        return huiyuandengjiTypes;
    }


    /**
	 * 设置：会员等级
	 */
    public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
        this.huiyuandengjiTypes = huiyuandengjiTypes;
    }
    /**
	 * 获取：总积分
	 */
    public Double getYonghuSumJifen() {
        return yonghuSumJifen;
    }


    /**
	 * 设置：总积分
	 */
    public void setYonghuSumJifen(Double yonghuSumJifen) {
        this.yonghuSumJifen = yonghuSumJifen;
    }
    /**
	 * 获取：现积分
	 */
    public Double getYonghuNewJifen() {
        return yonghuNewJifen;
    }


    /**
	 * 设置：现积分
	 */
    public void setYonghuNewJifen(Double yonghuNewJifen) {
        this.yonghuNewJifen = yonghuNewJifen;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
