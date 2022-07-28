package com.example.iogamedemo.action;

import com.example.iogamedemo.common.pb.LoginVerify;
import com.example.iogamedemo.common.pb.TankInfoPb;
import com.example.iogamedemo.common.pb.UserListPb;
import com.example.iogamedemo.entity.TankInfo;
import com.example.iogamedemo.service.BizService;
import com.iohao.game.action.skeleton.annotation.ActionController;
import com.iohao.game.action.skeleton.annotation.ActionMethod;
import com.iohao.game.action.skeleton.core.flow.FlowContext;
import com.iohao.game.bolt.broker.client.external.session.UserSessions;
import com.iohao.game.bolt.broker.client.kit.UserIdSettingKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: lrq
 * @create: 2022-07-26 20:17
 **/
@Component
@Slf4j
@ActionController(Cmd.Common)
public class CommonAction {

    @Resource
    BizService bizService;

    @ActionMethod(Cmd.LOGIN)
    public TankInfoPb here(LoginVerify loginVerify, FlowContext flowContext) {
        boolean success = UserIdSettingKit.settingUserId(flowContext, loginVerify.id);
        log.info("登录 id = {},结果 = {},当前在线人数={}", loginVerify, success, UserSessions.me().countOnline());
        TankInfo join = bizService.join( loginVerify.id);
        TankInfoPb tankInfoPb = new TankInfoPb();
        tankInfoPb.x = join.getX();
        tankInfoPb.y = join.getY();
        tankInfoPb.degree = join.getDegree();
        return tankInfoPb;
    }
}
