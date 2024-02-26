package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ChongdianzhuangbaoxiuEntity;
import com.entity.view.ChongdianzhuangbaoxiuView;

import com.service.ChongdianzhuangbaoxiuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 充电桩报修
 * 后端接口
 * @author 
 * @email 
 * @date 2022-03-02 13:45:25
 */
@RestController
@RequestMapping("/chongdianzhuangbaoxiu")
public class ChongdianzhuangbaoxiuController {
    @Autowired
    private ChongdianzhuangbaoxiuService chongdianzhuangbaoxiuService;

    @Autowired
    private StoreupService storeupService;

    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			chongdianzhuangbaoxiu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ChongdianzhuangbaoxiuEntity> ew = new EntityWrapper<ChongdianzhuangbaoxiuEntity>();
		PageUtils page = chongdianzhuangbaoxiuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongdianzhuangbaoxiu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu, 
		HttpServletRequest request){
        EntityWrapper<ChongdianzhuangbaoxiuEntity> ew = new EntityWrapper<ChongdianzhuangbaoxiuEntity>();
		PageUtils page = chongdianzhuangbaoxiuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongdianzhuangbaoxiu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu){
       	EntityWrapper<ChongdianzhuangbaoxiuEntity> ew = new EntityWrapper<ChongdianzhuangbaoxiuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chongdianzhuangbaoxiu, "chongdianzhuangbaoxiu")); 
        return R.ok().put("data", chongdianzhuangbaoxiuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu){
        EntityWrapper< ChongdianzhuangbaoxiuEntity> ew = new EntityWrapper< ChongdianzhuangbaoxiuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chongdianzhuangbaoxiu, "chongdianzhuangbaoxiu")); 
		ChongdianzhuangbaoxiuView chongdianzhuangbaoxiuView =  chongdianzhuangbaoxiuService.selectView(ew);
		return R.ok("查询充电桩报修成功").put("data", chongdianzhuangbaoxiuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu = chongdianzhuangbaoxiuService.selectById(id);
        return R.ok().put("data", chongdianzhuangbaoxiu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu = chongdianzhuangbaoxiuService.selectById(id);
        return R.ok().put("data", chongdianzhuangbaoxiu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu, HttpServletRequest request){
    	chongdianzhuangbaoxiu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chongdianzhuangbaoxiu);
        chongdianzhuangbaoxiuService.insert(chongdianzhuangbaoxiu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
	@IgnoreAuth
    @RequestMapping("/add")
    public R add(@RequestBody ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu, HttpServletRequest request){
    	chongdianzhuangbaoxiu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chongdianzhuangbaoxiu);
        chongdianzhuangbaoxiuService.insert(chongdianzhuangbaoxiu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ChongdianzhuangbaoxiuEntity chongdianzhuangbaoxiu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chongdianzhuangbaoxiu);
        chongdianzhuangbaoxiuService.updateById(chongdianzhuangbaoxiu);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chongdianzhuangbaoxiuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<ChongdianzhuangbaoxiuEntity> wrapper = new EntityWrapper<ChongdianzhuangbaoxiuEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			wrapper.eq("yonghuzhanghao", (String)request.getSession().getAttribute("username"));
		}

		int count = chongdianzhuangbaoxiuService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
