package com.yf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yf.model.ProductionNode;
import com.yf.model.Track;
import com.yf.utils.RowMapperUtil;

@Repository("productionDao")
public class ProductionDao extends DaoAdapter{

	private static RowMapper<ProductionNode> proRowMapper;
	
	static {
		
		proRowMapper = new RowMapper<ProductionNode>() {

			public ProductionNode mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return (ProductionNode)RowMapperUtil.getRowMapper(ProductionNode.class, rs, "");
			}
		};
	}
	
	public int insertProduction(ProductionNode productionNode){
		
		try {
			
			String sql = "insert into productionnode values(?,?)";
			
			return super.getJdbcTemplate().update(sql,productionNode.getId(),productionNode.getName());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int updateProduction(ProductionNode productionNode){
		
		try {
			
			String sql = "update productionnode set name=? where id = ?";
			
			return super.getJdbcTemplate().update(sql,productionNode.getName(),productionNode.getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int deleteProduction(final String []ids){
		
		try {
			
			String sql = "delete from productionnode where id = ?";
			
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
	
	public List<ProductionNode> getAllProduction(SearchEntity searchEntity){
		
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
			
			return super.getJdbcTemplate().query(sql,proRowMapper,searchEntity.getSearchValues());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public ProductionNode getProductionNodeById(String id){
		
		
		try {
			String sql = "select * from productionnode where id = ?";
			return super.getJdbcTemplate().queryForObject(sql, proRowMapper,id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public List<ProductionNode> getAll(){
		try {
			
			String sql = "select * from productionnode";
			
			return super.getJdbcTemplate().query(sql, proRowMapper);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
