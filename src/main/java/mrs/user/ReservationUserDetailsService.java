package mrs.user;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ReservationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
	@NewSpan
    public UserDetails loadUserByUsername(@SpanTag String username)
        throws UsernameNotFoundException {
        User user = this.userRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException(username + " is not found."));
        return new ReservationUserDetails(user);
    }
}
