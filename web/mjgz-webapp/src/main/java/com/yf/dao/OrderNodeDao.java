package com.yf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yf.constant.OrderNodeConstant;
import com.yf.model.OrderNode;
import com.yf.utils.RowMapperUtil;

@Repository("orderNodeDao")
public class OrderNodeDao extends DaoAdapter{

	private static RowMapper<OrderNode> orderNodeRowMapper;
	
	static {
		
		orderNodeRowMapper = new RowMapper<OrderNode>() {

			public OrderNode mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				return (OrderNode)RowMapperUtil.getRowMapper(OrderNode.class, rs, "");
			}
		};
	}
	
	/**
	 * 插入数据
	 * @param orderNode
	 * @return
	 */
	public int addOrderNode(OrderNode orderNode){
		
		try {
			
			String sql = "insert into node values(?,?,?,?,?)";
			
			return super.getJdbcTemplate().update(sql,orderNode.getNodeName(),orderNode.getOrderId(),orderNode.getStarttime(),orderNode.getOvertime(),orderNode.getStatus());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	/**
	 * 根据订单Id获取正在进行的节点信息
	 * @param orderId
	 * @return
	 */
	public List<OrderNode> getOrderNodes(String orderId){
		
		try {
			
			String sql = "select * from node where orderId = ? and status = ?";
			
			return super.getJdbcTemplate().query(sql, orderNodeRowMapper,orderId,OrderNodeConstant.NODE_EXCUTING);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	/**
	 * 根据订单id和节点名称获取节点对象
	 * @param nodeName
	 * @param orderId
	 * @return
	 */
	public OrderNode getOrderNode(String nodeName,String orderId){
		
		try {
			
			String sql = "select * from node where orderId = ? and nodeName = ?";
			
			return super.getJdbcTemplate().queryForObject(sql, orderNodeRowMapper,orderId,nodeName);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public void updateOrderNode(OrderNode orderNode){
		
		String sql = "update node set orderId = ?,starttime=?,overtime=?,status=? where nodeName=?";
		
		super.getJdbcTemplate().update(sql,orderNode.getOrderId(),orderNode.getStarttime(),orderNode.getOvertime(),orderNode.getStatus(),orderNode.getNodeName());
		
	}
	
}
