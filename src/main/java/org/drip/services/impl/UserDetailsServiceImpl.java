package org.drip.services.impl;

import java.util.Collection;
import java.util.List;

import org.drip.model.Role;
import org.drip.model.User;
import org.drip.repository.UserRepository;
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
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new CustomUserDetails(user);
	}
	
	private final static class CustomUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 8385297473576148615L;

		private CustomUserDetails(User user) {
			super(user);
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
			List<Role> authorityCollect = getRoles();
			String[] authorityArray = new String[authorityCollect.size()];
			
			for (int i = 0; i < authorityArray.length; i++) {
				authorityArray[i] = authorityCollect.get(i).getAuthority();
			}
	        return AuthorityUtils.createAuthorityList(authorityArray);
        }		
	}
	
}
