package org.drip.services.impl;

import java.util.Collection;
import java.util.List;

import org.drip.model.Customer;
import org.drip.model.Role;
import org.drip.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(username);
		if (customer == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new CustomUserDetails(customer);
	}
	
	private final static class CustomUserDetails extends Customer implements UserDetails {

        private static final long serialVersionUID = 8385297473576148615L;

		private CustomUserDetails(Customer customer) {
			super(customer);
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

		@Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
			List<Role> authorityCollect = getUser().getRoles();
			String[] authorityArray = new String[authorityCollect.size()];
			
			for (int i = 0; i < authorityArray.length; i++) {
				authorityArray[i] = authorityCollect.get(i).getAuthority();
			}
	        return AuthorityUtils.createAuthorityList(authorityArray);
        }

		@Override
        public String getUsername() {
	        return getEmail();
        }

		@Override
        public String getPassword() {
	        return getUser().getPassword();
        }		
	}
	
}
