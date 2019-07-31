import org.jetbrains.annotations.NotNull;

final class FileBooksFactoryCreator {
    @NotNull
    BooksFactory getBooksFactory(@NotNull String fileName){
        return new FileBookFactory( fileName );
    }
}
