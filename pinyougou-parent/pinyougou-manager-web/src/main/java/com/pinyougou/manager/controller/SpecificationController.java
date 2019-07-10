package com.pinyougou.manager.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationOptionService;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

	@Reference
	private SpecificationService specificationService;
	
	@Reference
	private SpecificationOptionService specificationOptionService;
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbSpecification> findAll(){			
		return specificationService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return specificationService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param specification
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Specification specification){
		try {
			TbSpecification tbSpecification=specification.getTbSpecification();
			tbSpecification=specificationService.add(tbSpecification);
			for(TbSpecificationOption tbSpecificationOption :specification.getTbSpecificationOptions()) {
				tbSpecificationOption.setSpecId(tbSpecification.getId());
				specificationOptionService.add(tbSpecificationOption);
			};
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param specification
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Specification specification){
		try {
			specificationService.update(specification.getTbSpecification());
			List<TbSpecificationOption> tbSpecificationOptions=specificationOptionService.findBySpecId(specification.getTbSpecification().getId());
			ArrayList<Long> specOptionId=new ArrayList<>();//选项的id
			for(TbSpecificationOption tbSpecificationOption :tbSpecificationOptions){
				Long id=tbSpecificationOption.getId();
				if (id!=null&&id!=0L) {
					specOptionId.add(tbSpecificationOption.getId());
				}
				
			}
			Long[] ids=new Long[specOptionId.size()];
			ids=specOptionId.toArray(ids);
			specificationOptionService.delete(ids);
			for(TbSpecificationOption tbSpecificationOption :specification.getTbSpecificationOptions()) {
				tbSpecificationOption.setSpecId(specification.getTbSpecification().getId());
				specificationOptionService.add(tbSpecificationOption);
			};
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbSpecification findOne(Long id){
		
		return specificationService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * 本次修改只改动了控制层
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			ArrayList<Long> specOptionId=new ArrayList<>();//选项的id
			specificationService.delete(ids);
			for(Long specId :ids) {
				List<TbSpecificationOption> specificationOptions=specificationOptionService.findBySpecId(specId);
				for(TbSpecificationOption specificationOption:specificationOptions) {
					specOptionId.add(specificationOption.getId());//将选项的id添加进数组中
				}
				
			}
			System.out.println(specOptionId);
			ids=specOptionId.toArray(ids);
			
			specificationOptionService.delete(ids);//根据option的id删除指定行
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbSpecification specification, int page, int rows  ){
		return specificationService.findPage(specification, page, rows);		
	}
	
}
