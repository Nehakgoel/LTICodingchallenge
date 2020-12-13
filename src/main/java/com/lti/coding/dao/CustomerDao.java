package com.lti.coding.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lti.coding.entity.Account;
import com.lti.coding.entity.Customer;

@Repository
public class CustomerDao {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int createCustomer(Customer customer) {
		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO CUSTOMER (FIRST_NAME,LAST_NAME,ADDRESS,PHONE_NUMBER,SOCIAL_SECURITY_NUMBER)"
				+ " VALUES (:firstName,:lastName,:address:phoneNumber,:socialSecurityNumber)");
		return this.namedParameterJdbcTemplate.update(query.toString(), new BeanPropertySqlParameterSource(customer));

	}

	public int updateCustomer(Customer customer) {

		StringBuilder query = new StringBuilder();

		query.append("UPDATE CUSTOMER SET FIRST_NAME = :firstName, "
				+ "LAST_NAME =:lastName, ADDRESS=:address, PHONE_NUMBER =:phoneNumber, "
				+ "SOCIAL_SECURITY_NUMBER = :socialSecurityNumber WHERE ID = :id");
		return this.namedParameterJdbcTemplate.update(query.toString(), new BeanPropertySqlParameterSource(customer));
	}

	public int removeCustomer(int id) {

		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM ACCOUNT WHERE CUSTOMER_ID = :id");
		this.jdbcTemplate.update(query.toString(), new Object[] {id});
		
		query = new StringBuilder();
		query.append("DELETE FROM CUSTOMER WHERE ID = :id");
		return this.jdbcTemplate.update(query.toString(), new Object[] {id});
	}

	public Map<Integer, Customer> getDetails() {

		StringBuilder query = new StringBuilder();

		query.append("select CUS.ID, FIRST_NAME, LAST_NAME, ADDRESS, SOCIAL_SECURITY_NUMBER, PHONE_NUMBER, ACC.ID AS ACCOUNT_ID, TYPE, AMOUNT"
				+ " from customer cus join account acc on cus.id = acc.customer_id");
		return this.jdbcTemplate.query(query.toString(), new ResultSetExtractor<Map<Integer, Customer>>() {

			@Override
			public Map<Integer, Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer, Customer> cusList = new HashMap<>();
				while(rs.next()) {
					if(cusList.containsKey(rs.getInt("ID"))) {
						Account account = new Account();
						account.setId(rs.getInt("ACCOUNT_ID"));
						account.setAmount(rs.getDouble("AMOUNT"));
						account.setType(rs.getString("TYPE"));


						cusList.get(rs.getInt("ID")).getAccount().add(account);
					}else {
						Customer cus = new Customer();
						cus.setId(rs.getInt("ID"));
						cus.setFirstName(rs.getString("FIRST_NAME"));
						cus.setLastName(rs.getString("LAST_NAME"));
						cus.setAddress(rs.getString("ADDRESS"));
						cus.setSocialSecurityNumber(rs.getLong("SOCIAL_SECURITY_NUMBER"));
						
						Account account = new Account();
						account.setId(rs.getInt("ACCOUNT_ID"));
						account.setAmount(rs.getDouble("AMOUNT"));
						account.setType(rs.getString("TYPE"));
						
						cus.setAccount(Arrays.asList(account));
						cusList.put(cus.getId(), cus);
					}
				}

				return cusList;
			}

		});
	}
}



