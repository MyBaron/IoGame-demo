package com.example.iogamedemo.common.pb;

import com.baidu.bjf.remoting.protobuf.annotation.EnableZigZap;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.example.iogamedemo.common.SpringGameProtoFile;
import com.iohao.game.widget.light.protobuf.ProtoFileMerge;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @description:
 * @author: lrq
 * @create: 2022-07-28 17:49
 **/
@ToString
@EnableZigZap
@ProtobufClass
@FieldDefaults(level = AccessLevel.PUBLIC)
@ProtoFileMerge(fileName = SpringGameProtoFile.COMMON_FILE_NAME, filePackage = SpringGameProtoFile.COMMON_FILE_PACKAGE)
public class UserListPb {
    List<UserTankInfoPb> userTankInfoPbs;
}
