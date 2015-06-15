package org.drip.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.drip.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordServiceTest extends AbstractServiceTest {
	
	@Autowired
	PasswordService passwordService;
	
	@Autowired
	CustomerService customerService;
	
	public void setupExpiredHash() throws Exception {
	    Connection connection = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection databaseConnection = new DatabaseConnection(connection);
		String dataSetFile = "src/test/resources/expiredHashData.xml";
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(dataSetFile));
		ReplacementDataSet rDataSet = new ReplacementDataSet(dataSet);
		rDataSet.addReplacementObject("[expire_date]", DateUtils.addDays(new Date(), -1));
		DatabaseOperation.UPDATE.execute(databaseConnection, rDataSet);
    }
	
	@Test
	@Transactional
	public void testUpdatePassword() {
		User userBeforePasswordChange = customerService.getCustomer("business@test.com").getUser();				
		String oldPassword = userBeforePasswordChange.getPassword();
		passwordService.updatePassword("business@test.com", "secret2");
		User userAfterPasswordChange = customerService.getCustomer("business@test.com").getUser();
		String newPassword = userAfterPasswordChange.getPassword();
		BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		assertTrue(passwordEncorder.matches("secret", oldPassword));
		assertTrue(passwordEncorder.matches("secret2", newPassword));
		assertTrue(userAfterPasswordChange.getResetHash().getUsed());
	}
	
	@Test	
	public void testGetUserAssociatedWithHash() {
		User user = passwordService.getUserAssociatedWithHash("d41d8cd98f00b204e9800998ecf8427e");
		assertNotNull(user);
	}	
	
	@Test
	public void testGetUserAssociatedWithExpiredHash() throws Exception {
		setupExpiredHash();
		User user = passwordService.getUserAssociatedWithHash("d41d8cd98f00b204e9800998ecf8427e");
		assertNull(user);
	}
	
	@Test
	public void testUpdateHash() {
		String hash = "251a4e8aed2218a0755292327f2125655492e201";
		passwordService.saveHash("business@test.com", hash);
		User user = passwordService.getUserAssociatedWithHash(hash);
		assertNotNull(user);
		assertEquals(hash, user.getResetHash().getHash());
	}
	
	@Test
	public void testSaveHash() {
		String hash = "251a4e8aed2218a0755292327f2125655492e201";
		passwordService.saveHash("jane.doe@test.com", hash);
		User user = passwordService.getUserAssociatedWithHash(hash);
		assertNotNull(user);
		assertEquals(hash, user.getResetHash().getHash());
	}
	
}
