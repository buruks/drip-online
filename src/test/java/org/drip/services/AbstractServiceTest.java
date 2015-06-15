package org.drip.services;

import java.io.FileInputStream;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.lang.time.DateUtils;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
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
	
	protected IDataSet getDataSet() throws Exception {
		String dataSetFile = "src/test/resources/testData.xml";
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(dataSetFile));
		ReplacementDataSet rDataSet = new ReplacementDataSet(dataSet);
		rDataSet.addReplacementObject("[expire_date]", DateUtils.addDays(new Date(), 1));
		return rDataSet;
	}
	
}
