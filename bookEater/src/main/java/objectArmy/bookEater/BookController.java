package objectArmy.bookEater;

import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Philip Athanasopoulos
 */
@Controller
public class BookController {

    @RequestMapping("/users")
    public String users(){
        return "users";
    }

}
