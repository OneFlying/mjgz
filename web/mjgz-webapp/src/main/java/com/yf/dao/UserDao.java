package com.yf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yf.dao.DaoAdapter;
import com.yf.model.User;
import com.yf.utils.RowMapperUtil;

@Repository("userDao")
public class UserDao extends DaoAdapter{

	
	private static RowMapper<User> userRowMapper;
	
	static {
		
		userRowMapper = new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return (User)RowMapperUtil.getRowMapper(User.class, rs, "");		
			}
		};
	}
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	public int insertUser(User user){
		
		try {
			
			String sql = "insert into user values(?,?,?,?)";
			
			return super.getJdbcTemplate().update(sql,user.getId(),user.getName(),user.getPhone(),user.getDuty());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user){
		try {
			
			String sql = "update user set name=?,phone=?,duty=? where id=?";
			
			return super.getJdbcTemplate().update(sql,user.getName(),user.getPhone(),user.getDuty(),user.getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 分页获取全部用户信息
	 * @param searchEntity
	 * @return
	 */
	public List<User> getUsers(SearchEntity searchEntity){
		
		try {
			
			String totalSql = searchEntity.toPageTotalSql();
			String sql = searchEntity.toSql();
			int total = 0;
			if(searchEntity.getPageTotalSearchValues().size() <= 0){
				
				total = super.getJdbcTemplate().queryForInt(totalSql);
				
			}else{
				total = super.getJdbcTemplate().queryForInt(sql,searchEntity.getPageTotalSearchValues().toArray());
			}
			
			searchEntity.setTotal(total);
			
			return super.getJdbcTemplate().query(sql, userRowMapper,searchEntity.getSearchValues().toArray());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	public User getUserById(String id){
		
		try {
			
			String sql = "select * from user where id = ?";
			
			return super.getJdbcTemplate().queryForObject(sql, userRowMapper);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}		
	}
	
	/**
	 *批量删除
	 * @param ids
	 * @return
	 */
	public int deleteUser(final String []ids){
		try {
			
			String sql = "delete from user where id=?";
			
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
			e.printStackTrace();
			return 0;
		}
	}
	
	
}
