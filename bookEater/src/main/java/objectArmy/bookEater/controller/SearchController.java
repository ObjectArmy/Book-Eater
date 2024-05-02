package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.search.AuthorSearchStrategy;
import objectArmy.bookEater.entity.search.SearchStrategyFactory;
import objectArmy.bookEater.entity.search.Searcher;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */

@Controller
public class SearchController {

    @Autowired
    AuthorSearchStrategy authorSearcher;
    @Autowired
    UserService userService;
    @Autowired
    private Searcher searcher;
    @Autowired
    private SearchStrategyFactory searchStrategyFactory;

    @GetMapping("/search")
    public String searchForBooks(Model model, @RequestParam("userQuery") String userQuery, @RequestParam("searchType") String searchType, @RequestParam("searchAccuracy") String searchAccuracy

    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);

        List<BookOffer> results;
        searcher.setSearchStrategy(searchStrategyFactory.getSearchStrategy(searchType));
        if (searchAccuracy.equals("exact")) results = searcher.searchExact(userQuery);
        else if (searchAccuracy.equals("approximate")) results = searcher.searchApproximately(userQuery);
        else results = null;

        model.addAttribute("searchResults", results);


        return "search/searchResults";
    }
}