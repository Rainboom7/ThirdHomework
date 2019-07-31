
import org.jetbrains.annotations.NotNull;

final class LibraryFactory {


    @NotNull
    Library library(@NotNull BooksFactory booksFactory, @NotNull Integer capacity) throws Exception {
        return new Library(booksFactory,capacity );
    }

}
