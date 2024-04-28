package objectArmy.bookEater.entity.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Philip Athanasopoulos
 */
@Component
public class SearchStrategyFactory {

    @Autowired
    private TitleSearchStrategy titleSearchStrategy;
    @Autowired
    private AuthorSearchStrategy authorSearchStrategy;

    public SearchStrategy getSearchStrategy(String searchType) {
        if (searchType.equals("title")) return titleSearchStrategy;
        else if (searchType.equals("author")) return authorSearchStrategy;
        else {
            System.out.println("Invalid search type");
            return null;
        }
    }
}
