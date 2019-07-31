import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data

final class book {
    @NotNull
    private String name;
    @NotNull
    private Author author;


    book (@NotNull Author author,@NotNull String name) {
        this.name = name;
        this.author = author;
    }
}
