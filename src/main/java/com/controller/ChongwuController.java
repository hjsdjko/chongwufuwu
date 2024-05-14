














package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 宠物领养
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/chongwu")
public class ChongwuController {
    private static final Logger logger = LoggerFactory.getLogger(ChongwuController.class);

    @Autowired
    private ChongwuService chongwuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("chongwuDeleteStart",1);params.put("chongwuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = chongwuService.queryPage(params);

        //字典表数据转换
        List<ChongwuView> list =(List<ChongwuView>)page.getList();
        for(ChongwuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChongwuEntity chongwu = chongwuService.selectById(id);
        if(chongwu !=null){
            //entity转view
            ChongwuView view = new ChongwuView();
            BeanUtils.copyProperties( chongwu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ChongwuEntity chongwu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chongwu:{}",this.getClass().getName(),chongwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        Wrapper<ChongwuEntity> queryWrapper = new EntityWrapper<ChongwuEntity>()
            .eq("chongwu_uuid_number", chongwu.getChongwuUuidNumber())
            .eq("chongwu_name", chongwu.getChongwuName())
            .eq("chongwu_video", chongwu.getChongwuVideo())
            .eq("chongwu_types", chongwu.getChongwuTypes())
            .eq("chongwu_lingyang_types", chongwu.getChongwuLingyangTypes())
            .eq("chongwu_delete", chongwu.getChongwuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChongwuEntity chongwuEntity = chongwuService.selectOne(queryWrapper);
        if(chongwuEntity==null){
            chongwu.setChongwuDelete(1);
            chongwu.setCreateTime(new Date());
            chongwuService.insert(chongwu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChongwuEntity chongwu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,chongwu:{}",this.getClass().getName(),chongwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<ChongwuEntity> queryWrapper = new EntityWrapper<ChongwuEntity>()
            .notIn("id",chongwu.getId())
            .andNew()
            .eq("chongwu_uuid_number", chongwu.getChongwuUuidNumber())
            .eq("chongwu_name", chongwu.getChongwuName())
            .eq("chongwu_video", chongwu.getChongwuVideo())
            .eq("chongwu_types", chongwu.getChongwuTypes())
            .eq("chongwu_lingyang_types", chongwu.getChongwuLingyangTypes())
            .eq("chongwu_delete", chongwu.getChongwuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChongwuEntity chongwuEntity = chongwuService.selectOne(queryWrapper);
        if("".equals(chongwu.getChongwuPhoto()) || "null".equals(chongwu.getChongwuPhoto())){
                chongwu.setChongwuPhoto(null);
        }
        if("".equals(chongwu.getChongwuVideo()) || "null".equals(chongwu.getChongwuVideo())){
                chongwu.setChongwuVideo(null);
        }
        if(chongwuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      chongwu.set
            //  }
            chongwuService.updateById(chongwu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<ChongwuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ChongwuEntity chongwuEntity = new ChongwuEntity();
            chongwuEntity.setId(id);
            chongwuEntity.setChongwuDelete(2);
            list.add(chongwuEntity);
        }
        if(list != null && list.size() >0){
            chongwuService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ChongwuEntity> chongwuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ChongwuEntity chongwuEntity = new ChongwuEntity();
//                            chongwuEntity.setChongwuUuidNumber(data.get(0));                    //宠物唯一编号 要改的
//                            chongwuEntity.setChongwuName(data.get(0));                    //宠物名称 要改的
//                            chongwuEntity.setChongwuPhoto("");//照片
//                            chongwuEntity.setChongwuVideo(data.get(0));                    //宠物视频 要改的
//                            chongwuEntity.setChongwuTypes(Integer.valueOf(data.get(0)));   //宠物类型 要改的
//                            chongwuEntity.setChongwuLingyangTypes(Integer.valueOf(data.get(0)));   //是否被领养 要改的
//                            chongwuEntity.setChongwuDelete(1);//逻辑删除字段
//                            chongwuEntity.setChongwuContent("");//照片
//                            chongwuEntity.setCreateTime(date);//时间
                            chongwuList.add(chongwuEntity);


                            //把要查询是否重复的字段放入map中
                                //宠物唯一编号
                                if(seachFields.containsKey("chongwuUuidNumber")){
                                    List<String> chongwuUuidNumber = seachFields.get("chongwuUuidNumber");
                                    chongwuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> chongwuUuidNumber = new ArrayList<>();
                                    chongwuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("chongwuUuidNumber",chongwuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //宠物唯一编号
                        List<ChongwuEntity> chongwuEntities_chongwuUuidNumber = chongwuService.selectList(new EntityWrapper<ChongwuEntity>().in("chongwu_uuid_number", seachFields.get("chongwuUuidNumber")).eq("chongwu_delete", 1));
                        if(chongwuEntities_chongwuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChongwuEntity s:chongwuEntities_chongwuUuidNumber){
                                repeatFields.add(s.getChongwuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [宠物唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        chongwuService.insertBatch(chongwuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = chongwuService.queryPage(params);

        //字典表数据转换
        List<ChongwuView> list =(List<ChongwuView>)page.getList();
        for(ChongwuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChongwuEntity chongwu = chongwuService.selectById(id);
            if(chongwu !=null){


                //entity转view
                ChongwuView view = new ChongwuView();
                BeanUtils.copyProperties( chongwu , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ChongwuEntity chongwu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,chongwu:{}",this.getClass().getName(),chongwu.toString());
        Wrapper<ChongwuEntity> queryWrapper = new EntityWrapper<ChongwuEntity>()
            .eq("chongwu_uuid_number", chongwu.getChongwuUuidNumber())
            .eq("chongwu_name", chongwu.getChongwuName())
            .eq("chongwu_video", chongwu.getChongwuVideo())
            .eq("chongwu_types", chongwu.getChongwuTypes())
            .eq("chongwu_lingyang_types", chongwu.getChongwuLingyangTypes())
            .eq("chongwu_delete", chongwu.getChongwuDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChongwuEntity chongwuEntity = chongwuService.selectOne(queryWrapper);
        if(chongwuEntity==null){
            chongwu.setChongwuDelete(1);
            chongwu.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      chongwu.set
        //  }
        chongwuService.insert(chongwu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



}
