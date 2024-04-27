package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.search.TitleSearcher;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.BookOfferService;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
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



    @GetMapping("/homepage")
    public String gotoHomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);
        UserProfile finalUser = user;
        model.addAttribute("bookOffers", bookOfferService.getAllBookOffers().stream()
                .filter(bookOffer -> !bookOffer.getOfferor().getId().equals(finalUser.getId()))
                .collect(Collectors.toList()));
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
