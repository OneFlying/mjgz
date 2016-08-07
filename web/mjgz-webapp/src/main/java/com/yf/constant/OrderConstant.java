package com.yf.constant;

/**
 * 订单常用常量
 * @author abc
 *
 */
public class OrderConstant {

	/**订单未被执行*/
	public final static int ORDER_UNEXCUTE = 0;
	
	/**订单正在被执行*/
	public final static int ORDER_EXCUTING = 1;
	
	/**订单执行超时，开启警报*/
	public final static int ORDER_OVERTIME = 2;
	
	/**订单结束*/
	public final static int ORDER_END = 3;
}
