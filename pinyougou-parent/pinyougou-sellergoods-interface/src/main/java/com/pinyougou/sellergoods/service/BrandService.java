package com.pinyougou.sellergoods.service;

import java.util.List;


import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

public interface BrandService {
	
	/**
	 * 返回全部列表
	 * @return
	 */
	
	public List<TbBrand> findAll();
	/**
	 * 分页查询所有
	 * @return
	 */
	public PageResult findByPage(TbBrand tbBrand,Integer pageNum,Integer pageSize);
	
	/**
	 * 添加一个品牌信息
	 * @param tbBrand
	 * @throws Exception
	 */
	public void add(TbBrand tbBrand) throws Exception;
	
	/**
	 * 查询品牌信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TbBrand findOneById(Long id) throws Exception;
	
	/**
	 * 跟新一个品牌信息
	 * @param tbBrand
	 * @return
	 * @throws Exception
	 */
	public void update(TbBrand tbBrand) throws Exception; 
	
	/**
	 * 批量删除
	 * @param ids
	 * @throws Exception
	 */
	public void delete(Long[] ids) throws Exception;
	
	
}
