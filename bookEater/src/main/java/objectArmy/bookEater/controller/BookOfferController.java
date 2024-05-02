package objectArmy.bookEater.controller;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import objectArmy.bookEater.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Philip Athanasopoulos
 */

@Controller
public class BookOfferController {
    UserService userService;
    BookOfferService bookOfferService;
    BookService bookService;
    BookCategoryService bookCategoryService;
    AuthorService authorService;
    BookRequestService bookRequestService;

    @Autowired
    public BookOfferController(UserService userService, BookOfferService bookOfferService, BookService bookService, BookCategoryService bookCategoryService, AuthorService authorService, BookRequestService bookRequestService) {
        this.userService = userService;
        this.bookOfferService = bookOfferService;
        this.bookService = bookService;
        this.bookCategoryService = bookCategoryService;
        this.authorService = authorService;
        this.bookRequestService = bookRequestService;
    }


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
    public String addBookOffer(@ModelAttribute BookOffer bookOffer, @RequestParam("authors") String authorNames, @RequestParam("categories") String categoryNames) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = (UserProfile) authentication.getPrincipal();
        UserProfile loadedUser = userService.getUserById(user.getId());

        for (String authorName : authorNames.split(","))
            bookOffer.getOfferedBook().addAuthor(authorService.getAuthorOrElseCreate(authorName));

        for (String categoryName : categoryNames.split(","))
            bookOffer.getOfferedBook().addCategory(bookCategoryService.getBookCategoryOrElseCreate(categoryName));

        userService.addBookOfferToUser(loadedUser, bookOffer);

        return "redirect:/profileBookOffers";
    }

    @PostMapping("/deleteBookOffer")
    public String deleteBookOffer(@RequestParam("id") Long id) {
        bookOfferService.deleteBookOfferById(id);
        return "redirect:/profileBookOffers";
    }

    @PostMapping("/requestBook/{userId}/{bookOfferId}")
    public String requestBook(@PathVariable String userId, @PathVariable String bookOfferId) {
        bookOfferService.addBookRequest(Long.valueOf(userId), Long.valueOf(bookOfferId));
        return "redirect:/homepage";
    }

}
