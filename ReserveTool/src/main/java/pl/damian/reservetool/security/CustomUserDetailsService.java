package pl.damian.reservetool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import pl.damian.reservetool.model.entity.UserEntity;
import pl.damian.reservetool.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail)
			throws UsernameNotFoundException {
		// Let people login with either username or email
		UserEntity user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> 
						new UsernameNotFoundException("Użytkownik o podanym loginie albo emailu: " + usernameOrEmail + " nie istnieje.")
		);

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		UserEntity user = userRepository.findById(id).orElseThrow(
			() -> new UsernameNotFoundException("Nie znaleziono użytkownika z id: : " + id)
		);

		return UserPrincipal.create(user);
	}
}
