package onetech.onetech.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import onetech.onetech.entities.User;
import onetech.onetech.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface, UserDetailsService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	@Override
	public User add(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public User retrieveByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> retrieveAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(email);
//		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				authorities);
		return userRepository.findByEmail(email);
	}

}
