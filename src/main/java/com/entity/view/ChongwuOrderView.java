package com.entity.view;

import com.entity.ChongwuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 宠物领养订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("chongwu_order")
public class ChongwuOrderView extends ChongwuOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 领养状态的值
		*/
		private String chongwuOrderValue;



		//级联表 chongwu
			/**
			* 宠物唯一编号
			*/
			private String chongwuUuidNumber;
			/**
			* 宠物名称
			*/
			private String chongwuName;
			/**
			* 宠物照片
			*/
			private String chongwuPhoto;
			/**
			* 宠物视频
			*/
			private String chongwuVideo;
			/**
			* 宠物类型
			*/
			private Integer chongwuTypes;
				/**
				* 宠物类型的值
				*/
				private String chongwuValue;
			/**
			* 是否被领养
			*/
			private Integer chongwuLingyangTypes;
				/**
				* 是否被领养的值
				*/
				private String chongwuLingyangValue;
			/**
			* 逻辑删除
			*/
			private Integer chongwuDelete;
			/**
			* 宠物介绍
			*/
			private String chongwuContent;

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

	public ChongwuOrderView() {

	}

	public ChongwuOrderView(ChongwuOrderEntity chongwuOrderEntity) {
		try {
			BeanUtils.copyProperties(this, chongwuOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 领养状态的值
			*/
			public String getChongwuOrderValue() {
				return chongwuOrderValue;
			}
			/**
			* 设置： 领养状态的值
			*/
			public void setChongwuOrderValue(String chongwuOrderValue) {
				this.chongwuOrderValue = chongwuOrderValue;
			}







				//级联表的get和set chongwu
					/**
					* 获取： 宠物唯一编号
					*/
					public String getChongwuUuidNumber() {
						return chongwuUuidNumber;
					}
					/**
					* 设置： 宠物唯一编号
					*/
					public void setChongwuUuidNumber(String chongwuUuidNumber) {
						this.chongwuUuidNumber = chongwuUuidNumber;
					}
					/**
					* 获取： 宠物名称
					*/
					public String getChongwuName() {
						return chongwuName;
					}
					/**
					* 设置： 宠物名称
					*/
					public void setChongwuName(String chongwuName) {
						this.chongwuName = chongwuName;
					}
					/**
					* 获取： 宠物照片
					*/
					public String getChongwuPhoto() {
						return chongwuPhoto;
					}
					/**
					* 设置： 宠物照片
					*/
					public void setChongwuPhoto(String chongwuPhoto) {
						this.chongwuPhoto = chongwuPhoto;
					}
					/**
					* 获取： 宠物视频
					*/
					public String getChongwuVideo() {
						return chongwuVideo;
					}
					/**
					* 设置： 宠物视频
					*/
					public void setChongwuVideo(String chongwuVideo) {
						this.chongwuVideo = chongwuVideo;
					}
					/**
					* 获取： 宠物类型
					*/
					public Integer getChongwuTypes() {
						return chongwuTypes;
					}
					/**
					* 设置： 宠物类型
					*/
					public void setChongwuTypes(Integer chongwuTypes) {
						this.chongwuTypes = chongwuTypes;
					}


						/**
						* 获取： 宠物类型的值
						*/
						public String getChongwuValue() {
							return chongwuValue;
						}
						/**
						* 设置： 宠物类型的值
						*/
						public void setChongwuValue(String chongwuValue) {
							this.chongwuValue = chongwuValue;
						}
					/**
					* 获取： 是否被领养
					*/
					public Integer getChongwuLingyangTypes() {
						return chongwuLingyangTypes;
					}
					/**
					* 设置： 是否被领养
					*/
					public void setChongwuLingyangTypes(Integer chongwuLingyangTypes) {
						this.chongwuLingyangTypes = chongwuLingyangTypes;
					}


						/**
						* 获取： 是否被领养的值
						*/
						public String getChongwuLingyangValue() {
							return chongwuLingyangValue;
						}
						/**
						* 设置： 是否被领养的值
						*/
						public void setChongwuLingyangValue(String chongwuLingyangValue) {
							this.chongwuLingyangValue = chongwuLingyangValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getChongwuDelete() {
						return chongwuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setChongwuDelete(Integer chongwuDelete) {
						this.chongwuDelete = chongwuDelete;
					}
					/**
					* 获取： 宠物介绍
					*/
					public String getChongwuContent() {
						return chongwuContent;
					}
					/**
					* 设置： 宠物介绍
					*/
					public void setChongwuContent(String chongwuContent) {
						this.chongwuContent = chongwuContent;
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
