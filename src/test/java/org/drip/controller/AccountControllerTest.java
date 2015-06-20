package org.drip.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drip.config.WebTestConfig;
import org.drip.model.BillSummary;
import org.drip.model.Customer;
import org.drip.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebTestConfig.class)
@WebAppConfiguration
public class AccountControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private FilterChainProxy springSecurityFilter;
	
	private AccountService accountServiceMock;
	
	private MockUserDetails userDetails;
	
	@Before
	public void setup() {
		accountServiceMock = Mockito.mock(AccountService.class);
		mockMvc = MockMvcBuilders.standaloneSetup(new AccountController(accountServiceMock))
		        .addFilters(springSecurityFilter).build();
		
		Customer customer = new Customer();
		customer.setId(1L);
		userDetails = new MockUserDetails(customer);
		
	}
	
	@Test
	public void testGetAllBillSummaries() throws Exception {
		
		BillSummary firstBS = new BillSummary();
		firstBS.setBillDate(new Date());
		firstBS.setAmountDue(Double.valueOf(30.00));
		firstBS.setAmountPaid(Double.valueOf(15.00));
		firstBS.setCurrentAmount(Double.valueOf(18.00));
		
		BillSummary secondBS = new BillSummary();
		firstBS.setBillDate(new Date());
		firstBS.setAmountDue(Double.valueOf(45.00));
		firstBS.setAmountPaid(Double.valueOf(25.00));
		firstBS.setCurrentAmount(Double.valueOf(40.00));
		
		Map<String, List<BillSummary>> map = new HashMap<String, List<BillSummary>>();
		String firstKey = "12345";
		map.put(firstKey, Arrays.asList(firstBS));
		String secondKey = "12346";
		map.put(secondKey, Arrays.asList(secondBS));
		
		Mockito.when(accountServiceMock.getBillSummaries(1L)).thenReturn(map);
		
		mockMvc.perform(get("/accounts/bills").with(user(userDetails)))
		        .andExpect(status().isOk()).andExpect(view().name("bill-history"))
		        .andExpect(forwardedUrl("bill-history"))
		        .andExpect(model().attribute("billSummariesMap", hasKey(equalTo(firstKey))))
		        .andExpect(model().attribute("billSummariesMap", hasKey(equalTo(secondKey))))
		        .andExpect(model().attribute("billSummariesMap", hasValue(hasSize(1))));
		
		Mockito.verify(accountServiceMock, Mockito.times(1)).getBillSummaries(1L);
		
	}
	
	@Test
	public void testGetBillSummariesForGivenAccount() throws Exception {
		BillSummary firstBS = new BillSummary();
		firstBS.setBillDate(new Date());
		firstBS.setAmountDue(Double.valueOf(30.00));
		firstBS.setAmountPaid(Double.valueOf(15.00));
		firstBS.setCurrentAmount(Double.valueOf(18.00));
		
		List<BillSummary> billSummaries = Arrays.asList(firstBS);
		
		Mockito.when(accountServiceMock.getBillSummaries("1234")).thenReturn(billSummaries);
		
		mockMvc.perform(get("/accounts/1234/bills").with(user(userDetails))).andExpect(status().isOk()).andExpect(view().name("bill-history")).andExpect(model().attribute("billSummaries", hasSize(1)));
		
		Mockito.verify(accountServiceMock, Mockito.times(1)).getBillSummaries("1234");
		
	}
	
	private final class MockUserDetails extends Customer implements UserDetails {
		
		private static final long serialVersionUID = 1L;
		
		public MockUserDetails(Customer customer) {
			super(customer);
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.createAuthorityList("USER");
		}
		
		@Override
		public String getPassword() {
			return null;
		}
		
		@Override
		public String getUsername() {
			return "user";
		}
		
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}
		
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}
		
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
		
		@Override
		public boolean isEnabled() {
			return true;
		}
		
	}
}
