package com.entity.view;

import com.entity.XihuCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 洗护评价
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("xihu_commentback")
public class XihuCommentbackView extends XihuCommentbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 xihu
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
				* 洗护类型的值
				*/
				private String xihuValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer xihuDelete;
			/**
			* 洗护介绍
			*/
			private String xihuContent;

		//级联表 yonghu
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
				* 会员等级的值
				*/
				private String huiyuandengjiValue;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;

	public XihuCommentbackView() {

	}

	public XihuCommentbackView(XihuCommentbackEntity xihuCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, xihuCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


































				//级联表的get和set xihu
					/**
					* 获取： 洗护名称
					*/
					public String getXihuName() {
						return xihuName;
					}
					/**
					* 设置： 洗护名称
					*/
					public void setXihuName(String xihuName) {
						this.xihuName = xihuName;
					}
					/**
					* 获取： 洗护照片
					*/
					public String getXihuPhoto() {
						return xihuPhoto;
					}
					/**
					* 设置： 洗护照片
					*/
					public void setXihuPhoto(String xihuPhoto) {
						this.xihuPhoto = xihuPhoto;
					}
					/**
					* 获取： 洗护类型
					*/
					public Integer getXihuTypes() {
						return xihuTypes;
					}
					/**
					* 设置： 洗护类型
					*/
					public void setXihuTypes(Integer xihuTypes) {
						this.xihuTypes = xihuTypes;
					}


						/**
						* 获取： 洗护类型的值
						*/
						public String getXihuValue() {
							return xihuValue;
						}
						/**
						* 设置： 洗护类型的值
						*/
						public void setXihuValue(String xihuValue) {
							this.xihuValue = xihuValue;
						}
					/**
					* 获取： 购买获得积分
					*/
					public Integer getXihuPrice() {
						return xihuPrice;
					}
					/**
					* 设置： 购买获得积分
					*/
					public void setXihuPrice(Integer xihuPrice) {
						this.xihuPrice = xihuPrice;
					}
					/**
					* 获取： 原价
					*/
					public Double getXihuOldMoney() {
						return xihuOldMoney;
					}
					/**
					* 设置： 原价
					*/
					public void setXihuOldMoney(Double xihuOldMoney) {
						this.xihuOldMoney = xihuOldMoney;
					}
					/**
					* 获取： 现价/积分
					*/
					public Double getXihuNewMoney() {
						return xihuNewMoney;
					}
					/**
					* 设置： 现价/积分
					*/
					public void setXihuNewMoney(Double xihuNewMoney) {
						this.xihuNewMoney = xihuNewMoney;
					}
					/**
					* 获取： 商品库存
					*/
					public Integer getXihuKucunNumber() {
						return xihuKucunNumber;
					}
					/**
					* 设置： 商品库存
					*/
					public void setXihuKucunNumber(Integer xihuKucunNumber) {
						this.xihuKucunNumber = xihuKucunNumber;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getXihuClicknum() {
						return xihuClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setXihuClicknum(Integer xihuClicknum) {
						this.xihuClicknum = xihuClicknum;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getXihuDelete() {
						return xihuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setXihuDelete(Integer xihuDelete) {
						this.xihuDelete = xihuDelete;
					}
					/**
					* 获取： 洗护介绍
					*/
					public String getXihuContent() {
						return xihuContent;
					}
					/**
					* 设置： 洗护介绍
					*/
					public void setXihuContent(String xihuContent) {
						this.xihuContent = xihuContent;
					}










				//级联表的get和set yonghu
					/**
					* 获取： 昵称
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 昵称
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}
					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}
					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}
					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}



}
