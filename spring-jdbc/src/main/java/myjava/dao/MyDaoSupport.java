package myjava.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import myjava.dao.MyJdbcDaoimpl.CircleRowMapper;
import myjava.model.Circle;

@Component(value="myDaoSupport")
public class MyDaoSupport extends NamedParameterJdbcDaoSupport{
	
public List<Circle> getCircle() {
		
		String sql = "SELECT * FROM CIRCLE"; //in case of hibernate the name of the table in the query should match the entity.
		return this.getJdbcTemplate().query(sql, new CircleRowMapper());
	}

}
