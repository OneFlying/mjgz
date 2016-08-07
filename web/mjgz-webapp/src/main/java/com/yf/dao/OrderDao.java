package com.yf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yf.constant.OrderConstant;
import com.yf.model.Order;
import com.yf.utils.RowMapperUtil;

@Repository("orderDao")
public class OrderDao extends DaoAdapter{

	private static RowMapper<Order> orderRowMapper;
	
	static {
		
		orderRowMapper = new RowMapper<Order>() {

			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				return (Order)RowMapperUtil.getRowMapper(Order.class, rs, "");
			}
		};
	}
	
	public  int insertOrder(Order order){
		
		try {
			
			String sql = "insert into mjorder values(?,?,?,?,?)";
			
			return super.getJdbcTemplate().update(sql,order.getId(),order.getMaterialId(),order.getCode(),order.getStatus(),order.getNode());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	public int updateOrder(Order order){
		try {
			
			String sql = "update mjorder set materialId=?,code=?,status=?,node=? where id = ?";
			
			return super.getJdbcTemplate().update(sql,order.getMaterialId(),order.getCode(),order.getStatus(),order.getNode(),order.getId());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int updateOrderCurrentNode(String orderId,String node){
		
		try {
			
			String sql = "update mjorder set node=? where id=?";
			
			return super.getJdbcTemplate().update(sql,node,orderId);
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int deleteOrder(final String []ids){
		
		try {
			
			String sql = "delete from mjorder where id = ?";
			
			return super.getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
				
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, ids[i]);
				}
				
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return ids.length;
				}
			}).length;
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	public Order getOrderById(String id){
		
		try {
			
			String sql = "select * from mjorder where id = ?";
			
			return super.getJdbcTemplate().queryForObject(sql, orderRowMapper,id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<Order> getAllOrder(SearchEntity searchEntity){
		
		try {
			
			String totalSql = searchEntity.toPageTotalSql();
			String sql = searchEntity.toSql();
			
			int total = 0;
			if(searchEntity.getPageTotalSearchValues().size() <= 0){
				total = super.getJdbcTemplate().queryForInt(totalSql);
			}else{
				total = super.getJdbcTemplate().queryForInt(totalSql,searchEntity.getPageTotalSearchValues().toArray());
			}
			searchEntity.setTotal(total);
			return super.getJdbcTemplate().query(sql, orderRowMapper,searchEntity.getSearchValues().toArray());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}		
	}
	
	/**
	 * 获取所有正在被执行的订单
	 * @return
	 */
	public List<Order> getOrderExcute(){
		
		try {
			
			String sql = "select * from mjorder where status = ?";
			
			return super.getJdbcTemplate().query(sql, orderRowMapper,OrderConstant.ORDER_EXCUTING);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
}
