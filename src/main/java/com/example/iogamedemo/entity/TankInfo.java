package com.example.iogamedemo.entity;

import lombok.Data;

/**
 * @description:
 * @author: lrq
 * @create: 2022-07-28 17:54
 **/
@Data
public class TankInfo {
    private float x;
    private float y;
    private float degree;

    public TankInfo(float x, float y, float degree) {
        this.x = x;
        this.y = y;
        this.degree = degree;
    }
}
