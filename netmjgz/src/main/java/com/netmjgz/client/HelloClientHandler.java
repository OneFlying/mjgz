package com.netmjgz.client;

import com.netmjgz.msg.HeartBeatAck;
import com.netmjgz.msg.HeartBeatReq;
import com.netmjgz.msg.LiuchengXinxiAck;
import com.netmjgz.msg.Msg;
import com.netmjgz.msg.MsgType;
import com.netmjgz.msg.SaomiaoXinxiAck;
import com.netmjgz.msg.SaomiaoXinxiReq;
import com.netmjgz.util.JsonUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msgstr) throws Exception {
        
// 收到消息直接打印输出
        
        Msg msg = (Msg) JsonUtils.JsonToObj(msgstr, Msg.class);
        //判断类型
        if(msg.getId()==MsgType.HEART_BEAT){//心跳
        	
        	HeartBeatAck heartBeatAck = (HeartBeatAck) JsonUtils.JsonToObj(msg.getObject().toString(), HeartBeatAck.class);
        	System.out.println("[Client][心跳消息]"+ctx.channel().remoteAddress() + " Say : " + msgstr+"[对象]"+heartBeatAck.toString());
//            ctx.writeAndFlush(msgack+"\n");
        }else if(msg.getId()==MsgType.SAOMAXINXI){//扫描信息
        	
        	SaomiaoXinxiAck saomiaoXinxiAck = (SaomiaoXinxiAck) JsonUtils.JsonToObj(msg.getObject().toString(), SaomiaoXinxiAck.class);
        	System.out.println("[Client][扫描信息]"+ctx.channel().remoteAddress() + " Say : " + msgstr+"[对象]"+saomiaoXinxiAck.toString());
        	
        }else if(msg.getId()==MsgType.LIUCHENGXINXI){//流程节点
        	
        	LiuchengXinxiAck liuchengXinxiAck = (LiuchengXinxiAck) JsonUtils.JsonToObj(msg.getObject().toString(), LiuchengXinxiAck.class);
        	System.out.println("[Client][流程节点获取]"+ctx.channel().remoteAddress() + " Say : " + msgstr+"[对象]"+liuchengXinxiAck.toString());
        	
        }else {
        	System.out.println("[Client][未知消息]"+ctx.channel().remoteAddress() + " Say : " + msgstr);
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
}