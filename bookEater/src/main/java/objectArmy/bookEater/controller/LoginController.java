package objectArmy.bookEater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String gotoLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String accessPage() {
        return "login";
    }
}
