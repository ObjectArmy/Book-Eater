package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.search.TitleSearcher;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Controller
public class SearchController {

    @Autowired
    TitleSearcher searcher;

    @GetMapping("/search")
    public String searchForBooks(Model model, @RequestParam("userQuery") String userQuery) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);


        System.out.println("User query: " + userQuery);
        model.addAttribute("searchResults",null);
        List<BookOffer> results;
        if (userQuery != null) results = searcher.searchApproximately(userQuery);
        else results = new ArrayList<>();
        System.out.println("Search results: " + results);
        model.addAttribute("searchResults", results);
        return "search/searchResults";
    }
}
