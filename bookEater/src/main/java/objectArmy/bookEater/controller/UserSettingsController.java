package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class UserSettingsController {

    @Autowired
    UserService userService;
    @GetMapping("/{id}/settings")
    public String gotoUserSettings(Model model , @PathVariable long id) {
        UserProfile user = userService.getUserById(id);
        model.addAttribute("user",user);
        return  "profile/editProfileSettings";
    }
}
