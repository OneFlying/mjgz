package com.netmjgz.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.netmjgz.msg.HeartBeatReq;
import com.netmjgz.msg.Msg;
import com.netmjgz.msg.MsgType;
import com.netmjgz.msg.SaomiaoXinxiReq;
import com.netmjgz.util.JsonUtils;


public class Client {
	private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);  
	  
	public static String host = "127.0.0.1";
    public static int port = 7878;

    /**
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException,
            IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .handler(new HelloClientInitializer());

            // 连接服务端
            final Channel ch = b.connect(host, port).sync().channel();

            //表示延迟1秒后每3秒执行一次。 发送心跳
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
         	   public void run() {
         		   //心跳
         		 /* Msg msg  = new Msg();
         		  msg.setId(MsgType.HEART_BEAT);
         		  HeartBeatReq heartBeatReq = new HeartBeatReq();
         		  heartBeatReq.setData("heartbeat");
         		  msg.setObject(heartBeatReq);
         		  String json = JsonUtils.ObjToJson(msg);*/
         		   //扫描
         		  /*Msg msg  = new Msg();
         		  msg.setId(MsgType.SAOMAXINXI);
         		  SaomiaoXinxiReq saomiaoXinxiReq = new SaomiaoXinxiReq();
         		  saomiaoXinxiReq.setId("1010101010");
         		  saomiaoXinxiReq.setName("张三");
         		  saomiaoXinxiReq.setNode("打磨");
         		  msg.setObject(saomiaoXinxiReq);
         		  String json = JsonUtils.ObjToJson(msg);*/
         		   //流程节点获取
         		  Msg msg  = new Msg();
         		  msg.setId(MsgType.LIUCHENGXINXI);
         		  String json = JsonUtils.ObjToJson(msg);
         		  ch.writeAndFlush(json+"\r\n");
         	   }  
         	  }, 1,3, TimeUnit.SECONDS); 
            
            
            while(true);
            //Thread.currentThread().sleep(300000000);
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }

}
