package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;

import entity.PageResult;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private TbBrandMapper brandMapper;

	@Override
	public List<TbBrand> findAll() {
		return brandMapper.selectByExample(null);
	}

	@Override
	public PageResult findByPage(TbBrand tbBrand,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<TbBrand> pages=null;//结果集
		//是否模糊查询
		if (tbBrand!=null) {
			TbBrandExample example=new TbBrandExample();
			Criteria criteria=example.createCriteria();
			String name=tbBrand.getName();
			String firstChar=tbBrand.getFirstChar();
			if (name!=null&&name.length()>0) {
				criteria.andNameLike("%"+name+"%");
			}
			if (firstChar!=null&&firstChar.length()>0) {
				criteria.andFirstCharLike(firstChar);
			}
			pages=(Page<TbBrand>) brandMapper.selectByExample(example);
		}else {
			pages=(Page<TbBrand>) brandMapper.selectByExample(null);	
		}
		return new PageResult(pages.getTotal(),pages.getResult());
		
//		List<TbBrand>list=brandMapper.selectByExample(null);
//		return new PageResult(new PageInfo<TbBrand>().getTotal(),list);
		
	}

	@Override
	public void add(TbBrand tbBrand) throws Exception {
			brandMapper.insert(tbBrand);
	}

	@Override
	public TbBrand findOneById(Long id) throws Exception {
		return brandMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbBrand tbBrand) throws Exception {
		brandMapper.updateByPrimaryKey(tbBrand);
	}

	@Override
	public void delete(Long[] ids) throws Exception {
		for(Long id : ids) {
			brandMapper.deleteByPrimaryKey(id);
		}
		
	}

	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return brandMapper.selectOptionList();
	}

}
