package com.hyd.goodscenter.data.protocal

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
     商品分类
 */
data class Category(
    val id: Int, //分类ID
    val categoryName: String, //分类名称
    val categoryIcon: String = "", //分类图标
    val parentId: Int, //分类 父级ID
    var isSelected: Boolean = false//是否被选中
)