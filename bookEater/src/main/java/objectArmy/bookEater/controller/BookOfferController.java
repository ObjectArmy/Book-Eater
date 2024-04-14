package objectArmy.bookEater.controller;

import objectArmy.bookEater.dao.BookOfferRepository;
import objectArmy.bookEater.entity.book.BookCategory;
import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BookOfferController {

    private static final Logger logger = LoggerFactory.getLogger(BookOfferController.class);

    @Autowired
    UserService userService;

    @Autowired
    BookOfferService bookOfferService;

    @Autowired
    BookService bookService;

    @Autowired
    BookCategoryService bookCategoryService;

    @Autowired
    AuthorService authorService;


    @GetMapping("/profileBookOffers")
    public String gotoUserBookOffers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        UserProfile loadedUser = userService.getUserById(user.getId());

        model.addAttribute("user", loadedUser);
        model.addAttribute("bookOffers", loadedUser.getBookOffers());

        return "bookOffer/profileBookOffers";
    }

    @GetMapping("/addBookOffer")
    public String gotoAddBookOffer(Model model) {
        model.addAttribute("bookOffer", new BookOffer());
        model.addAttribute("categories", bookCategoryService.getBookCategories());
        model.addAttribute("authors", authorService.getAuthors());
        return "bookOffer/addBookOfferForm";
    }

    @PostMapping("/addBookOffer")
    public String addBookOffer(Model model, @ModelAttribute BookOffer bookOffer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        UserProfile loadedUser = userService.getUserById(user.getId());

        userService.addBookOfferToUser(loadedUser, bookOffer);

        return "redirect:/profileBookOffers";
    }

}
