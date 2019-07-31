import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public final class Application {



    public static void main(@NotNull String[] args) throws Exception {
       final Injector injector= Guice.createInjector( new LibraryModule() );
        Scanner scanner = new Scanner( System.in );
        System.out.println("Enter file name" );
        String filename= scanner.next( );
        BooksFactory booksFactory = injector.getInstance(FileBooksFactoryCreator.class ).getBooksFactory( filename );
        System.out.println("Enter expected capacity" );
    Integer capacity=scanner.nextInt();
      Library library= injector.getInstance( LibraryFactory.class ).library(booksFactory,capacity );
        System.out.println(library.getBooksInCells().size());
        library.listAllBooks();





}

}

