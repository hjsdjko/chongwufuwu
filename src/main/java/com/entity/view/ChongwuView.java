package com.entity.view;

import com.entity.ChongwuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 宠物领养
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("chongwu")
public class ChongwuView extends ChongwuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 宠物类型的值
		*/
		private String chongwuValue;
		/**
		* 是否被领养的值
		*/
		private String chongwuLingyangValue;



	public ChongwuView() {

	}

	public ChongwuView(ChongwuEntity chongwuEntity) {
		try {
			BeanUtils.copyProperties(this, chongwuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
















}
