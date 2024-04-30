package objectArmy.bookEater.entity.recommend;

import objectArmy.bookEater.entity.book.BookOffer;
import objectArmy.bookEater.entity.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Philip Athanasopoulos
 */
@Component
public class BookOfferRecommender {
    @Autowired
    BookOfferRecommendStrategy bookOfferRecommendStrategy;

    public BookOfferRecommender(BookOfferRecommendStrategy bookOfferRecommendStrategy) {
        this.bookOfferRecommendStrategy = bookOfferRecommendStrategy;
    }

    public List<BookOffer> getRecommendedOffers(UserProfile userProfile) {
        return bookOfferRecommendStrategy.getRecommendedOffers(userProfile);
    }
}
