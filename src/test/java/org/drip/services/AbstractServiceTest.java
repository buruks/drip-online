package org.drip.services;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.drip.config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@ContextConfiguration(classes = { TestConfig.class })
public abstract class AbstractServiceTest {
	
	private IDatabaseTester databaseTester;
	
	protected DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Before
	public void setupDatabase() throws Exception {
		try {
			databaseTester = new DataSourceDatabaseTester(dataSource);
			databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
			IDataSet dataSet = getDataSet();
			databaseTester.setDataSet(dataSet);
			
			databaseTester.onSetup();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@After
	public void tearDownDatabase() throws Exception {
		if (databaseTester != null) {
			databaseTester.onTearDown();
		}
	}
	
	protected abstract IDataSet getDataSet() throws Exception;
	
}
