package com.dao;

import com.entity.XihuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XihuOrderView;

/**
 * 洗护订单 Dao 接口
 *
 * @author 
 */
public interface XihuOrderDao extends BaseMapper<XihuOrderEntity> {

   List<XihuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
