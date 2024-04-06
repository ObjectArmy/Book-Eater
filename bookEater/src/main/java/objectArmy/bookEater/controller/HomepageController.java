package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class HomepageController {
    @GetMapping("/homepage")
    public String gotoHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "/homepage";
    }
}
