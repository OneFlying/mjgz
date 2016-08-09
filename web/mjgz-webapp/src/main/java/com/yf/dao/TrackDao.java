package com.yf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yf.model.Track;
import com.yf.utils.RowMapperUtil;

@Repository("trackDao")
public class TrackDao extends DaoAdapter{

	private static RowMapper<Track> trackRowMapper;
	
	static {
		
		trackRowMapper = new RowMapper<Track>() {

			public Track mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return (Track)RowMapperUtil.getRowMapper(Track.class, rs, "");
			}
		};
	}
	
	public int insertTrack(Track track){
		
		try {
			
			String sql = "insert into track values(?,?,?,?,?)";
			
			return super.getJdbcTemplate().update(sql,track.getId(),track.getWorkName(),track.getNode(),track.getCode(),track.getTime());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int updateTrack(Track track){
		
		try {
			
			String sql = "update track set workName=?,node=?,code=?,time=? where id = ?";
			
			return super.getJdbcTemplate().update(sql,track.getWorkName(),track.getNode(),track.getCode(),track.getTime(),track.getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public int deleteTrack(final String []ids){
		
		try {
			
			String sql = "delete from track where id = ?";
			
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
	
	public List<Track> getAllTracks(SearchEntity searchEntity){
		
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
			
			return super.getJdbcTemplate().query(sql,trackRowMapper,searchEntity.getSearchValues());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public Track getTrackById(String id){
		
		
		try {
			String sql = "select * from track where id = ?";
			return super.getJdbcTemplate().queryForObject(sql, trackRowMapper,id);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 判断用户是否已经扫描过改节点
	 * @param workName
	 * @param node
	 * @param code
	 * @return
	 */
	public Track workNameIsExist(String node,String code){
		
		try {
			
			String sql = "select * from track where node=? and code=?";
			
			return super.getJdbcTemplate().queryForObject(sql, trackRowMapper,node,code);
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} 
		
	}
	
}
