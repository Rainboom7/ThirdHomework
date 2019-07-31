import org.jetbrains.annotations.NotNull;

import java.util.Collection;

interface BooksFactory {
    @NotNull
    Collection<book> books();
}
