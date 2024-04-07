package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.BookCategoryService;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class UserSettingsController {

    @Autowired
    UserService userService;
    @Autowired
    BookCategoryService bookCategoryService;

    @GetMapping("/settings")
    public String gotoUserSettings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        model.addAttribute("bookCategories", this.bookCategoryService.getBookCategories());
        model.addAttribute("user", user);
        return "profile/editProfileSettings";
    }

    @PostMapping("/settings")
    public String updateUserProfile(@ModelAttribute("user") UserProfile userProfile, Model model) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long usersId = user.getId();
        UserProfile updatedUser = userService.updateUser(userProfile,usersId);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(updatedUser, null, updatedUser.getAuthorities());

        // Set the new Authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/settings";
    }
}
