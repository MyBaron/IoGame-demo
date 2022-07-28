package com.example.iogamedemo.service;

import com.example.iogamedemo.common.pb.UserListPb;
import com.example.iogamedemo.common.pb.UserTankInfoPb;
import com.example.iogamedemo.entity.TankInfo;
import com.iohao.game.bolt.broker.client.external.session.UserSessions;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 业务处理类
 * @author: lrq
 * @create: 2022-07-12 18:27
 **/
@Component
@Slf4j
public class BizService {
    final static Map<Integer, TankInfo> TANK_INFO_MAP = new ConcurrentHashMap<>();

    /**
     * 加入游戏
     * @param id
     * @return
     */
    public TankInfo join(int id) {
        // 初始化坦克
        TankInfo tankInfo = new TankInfo(0, 0, 0);
        TANK_INFO_MAP.put(id, tankInfo);
        return tankInfo;
    }

    /**
     * 获取所有用户数据
     * @return
     */
    public Map<Integer, TankInfo> getAll() {
        return Collections.unmodifiableMap(TANK_INFO_MAP);
    }

}
