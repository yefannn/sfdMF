package com.ekfans.base.content.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ekfans.base.content.model.ContentCategory;
import com.ekfans.pub.util.StringUtil;

/**
 * 资讯分类的帮助类
 * 
 * @ClassName: ProductConst
 * @author 成都易科远见科技有限公司
 * @date 2014-6-19 上午11:28:57
 * @version v1.0 Copyright: Copyright (c) Chengdu Ekfans Technology Co.,Ltd
 *          Company:成都易科远见科技有限公司 www.ekfans.com
 */
public class ContentCategoryHelper {

	/**
	 * 解析商品分类集合，将子集合归类
	 * 
	 * @Title: recoverCategoryList
	 * @Description: TODO(这里用一句话描述这个方法的作用) 详细业务流程: (详细描述此方法相关的业务处理流程)
	 * @param @param productCategoryList
	 * @param @return 设定文件
	 * @return List<ContentCategory> 返回类型
	 * @throws
	 */
	public static void recoverCategoryList(List<ContentCategory> contentCategoryList, List<ContentCategory> list) {
		// 如果传进来的集合为空或者长度小于等于0，返回空
		if (contentCategoryList == null || contentCategoryList.size() <= 0 || list == null || list.size() <= 0) {
			return;
		}

		// 定义一个Map
		Map<String, List<ContentCategory>> map = new HashMap<String, List<ContentCategory>>();
		// 主分类集合
		List<ContentCategory> mainCategory = new ArrayList<ContentCategory>();
		for (int i = 0; i < contentCategoryList.size(); i++) {
			// 获取资讯分类
			ContentCategory category = contentCategoryList.get(i);
			if (category == null) {
				continue;
			}

			// 如果分类的父级ID不为空
			if (!StringUtil.isEmpty(category.getParentId())) {
				// 如果Map中存在该分类的父ID，则将该分类放入List中，并放入Map
				if (map.containsKey(category.getParentId())) {
					List<ContentCategory> childList = map.get(category.getParentId());
					childList.add(category);
					map.put(category.getParentId(), childList);
				} else {
					// 定义个集合。将该分类放入集合，并放入Map
					List<ContentCategory> childList = new ArrayList<ContentCategory>();
					childList.add(category);
					map.put(category.getParentId(), childList);
				}
			} else {
				// 将该分类放入主分类集合中
				mainCategory.add(category);
			}

		}
		// 定义返回List

		for (int i = 0; i < list.size(); i++) {
			ContentCategory category = list.get(i);
			if (category != null) {
				List<ContentCategory> childList = map.get(category.getId());
				setChilds(childList, map);
				category.setChildList(childList);
			}
		}

		return;
	}

	/**
	 * 递归设置子
	 * 
	 * @Title: setChilds
	 * @Description: TODO(这里用一句话描述这个方法的作用) 详细业务流程: (详细描述此方法相关的业务处理流程)
	 * @param @param categoryList
	 * @param @param map 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void setChilds(List<ContentCategory> categoryList, Map<String, List<ContentCategory>> map) {
		if (categoryList == null || categoryList.size() <= 0 || map == null || map.size() <= 0) {
			return;
		}

		for (int i = 0; i < categoryList.size(); i++) {
			ContentCategory category = categoryList.get(i);
			if (category != null) {
				List<ContentCategory> childList = map.get(category.getId());
				setChilds(childList, map);
				category.setChildList(childList);
			}
		}

	}

}
