














package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
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
 * 洗护订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xihuOrder")
public class XihuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(XihuOrderController.class);

    @Autowired
    private XihuOrderService xihuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private XihuService xihuService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private XihuCommentbackService xihuCommentbackService;



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
        PageUtils page = xihuOrderService.queryPage(params);

        //字典表数据转换
        List<XihuOrderView> list =(List<XihuOrderView>)page.getList();
        for(XihuOrderView c:list){
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
        XihuOrderEntity xihuOrder = xihuOrderService.selectById(id);
        if(xihuOrder !=null){
            //entity转view
            XihuOrderView view = new XihuOrderView();
            BeanUtils.copyProperties( xihuOrder , view );//把实体数据重构到view中

                //级联表
                XihuEntity xihu = xihuService.selectById(xihuOrder.getXihuId());
                if(xihu != null){
                    BeanUtils.copyProperties( xihu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXihuId(xihu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(xihuOrder.getYonghuId());
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
    public R save(@RequestBody XihuOrderEntity xihuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xihuOrder:{}",this.getClass().getName(),xihuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            xihuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        xihuOrder.setInsertTime(new Date());
        xihuOrder.setCreateTime(new Date());
        xihuOrderService.insert(xihuOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XihuOrderEntity xihuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xihuOrder:{}",this.getClass().getName(),xihuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            xihuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<XihuOrderEntity> queryWrapper = new EntityWrapper<XihuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XihuOrderEntity xihuOrderEntity = xihuOrderService.selectOne(queryWrapper);
        if(xihuOrderEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      xihuOrder.set
            //  }
            xihuOrderService.updateById(xihuOrder);//根据id更新
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
        xihuOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<XihuOrderEntity> xihuOrderList = new ArrayList<>();//上传的东西
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
                            XihuOrderEntity xihuOrderEntity = new XihuOrderEntity();
//                            xihuOrderEntity.setXihuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            xihuOrderEntity.setXihuId(Integer.valueOf(data.get(0)));   //洗护 要改的
//                            xihuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            xihuOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            xihuOrderEntity.setXihuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            xihuOrderEntity.setXihuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            xihuOrderEntity.setXihuOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            xihuOrderEntity.setYuyueTime(new Date(data.get(0)));          //预约日期 要改的
//                            xihuOrderEntity.setInsertTime(date);//时间
//                            xihuOrderEntity.setCreateTime(date);//时间
                            xihuOrderList.add(xihuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("xihuOrderUuidNumber")){
                                    List<String> xihuOrderUuidNumber = seachFields.get("xihuOrderUuidNumber");
                                    xihuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xihuOrderUuidNumber = new ArrayList<>();
                                    xihuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xihuOrderUuidNumber",xihuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<XihuOrderEntity> xihuOrderEntities_xihuOrderUuidNumber = xihuOrderService.selectList(new EntityWrapper<XihuOrderEntity>().in("xihu_order_uuid_number", seachFields.get("xihuOrderUuidNumber")));
                        if(xihuOrderEntities_xihuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XihuOrderEntity s:xihuOrderEntities_xihuOrderUuidNumber){
                                repeatFields.add(s.getXihuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xihuOrderService.insertBatch(xihuOrderList);
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
        PageUtils page = xihuOrderService.queryPage(params);

        //字典表数据转换
        List<XihuOrderView> list =(List<XihuOrderView>)page.getList();
        for(XihuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XihuOrderEntity xihuOrder = xihuOrderService.selectById(id);
            if(xihuOrder !=null){


                //entity转view
                XihuOrderView view = new XihuOrderView();
                BeanUtils.copyProperties( xihuOrder , view );//把实体数据重构到view中

                //级联表
                    XihuEntity xihu = xihuService.selectById(xihuOrder.getXihuId());
                if(xihu != null){
                    BeanUtils.copyProperties( xihu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXihuId(xihu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(xihuOrder.getYonghuId());
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
    public R add(@RequestBody XihuOrderEntity xihuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xihuOrder:{}",this.getClass().getName(),xihuOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            XihuEntity xihuEntity = xihuService.selectById(xihuOrder.getXihuId());
            if(xihuEntity == null){
                return R.error(511,"查不到该物品");
            }
//            Double xihuNewMoney = xihuEntity.getXihuNewMoney();

            if(false){
            }
            else if(xihuEntity.getXihuNewMoney() == null){
                return R.error(511,"物品价格不能为空");
            }
            else if((xihuEntity.getXihuKucunNumber() -xihuOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double zhifuMoney = xihuEntity.getXihuNewMoney() * xihuOrder.getBuyNumber();
            //计算所获得积分
            Double buyJifen = 0.0;
            buyJifen = new BigDecimal(xihuEntity.getXihuPrice()).multiply(new BigDecimal(xihuOrder.getBuyNumber())).doubleValue();
            double balance = yonghuEntity.getNewMoney() - zhifuMoney;//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
                xihuOrder.setXihuOrderPaymentTypes(1);
                xihuOrder.setInsertTime(new Date());
                xihuOrder.setCreateTime(new Date());
            xihuOrder.setXihuOrderTruePrice(zhifuMoney);
            xihuOrder.setXihuOrderTypes(3);
                xihuOrderService.insert(xihuOrder);//新增订单
            yonghuEntity.setNewMoney(balance);
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
            if(yonghuEntity.getYonghuSumJifen()  < 10000)
                yonghuEntity.setHuiyuandengjiTypes(1);
            else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                yonghuEntity.setHuiyuandengjiTypes(2);
            else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                yonghuEntity.setHuiyuandengjiTypes(3);

            yonghuService.updateById(yonghuEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }

    /**
     * 添加订单
     */
    /*@RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String xihuOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        Integer xihuOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("xihuOrderPaymentTypes")));//支付类型
        String yuyueTime = String.valueOf(params.get("yuyueTime"));//预约日期

        String data = String.valueOf(params.get("xihus"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> xihus = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<XihuOrderEntity> xihuOrderList = new ArrayList<>();
        //商品表
        List<XihuEntity> xihuList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型名称")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

        //循环取出需要的数据
        for (Map<String, Object> map : xihus) {
           //取值
            Integer xihuId = Integer.valueOf(String.valueOf(map.get("xihuId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            XihuEntity xihuEntity = xihuService.selectById(xihuId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(xihuEntity.getXihuKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(xihuEntity.getXihuName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                xihuEntity.setXihuKucunNumber(xihuEntity.getXihuKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            XihuOrderEntity xihuOrderEntity = new XihuOrderEntity<>();

            //赋值订单信息
            xihuOrderEntity.setXihuOrderUuidNumber(xihuOrderUuidNumber);//订单号
            xihuOrderEntity.setXihuId(xihuId);//洗护
            xihuOrderEntity.setYonghuId(userId);//用户
            xihuOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            xihuOrderEntity.setXihuOrderTypes(3);//订单类型
            xihuOrderEntity.setXihuOrderPaymentTypes(xihuOrderPaymentTypes);//支付类型
            try {
                xihuOrderEntity.setYuyueTime(new SimpleDateFormat("yyyy-MM-dd").parse(yuyueTime));//预约日期 ？？？？？？
            } catch (ParseException e) {
                e.printStackTrace();
            }
            xihuOrderEntity.setInsertTime(new Date());//订单创建时间
            xihuOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(xihuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(xihuEntity.getXihuNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + money); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + money); //设置现积分

                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    xihuOrderEntity.setXihuOrderTruePrice(money);

                }
            }
            xihuOrderList.add(xihuOrderEntity);
            xihuList.add(xihuEntity);

        }
        xihuOrderService.insertBatch(xihuOrderList);
        xihuService.updateBatchById(xihuList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }*/











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        if("用户".equals(role)){
            XihuOrderEntity xihuOrder = xihuOrderService.selectById(id);
            Integer buyNumber = xihuOrder.getBuyNumber();
            Integer xihuOrderPaymentTypes = xihuOrder.getXihuOrderPaymentTypes();
            Integer xihuId = xihuOrder.getXihuId();
            if(xihuId == null)
                return R.error(511,"查不到该物品");
            XihuEntity xihuEntity = xihuService.selectById(xihuId);
            if(xihuEntity == null)
                return R.error(511,"查不到该物品");
            Double xihuNewMoney = xihuEntity.getXihuNewMoney();
            if(xihuNewMoney == null)
                return R.error(511,"物品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型名称")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(xihuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = xihuEntity.getXihuNewMoney() * buyNumber  * zhekou;

                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(xihuEntity.getXihuPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 1000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }

            xihuEntity.setXihuKucunNumber(xihuEntity.getXihuKucunNumber() + buyNumber);



            xihuOrder.setXihuOrderTypes(2);//设置订单状态为退款
            xihuOrderService.updateById(xihuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            xihuService.updateById(xihuEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        XihuOrderEntity  xihuOrderEntity = new  XihuOrderEntity();;
        xihuOrderEntity.setId(id);
        xihuOrderEntity.setXihuOrderTypes(4);
        boolean b =  xihuOrderService.updateById( xihuOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }









    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        XihuOrderEntity  xihuOrderEntity = new  XihuOrderEntity();
        xihuOrderEntity.setId(id);
        xihuOrderEntity.setXihuOrderTypes(5);
        boolean b =  xihuOrderService.updateById( xihuOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText,HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            XihuOrderEntity xihuOrder = xihuOrderService.selectById(id);
        if(xihuOrder == null)
            return R.error(511,"查不到该订单");
        if(xihuOrder.getXihuOrderTypes() != 5)
            return R.error(511,"您不能评价");
        Integer xihuId = xihuOrder.getXihuId();
        if(xihuId == null)
            return R.error(511,"查不到该物品");

        XihuCommentbackEntity xihuCommentbackEntity = new XihuCommentbackEntity();
            xihuCommentbackEntity.setId(id);
            xihuCommentbackEntity.setXihuId(xihuId);
            xihuCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            xihuCommentbackEntity.setXihuCommentbackText(commentbackText);
            xihuCommentbackEntity.setReplyText(null);
            xihuCommentbackEntity.setInsertTime(new Date());
            xihuCommentbackEntity.setUpdateTime(null);
            xihuCommentbackEntity.setCreateTime(new Date());
            xihuCommentbackService.insert(xihuCommentbackEntity);

            xihuOrder.setXihuOrderTypes(1);//设置订单状态为已评价
            xihuOrderService.updateById(xihuOrder);//根据id更新
            return R.ok();
        }else{
            return R.error(511,"您没有权限评价");
        }
    }







}
