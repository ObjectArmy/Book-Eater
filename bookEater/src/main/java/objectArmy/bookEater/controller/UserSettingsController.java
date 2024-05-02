package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.BookCategoryService;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class UserSettingsController {

    UserService userService;
    BookCategoryService bookCategoryService;

    @Autowired
    public UserSettingsController(UserService userService, BookCategoryService bookCategoryService) {
        this.userService = userService;
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping("/settings")
    public String gotoUserSettings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("bookCategories", bookCategoryService.getBookCategories());
        model.addAttribute("user", user);
        return "profile/editProfileSettings";
    }

    @PostMapping("/settings")
    public String updateUserProfile(@ModelAttribute("user") UserProfile userProfile, Model model, @RequestParam("categories_values") String bookCategories) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

        String[] categories = bookCategories.split(",");
        System.out.println("Categories: " + Arrays.toString(categories));
        List<BookCategory> favoriteCategories = new ArrayList<>();
        for (String category : categories) {
            favoriteCategories.add(bookCategoryService.findBookCategoryByName(category));
        }
        System.out.println("Favorite categories: " + favoriteCategories);
        userProfile.setFavoriteCategories(favoriteCategories);

        userService.updateUser(userProfile, user.getId());
        return "redirect:/settings";
    }
}
