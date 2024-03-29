package mrs.user;

import java.io.Serializable;

import org.jilt.Builder;
import org.jilt.BuilderStyle;
import org.jilt.Opt;

@Builder(style = BuilderStyle.STAGED)
public record User(String userId, @Opt String password, @Opt RoleName roleName, @Opt String firstName,
		@Opt String lastName) implements Serializable {
}
