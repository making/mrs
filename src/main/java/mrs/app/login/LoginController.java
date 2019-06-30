package mrs.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("loginForm")
	public String loginForm() {
		return "login/loginForm";
	}
}
