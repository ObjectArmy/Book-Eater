package objectArmy.bookEater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String gotoLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "redirect:/";
    }

}
