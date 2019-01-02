package myjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import myjava.model.Circle;

@Component
public class MyJdbcDaoimpl {
	
	
	
	

	private DataSource datasource;
	
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParamTemplate;
	

	/*public Circle getCircle(int circleId) {

		Connection conn = null;
		
		

		//String driver = "org.apache.derby.jdbc.ClientDriver";

		try {

			//Class.forName(driver).newInstance();

			//conn = DriverManager.getConnection("jdbc:derby://localhost:1527/db");
			conn = datasource.getConnection();

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM CIRCLE WHERE id=?");

			ps.setInt(1, circleId);

			Circle cir = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				cir = new Circle(circleId, rs.getString("name"));
			}

			rs.close();

			ps.close();

			return cir;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}*/
	
	public int getCircleId(String name) {
		
		//jdbcTemplate.setDataSource(this.datasource);
		String sql = "SELECT ID FROM CIRCLE WHERE NAME=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {name},Integer.class);
	}
	
	public String getCircleName(int id) {
		
		//jdbcTemplate.setDataSource(this.datasource);
		String sql = "SELECT NAME FROM CIRCLE WHERE ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id},String.class);
	}
	
	public boolean addCircle(int id, String name) {
		
		String sql = "INSERT INTO CIRCLE(ID,NAME) VALUES (:id,:name)";
		SqlParameterSource namedParam = new MapSqlParameterSource("id",id).addValue("name", name);
		return namedParamTemplate.update(sql, namedParam) ==0 ? true : false;
	}
	
	public List<Circle> getCircle() {
		
		String sql = "SELECT * FROM CIRCLE";
		return jdbcTemplate.query(sql, new CircleRowMapper());
	}
	
	public Circle getCircleForId(int id) {
		String sql = "SELECT * FROM CIRCLE where ID=:id";
		SqlParameterSource sqlParam = new MapSqlParameterSource("id",id);
		return namedParamTemplate.queryForObject(sql, sqlParam, new CircleRowMapper());
	//	return namedParamTemplate.queryForObject(sql, new Object[] {id}, new CircleRowMapper());
	}
	
	public void createTable() {
		String sql = "CREATE TABLE TRIANGLE (ID INTEGER ,NAME VARCHAR(20)) ";
		jdbcTemplate.execute(sql);
		
	}
	public static final class CircleRowMapper implements RowMapper<Circle> {

		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("row number"+rowNum);
			// TODO Auto-generated method stub
			Circle cir = new Circle();
			cir.setId(rs.getInt("ID"));
			cir.setName(rs.getString("NAME"));
			return cir;
		}

	}

	public DataSource getDatasource() {
		return datasource;
	}

	@Autowired
	public void setDatasource(DataSource datasource) {
		//this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.namedParamTemplate = new NamedParameterJdbcTemplate(datasource);
		//this.jdbcTemplate = new JdbcTemplate();
	}

}
