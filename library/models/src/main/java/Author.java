import lombok.Data;
import org.jetbrains.annotations.NotNull;
@Data
public final class Author {

    @NotNull
    private String name;

    public Author(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    book writeBook(@NotNull String bookTitle) {
        return new book(this,bookTitle);
    }
}
