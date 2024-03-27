package objectArmy.bookEater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class UserController {

    @RequestMapping("/login")
    public String users(){
        return "login";
    }
    @RequestMapping("/")
    public String home(){
        return "login";
    }
}
