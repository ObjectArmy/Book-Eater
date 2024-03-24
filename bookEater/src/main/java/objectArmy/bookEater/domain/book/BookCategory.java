package objectArmy.bookEater.domain.book;

/**
 * @author Philip Athanasopoulos
 */
public class BookCategory {
    private String name;

    public BookCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
