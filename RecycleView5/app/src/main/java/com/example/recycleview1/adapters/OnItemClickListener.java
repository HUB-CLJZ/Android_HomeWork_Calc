package com.example.recycleview1.adapters;

/**
 * 编写回调的步骤
 * 1、创建这个接口
 * 2、定义借口内部的方法
 * 3、提供设置接口的方法(其实是外部实现)
 * 4、接口方法的调用
 */


public interface OnItemClickListener {
    void onItemClick(int position);
}