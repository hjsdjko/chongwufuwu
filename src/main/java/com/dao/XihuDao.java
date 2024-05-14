package com.dao;

import com.entity.XihuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XihuView;

/**
 * 宠物洗护 Dao 接口
 *
 * @author 
 */
public interface XihuDao extends BaseMapper<XihuEntity> {

   List<XihuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
