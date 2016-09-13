package mrs.domain.service.user;

import mrs.domain.model.User;
import mrs.domain.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " is not found.");
		}
		System.out.println(user);
		return new ReservationUserDetails(user);
	}
}
