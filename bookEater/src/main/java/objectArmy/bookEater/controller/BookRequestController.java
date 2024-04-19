package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class BookRequestController {

    @Autowired
    UserService userService;

    @GetMapping("/outgoingBookRequests")
    public String getOutgoingBookRequests(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

        model.addAttribute("outgoingRequests", user.getOutgoingBookRequests());

        return "profile/outgoingBookRequests";
    }

    @GetMapping("/incomingBookRequests")
    public String getIncomingBookRequests(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());

//        model.addAttribute("incomingRequests", user.getIncomingBookRequests());

        return "profile/incomingBookRequests";
    }



}
