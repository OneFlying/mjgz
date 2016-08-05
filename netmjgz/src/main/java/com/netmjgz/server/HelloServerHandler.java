package com.netmjgz.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

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

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msgstr)
            throws Exception {
    	
        // 收到消息直接打印输出
        
        Msg msg = (Msg) JsonUtils.JsonToObj(msgstr, Msg.class);
        //判断类型
        if(msg.getId()==MsgType.HEART_BEAT){//心跳
        	
        	HeartBeatReq heartBeatReq = (HeartBeatReq) JsonUtils.JsonToObj(msg.getObject().toString(), HeartBeatReq.class);
        	System.out.println("[Server][心跳消息]"+ctx.channel().remoteAddress() + " Say : " + msgstr+"[对象]"+heartBeatReq.toString());
        	// 返回客户端消息 - 我已经接收到了你的消息
        	Msg msgack  = new Msg();
        	msgack.setId(MsgType.HEART_BEAT);
   		  	HeartBeatAck heartBeatAck = new HeartBeatAck();
   		  	heartBeatAck.setStatus(true);
   		  	heartBeatAck.setData("heartbeat");
   		  	msgack.setObject(heartBeatAck);
   		  	String json = JsonUtils.ObjToJson(msgack);
            ctx.writeAndFlush(json+"\n");
        }else if(msg.getId()==MsgType.SAOMAXINXI){//扫描信息
        	
        	SaomiaoXinxiReq saomiaoXinxiReq = (SaomiaoXinxiReq) JsonUtils.JsonToObj(msg.getObject().toString(), SaomiaoXinxiReq.class);
        	System.out.println("[Server][扫描信息]"+ctx.channel().remoteAddress() + " Say : " + msgstr+"[对象]"+saomiaoXinxiReq.toString());
        	// 返回客户端消息 - 我已经接收到了你的消息
        	Msg msgack  = new Msg();
        	msgack.setId(MsgType.SAOMAXINXI);
        	SaomiaoXinxiAck saomiaoXinxiAck = new SaomiaoXinxiAck();
        	saomiaoXinxiAck.setStatus(true);
        	saomiaoXinxiAck.setData("success");
   		  	msgack.setObject(saomiaoXinxiAck);
   		  	String json = JsonUtils.ObjToJson(msgack);
            ctx.writeAndFlush(json+"\n");
        }else if(msg.getId()==MsgType.LIUCHENGXINXI){//流程节点
        	
        	
        	System.out.println("[Server][流程节点获取]"+ctx.channel().remoteAddress() + " Say : " + msgstr+"[对象]");
        	// 返回客户端消息 - 我已经接收到了你的消息
        	Msg msgack  = new Msg();
        	msgack.setId(MsgType.LIUCHENGXINXI);
        	LiuchengXinxiAck liuchengXinxiAck = new LiuchengXinxiAck();
        	List<String> list =new ArrayList<String>();
        	list.add("打磨");
        	list.add("装箱");
        	liuchengXinxiAck.setList(list);
        	
   		  	msgack.setObject(liuchengXinxiAck);
   		  	String json = JsonUtils.ObjToJson(msgack);
            ctx.writeAndFlush(json+"\n");
        	
        }else {
        	System.out.println("[Server][未知消息]"+ctx.channel().remoteAddress() + " Say : " + msgstr);
        }
        
    }

    /*
     * 
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * 
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	
        /*System.out.println("RamoteAddress : " + ctx.channel().remoteAddress()
                + " active !");

        ctx.writeAndFlush("Welcome to "
                + InetAddress.getLocalHost().getHostName() + " service!\n");*/

        super.channelActive(ctx);
    }
}