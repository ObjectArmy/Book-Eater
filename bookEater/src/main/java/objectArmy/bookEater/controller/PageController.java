package objectArmy.bookEater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class PageController {



    @RequestMapping("/login")
    public String login() {
        return "login";
    }


}
