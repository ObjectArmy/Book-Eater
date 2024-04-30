package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.recommend.BookOfferRecommender;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.BookOfferService;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class HomepageController {

    @Autowired
    BookOfferService bookOfferService;

    @Autowired
    UserService userService;

    @Autowired
    BookOfferRecommender bookOfferRecommender;



    @GetMapping("/homepage")
    public String gotoHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("recommendedBookOffers", bookOfferRecommender.getRecommendedOffers(user));
        model.addAttribute("user", user);

        return "/homepage";
    }

    @GetMapping("/notifications")
    public String gotoNotificationsPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);
        return "profile/notifications";
    }



}
