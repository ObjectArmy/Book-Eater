package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String gotoLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String goToRegisterForm(Model model) {
        model.addAttribute("user", new UserProfile());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserProfile user, Model model) {
        userService.saveUser(user);
        return "redirect:/login";
    }


}
