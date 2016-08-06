package com.yf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yf.model.Material;
import com.yf.model.Order;
import com.yf.utils.RowMapperUtil;

@Repository("materialDao")
public class MaterialDao extends DaoAdapter{

	private static RowMapper<Material> materialRowMapper;
	
	static {
		
		materialRowMapper = new RowMapper<Material>() {

			public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				return (Material)RowMapperUtil.getRowMapper(Material.class, rs, "");
			}
		
		
		};
		
	}
	
	public  int insertMaterial(Material material){
		
		try {
			
			String sql = "insert into order values(?,?,?,?,?,?,?)";
			
			return super.getJdbcTemplate().queryForInt(sql,material.getId(),material.getNum(),material.getName(),material.getStantard(),material.getMaterial(),material.getLife(),material.getDrawing());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		
	}
	
	public int updateMaterial(Material material){
		try {
			
			String sql = "update material set num=?,name=?,stantard=?,material=?,life=?,drawing=? where id = ?";
			
			return super.getJdbcTemplate().update(sql,material.getNum(),material.getName(),material.getStantard(),material.getMaterial(),material.getLife(),material.getDrawing(),material.getId());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int deleteMaterial(final String []ids){
		
		try {
			
			String sql = "delete from material where id = ?";
			
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
	
	public Material getMaterialById(String id){
		
		try {
			
			String sql = "select * from material where id = ?";
			
			return super.getJdbcTemplate().queryForObject(sql, materialRowMapper,id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<Material> getAllMaterial(SearchEntity searchEntity){
		
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
			return super.getJdbcTemplate().query(sql, materialRowMapper,searchEntity.getSearchValues().toArray());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}		
	}
}
