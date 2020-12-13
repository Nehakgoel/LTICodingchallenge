package com.lti.coding.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.lti.coding.entity.Account;
import com.lti.coding.entity.Customer;

public class AccountDao {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int createAccount(Account account) {

		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO ACCOUNT (TYPE,AMOUNT) VALUES (:type,:account)");
		return this.namedParameterJdbcTemplate.update(query.toString(), new BeanPropertySqlParameterSource(account));
	}

	public int updateAccount(Account account) {
		StringBuilder query = new StringBuilder();

		query.append("UPDATE ACCOUNT SET TYPE = :type, "
				+ "AMOUNT =:amount WHERE ID = :id");
		return this.namedParameterJdbcTemplate.update(query.toString(), new BeanPropertySqlParameterSource(account));
	}

	public int removeAccount(int id) {
		StringBuilder query = new StringBuilder();

		query.append("DELETE FROM ACCOUNT WHERE ID = :id");
		return this.jdbcTemplate.update(query.toString(), new Object[] {id});
	}
	
	public Map<Integer, Account> getAccounts(int fromId, int toId){
		
		StringBuilder query = new StringBuilder();
		
		Map<String, Object> paramMap = new HashMap<>();
		
		List<Integer> idList = new ArrayList<>();
		idList.add(fromId);
		idList.add(toId);
		
		paramMap.put("id", idList);

		query.append("SELECT ID,TYPE,AMOUNT FROM ACCOUNT WHERE ID in (id)");
		return this.namedParameterJdbcTemplate.query(query.toString(),  paramMap, new ResultSetExtractor< Map<Integer, Account>>() {

			@Override
			public Map<Integer, Account> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer, Account> result = new HashMap<>();
				
				while(rs.next()) {
					Account account = new Account();
					account.setAmount(rs.getDouble("AMOUNT"));
					account.setType(rs.getString("TYPE"));
					account.setId(rs.getInt("ID"));
					result.put(account.getId(), account);
				}
				
				return result;
			}
		});
	}
}