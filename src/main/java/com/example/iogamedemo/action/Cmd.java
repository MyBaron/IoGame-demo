package com.example.iogamedemo.action;

/**
 * @description:
 * @author: lrq
 * @create: 2022-07-12 18:23
 **/
public interface Cmd {
    int Common = 0;
    int Tank = 1;

    /** 示例 here 方法 */
    int LOGIN = 1;

    /**移动*/
    int MOVE = 2;

    /**
     * 用户移动
     */
    int UserMove = 3;

    /**
     * 用户列表
     */
    int UserList = 4;

}
