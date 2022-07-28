package com.example.iogamedemo.action;

import com.example.iogamedemo.common.pb.*;
import com.example.iogamedemo.entity.TankInfo;
import com.example.iogamedemo.service.BizService;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.CmdInfo;
import com.iohao.game.action.skeleton.core.commumication.BroadcastContext;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.core.client.BrokerClientHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: lrq
 * @create: 2022-07-12 18:22
 **/
@Component
@Slf4j
@ActionController(Cmd.Tank)
public class TankAction {

    @Resource
    BizService bizService;


    /**
     * 移动
     *
     * @param movePb @MovePb
     */
    @ActionMethod(Cmd.MOVE)
    public MovePb move(MovePb movePb, FlowContext flowContext) {
        log.info("data = {}", movePb);
        BroadcastContext broadcastContext = BrokerClientHelper.me().getBroadcastContext();
        CmdInfo cmdInfo = CmdInfo.getCmdInfo(Cmd.Tank, Cmd.UserMove);
        UserMovePb userMovePb = new UserMovePb();
        userMovePb.degree = movePb.degree;
        userMovePb.x = movePb.x;
        userMovePb.y = movePb.y;
        userMovePb.userId = (int)flowContext.getUserId();
        broadcastContext.broadcast(cmdInfo, userMovePb);
        return movePb;
    }

    /**
     * 获取列表
     * @return
     */
    @ActionMethod(Cmd.UserList)
    public UserListPb getList() {
        Map<Integer, TankInfo> all = bizService.getAll();
        log.info("data = {}", all);
        List<UserTankInfoPb> collect = all.entrySet().stream().map(k -> {
            UserTankInfoPb userTankInfoPb = new UserTankInfoPb();
            userTankInfoPb.id = k.getKey();
            TankInfo value = k.getValue();
            TankInfoPb tankInfoPb = new TankInfoPb();
            tankInfoPb.x = value.getX();
            tankInfoPb.y = value.getY();
            tankInfoPb.degree = value.getDegree();
            userTankInfoPb.tankInfo = tankInfoPb;
            return userTankInfoPb;
        }).collect(Collectors.toList());
        UserListPb userListPb = new UserListPb();
        userListPb.userTankInfoPbs = collect;
        return userListPb;
    }
}
