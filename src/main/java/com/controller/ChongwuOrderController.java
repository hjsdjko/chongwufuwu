














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
 * 宠物领养订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/chongwuOrder")
public class ChongwuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ChongwuOrderController.class);

    @Autowired
    private ChongwuOrderService chongwuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ChongwuService chongwuService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = chongwuOrderService.queryPage(params);

        //字典表数据转换
        List<ChongwuOrderView> list =(List<ChongwuOrderView>)page.getList();
        for(ChongwuOrderView c:list){
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
        ChongwuOrderEntity chongwuOrder = chongwuOrderService.selectById(id);
        if(chongwuOrder !=null){
            //entity转view
            ChongwuOrderView view = new ChongwuOrderView();
            BeanUtils.copyProperties( chongwuOrder , view );//把实体数据重构到view中

                //级联表
                ChongwuEntity chongwu = chongwuService.selectById(chongwuOrder.getChongwuId());
                if(chongwu != null){
                    BeanUtils.copyProperties( chongwu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setChongwuId(chongwu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(chongwuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody ChongwuOrderEntity chongwuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,chongwuOrder:{}",this.getClass().getName(),chongwuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            chongwuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        chongwuOrder.setInsertTime(new Date());
        chongwuOrder.setCreateTime(new Date());
        chongwuOrderService.insert(chongwuOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChongwuOrderEntity chongwuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,chongwuOrder:{}",this.getClass().getName(),chongwuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            chongwuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ChongwuOrderEntity> queryWrapper = new EntityWrapper<ChongwuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChongwuOrderEntity chongwuOrderEntity = chongwuOrderService.selectOne(queryWrapper);
        if("".equals(chongwuOrder.getChongwuOrderPhoto()) || "null".equals(chongwuOrder.getChongwuOrderPhoto())){
                chongwuOrder.setChongwuOrderPhoto(null);
        }
        if("".equals(chongwuOrder.getChongwuOrderZhengPhoto()) || "null".equals(chongwuOrder.getChongwuOrderZhengPhoto())){
                chongwuOrder.setChongwuOrderZhengPhoto(null);
        }
        if("".equals(chongwuOrder.getChongwuOrderFanPhoto()) || "null".equals(chongwuOrder.getChongwuOrderFanPhoto())){
                chongwuOrder.setChongwuOrderFanPhoto(null);
        }
        if("".equals(chongwuOrder.getChongwuOrderXinyongPhoto()) || "null".equals(chongwuOrder.getChongwuOrderXinyongPhoto())){
                chongwuOrder.setChongwuOrderXinyongPhoto(null);
        }
        if(chongwuOrderEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      chongwuOrder.set
            //  }
            chongwuOrderService.updateById(chongwuOrder);//根据id更新
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
        chongwuOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ChongwuOrderEntity> chongwuOrderList = new ArrayList<>();//上传的东西
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
                            ChongwuOrderEntity chongwuOrderEntity = new ChongwuOrderEntity();
//                            chongwuOrderEntity.setChongwuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            chongwuOrderEntity.setChongwuId(Integer.valueOf(data.get(0)));   //宠物 要改的
//                            chongwuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            chongwuOrderEntity.setChongwuOrderName(data.get(0));                    //申请人姓名 要改的
//                            chongwuOrderEntity.setChongwuOrderPhone(data.get(0));                    //申请人手机号 要改的
//                            chongwuOrderEntity.setChongwuOrderIdNumber(data.get(0));                    //申请人身份证号 要改的
//                            chongwuOrderEntity.setChongwuOrderPhoto("");//照片
//                            chongwuOrderEntity.setChongwuOrderZhengPhoto("");//照片
//                            chongwuOrderEntity.setChongwuOrderFanPhoto("");//照片
//                            chongwuOrderEntity.setChongwuOrderXinyongPhoto("");//照片
//                            chongwuOrderEntity.setChongwuOrderTypes(Integer.valueOf(data.get(0)));   //领养状态 要改的
//                            chongwuOrderEntity.setInsertTime(date);//时间
//                            chongwuOrderEntity.setCreateTime(date);//时间
                            chongwuOrderList.add(chongwuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("chongwuOrderUuidNumber")){
                                    List<String> chongwuOrderUuidNumber = seachFields.get("chongwuOrderUuidNumber");
                                    chongwuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> chongwuOrderUuidNumber = new ArrayList<>();
                                    chongwuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("chongwuOrderUuidNumber",chongwuOrderUuidNumber);
                                }
                                //申请人手机号
                                if(seachFields.containsKey("chongwuOrderPhone")){
                                    List<String> chongwuOrderPhone = seachFields.get("chongwuOrderPhone");
                                    chongwuOrderPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> chongwuOrderPhone = new ArrayList<>();
                                    chongwuOrderPhone.add(data.get(0));//要改的
                                    seachFields.put("chongwuOrderPhone",chongwuOrderPhone);
                                }
                                //申请人身份证号
                                if(seachFields.containsKey("chongwuOrderIdNumber")){
                                    List<String> chongwuOrderIdNumber = seachFields.get("chongwuOrderIdNumber");
                                    chongwuOrderIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> chongwuOrderIdNumber = new ArrayList<>();
                                    chongwuOrderIdNumber.add(data.get(0));//要改的
                                    seachFields.put("chongwuOrderIdNumber",chongwuOrderIdNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<ChongwuOrderEntity> chongwuOrderEntities_chongwuOrderUuidNumber = chongwuOrderService.selectList(new EntityWrapper<ChongwuOrderEntity>().in("chongwu_order_uuid_number", seachFields.get("chongwuOrderUuidNumber")));
                        if(chongwuOrderEntities_chongwuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChongwuOrderEntity s:chongwuOrderEntities_chongwuOrderUuidNumber){
                                repeatFields.add(s.getChongwuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //申请人手机号
                        List<ChongwuOrderEntity> chongwuOrderEntities_chongwuOrderPhone = chongwuOrderService.selectList(new EntityWrapper<ChongwuOrderEntity>().in("chongwu_order_phone", seachFields.get("chongwuOrderPhone")));
                        if(chongwuOrderEntities_chongwuOrderPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChongwuOrderEntity s:chongwuOrderEntities_chongwuOrderPhone){
                                repeatFields.add(s.getChongwuOrderPhone());
                            }
                            return R.error(511,"数据库的该表中的 [申请人手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //申请人身份证号
                        List<ChongwuOrderEntity> chongwuOrderEntities_chongwuOrderIdNumber = chongwuOrderService.selectList(new EntityWrapper<ChongwuOrderEntity>().in("chongwu_order_id_number", seachFields.get("chongwuOrderIdNumber")));
                        if(chongwuOrderEntities_chongwuOrderIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChongwuOrderEntity s:chongwuOrderEntities_chongwuOrderIdNumber){
                                repeatFields.add(s.getChongwuOrderIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [申请人身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        chongwuOrderService.insertBatch(chongwuOrderList);
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
        PageUtils page = chongwuOrderService.queryPage(params);

        //字典表数据转换
        List<ChongwuOrderView> list =(List<ChongwuOrderView>)page.getList();
        for(ChongwuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChongwuOrderEntity chongwuOrder = chongwuOrderService.selectById(id);
            if(chongwuOrder !=null){


                //entity转view
                ChongwuOrderView view = new ChongwuOrderView();
                BeanUtils.copyProperties( chongwuOrder , view );//把实体数据重构到view中

                //级联表
                    ChongwuEntity chongwu = chongwuService.selectById(chongwuOrder.getChongwuId());
                if(chongwu != null){
                    BeanUtils.copyProperties( chongwu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setChongwuId(chongwu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(chongwuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody ChongwuOrderEntity chongwuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,chongwuOrder:{}",this.getClass().getName(),chongwuOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            ChongwuEntity chongwuEntity = chongwuService.selectById(chongwuOrder.getChongwuId());
            if(chongwuEntity == null){
                return R.error(511,"查不到该物品");
            }

            if(false){
            }

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
//                chongwuOrder.setInsertTime(new Date());
                chongwuOrder.setCreateTime(new Date());
            chongwuOrder.setChongwuOrderTypes(1);
                chongwuOrderService.insert(chongwuOrder);//新增订单

            chongwuEntity.setChongwuLingyangTypes(2);
            chongwuService.updateById(chongwuEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }

    /**
     * 添加订单
     */
//    @RequestMapping("/order")
//    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
//        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
//        String chongwuOrderUuidNumber = String.valueOf(new Date().getTime());
//
//        //获取当前登录用户的id
//        Integer userId = (Integer) request.getSession().getAttribute("userId");
//
//        Integer chongwuOrderName = Integer.valueOf(String.valueOf(params.get("chongwuOrderName")));//申请人姓名
//        Integer chongwuOrderPhone = Integer.valueOf(String.valueOf(params.get("chongwuOrderPhone")));//申请人手机号
//        Integer chongwuOrderIdNumber = Integer.valueOf(String.valueOf(params.get("chongwuOrderIdNumber")));//申请人身份证号
//        Integer chongwuOrderPhoto = Integer.valueOf(String.valueOf(params.get("chongwuOrderPhoto")));//申请人人像照片
//        Integer chongwuOrderZhengPhoto = Integer.valueOf(String.valueOf(params.get("chongwuOrderZhengPhoto")));//申请人身份证正面照片
//        Integer chongwuOrderFanPhoto = Integer.valueOf(String.valueOf(params.get("chongwuOrderFanPhoto")));//申请人身份证反面照片
//        Integer chongwuOrderXinyongPhoto = Integer.valueOf(String.valueOf(params.get("chongwuOrderXinyongPhoto")));//申请人信用照片
//
//        String data = String.valueOf(params.get("chongwus"));
//        JSONArray jsonArray = JSON.parseArray(data);
//        List<Map> chongwus = JSON.parseObject(jsonArray.toString(), List.class);
//
//        //获取当前登录用户的个人信息
//        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
//
//        //当前订单表
//        List<ChongwuOrderEntity> chongwuOrderList = new ArrayList<>();
//        //商品表
//        List<ChongwuEntity> chongwuList = new ArrayList<>();
//        //购物车ids
//        List<Integer> cartIds = new ArrayList<>();
//
//        BigDecimal zhekou = new BigDecimal(1.0);
//        // 获取折扣
//        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
//                .eq("dic_code", "huiyuandengji_types")
//                .eq("dic_name", "会员等级类型名称")
//                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
//                ;
//        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
//        if(dictionaryEntity != null ){
//            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
//        }
//
//        //循环取出需要的数据
//        for (Map<String, Object> map : chongwus) {
//           //取值
//            Integer chongwuId = Integer.valueOf(String.valueOf(map.get("chongwuId")));//商品id
//            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
//            ChongwuEntity chongwuEntity = chongwuService.selectById(chongwuId);//购买的商品
//            String id = String.valueOf(map.get("id"));
//            if(StringUtil.isNotEmpty(id))
//                cartIds.add(Integer.valueOf(id));
//
//            //判断商品的库存是否足够
//            if(chongwuEntity.getChongwuKucunNumber() < buyNumber){
//                //商品库存不足直接返回
//                return R.error(chongwuEntity.getChongwuName()+"的库存不足");
//            }else{
//                //商品库存充足就减库存
//                chongwuEntity.setChongwuKucunNumber(chongwuEntity.getChongwuKucunNumber() - buyNumber);
//            }
//
//            //订单信息表增加数据
//            ChongwuOrderEntity chongwuOrderEntity = new ChongwuOrderEntity<>();
//
//            //赋值订单信息
//            chongwuOrderEntity.setChongwuOrderUuidNumber(chongwuOrderUuidNumber);//订单号
//            chongwuOrderEntity.setChongwuId(chongwuId);//宠物
//            chongwuOrderEntity.setYonghuId(userId);//用户
//            chongwuOrderEntity.setChongwuOrderName(chongwuOrderName);//申请人姓名 ？？？？？？
//            chongwuOrderEntity.setChongwuOrderPhone(chongwuOrderPhone);//申请人手机号 ？？？？？？
//            chongwuOrderEntity.setChongwuOrderIdNumber(chongwuOrderIdNumber);//申请人身份证号 ？？？？？？
//            chongwuOrderEntity.setChongwuOrderPhoto(chongwuOrderPhoto);//申请人人像照片 ？？？？？？
//            chongwuOrderEntity.setChongwuOrderZhengPhoto(chongwuOrderZhengPhoto);//申请人身份证正面照片 ？？？？？？
//            chongwuOrderEntity.setChongwuOrderFanPhoto(chongwuOrderFanPhoto);//申请人身份证反面照片 ？？？？？？
//            chongwuOrderEntity.setChongwuOrderXinyongPhoto(chongwuOrderXinyongPhoto);//申请人信用照片 ？？？？？？
//            chongwuOrderEntity.setInsertTime(new Date());//领养时间
//            chongwuOrderEntity.setCreateTime(new Date());//创建时间
//
//            //判断是什么支付方式 1代表余额 2代表积分
//            if(chongwuOrderPaymentTypes == 1){//余额支付
//                //计算金额
//                Double money = new BigDecimal(chongwuEntity.getChongwuNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();
//
//                if(yonghuEntity.getNewMoney() - money <0 ){
//                    return R.error("余额不足,请充值！！！");
//                }else{
//                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
//                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + money); //设置总积分
//                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + money); //设置现积分
//
//                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
//                            yonghuEntity.setHuiyuandengjiTypes(1);
//                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
//                            yonghuEntity.setHuiyuandengjiTypes(2);
//                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
//                            yonghuEntity.setHuiyuandengjiTypes(3);
//
//
//                    chongwuOrderEntity.setChongwuOrderTruePrice(money);
//
//                }
//            }
//            chongwuOrderList.add(chongwuOrderEntity);
//            chongwuList.add(chongwuEntity);
//
//        }
//        chongwuOrderService.insertBatch(chongwuOrderList);
//        chongwuService.updateBatchById(chongwuList);
//        yonghuService.updateById(yonghuEntity);
//        if(cartIds != null && cartIds.size()>0)
//            cartService.deleteBatchIds(cartIds);
//        return R.ok();
//    }
//

















}
