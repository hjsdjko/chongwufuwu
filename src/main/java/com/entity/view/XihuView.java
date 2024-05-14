package com.entity.view;

import com.entity.XihuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 宠物洗护
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("xihu")
public class XihuView extends XihuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 洗护类型的值
		*/
		private String xihuValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public XihuView() {

	}

	public XihuView(XihuEntity xihuEntity) {
		try {
			BeanUtils.copyProperties(this, xihuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
















}
