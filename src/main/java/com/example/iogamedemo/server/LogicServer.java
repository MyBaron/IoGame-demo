package com.example.iogamedemo.server;

import com.example.iogamedemo.action.CommonAction;
import com.example.iogamedemo.action.TankAction;
import com.iohao.game.action.skeleton.core.BarSkeleton;
import com.iohao.game.action.skeleton.core.BarSkeletonBuilderParamConfig;
import com.iohao.game.action.skeleton.core.flow.interal.DebugInOut;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.core.client.BrokerAddress;
import com.iohao.game.bolt.broker.core.client.BrokerClient;
import com.iohao.game.bolt.broker.core.client.BrokerClientBuilder;
import com.iohao.game.bolt.broker.core.common.BrokerGlobalConfig;
import com.iohao.game.common.kit.NetworkKit;

/**
 * @description: 逻辑服
 * @author: lrq
 * @create: 2022-07-12 18:18
 **/
public class LogicServer extends AbstractBrokerClientStartup {

    @Override
    public BarSkeleton createBarSkeleton() {
        // 业务框架构建器 配置
        var config = new BarSkeletonBuilderParamConfig()
                // 扫描 DemoSpringAction.class 所在包
                .addActionController(TankAction.class)
                .addActionController(CommonAction.class)
                ;
        // 业务框架构建器
        var builder = config.createBuilder();
        // 添加控制台输出插件
        builder.addInOut(new DebugInOut());
        return builder.build();
    }

    @Override
    public BrokerClientBuilder createBrokerClientBuilder() {
        BrokerClientBuilder builder = BrokerClient.newBuilder();
        builder.appName("demo spring 游戏逻辑服");
        return builder;
    }

    @Override
    public BrokerAddress createBrokerAddress() {
        // 类似 127.0.0.1 ，但这里是本机的 ip
        String localIp = NetworkKit.LOCAL_IP;
        // broker （游戏网关）默认端口
        int brokerPort = BrokerGlobalConfig.brokerPort;
        return new BrokerAddress(localIp, brokerPort);
    }
}
