import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.Mockito;
import javax.naming.SizeLimitExceededException;
import java.util.Arrays;
import java.util.NoSuchElementException;
@SuppressWarnings("NullableProblems")
public final class LibraryFunctionsTest {
    @NotNull
    private static final Author AUTHOR = new Author( "Tom" );
    @NotNull
    private static final book BOOK_3 = new book( AUTHOR, "book3" );
    @NotNull
    private static final book BOOK_2 = new book( AUTHOR, "book2" );
    @NotNull
    private static final book BOOK_1 = new book( AUTHOR, "book1" );

    @NotNull
    private  Library library;
    @NotNull
    private
    final Injector injector = Guice.createInjector( new LibraryModule() );
    @NotNull
    private BooksFactory booksFactory = Mockito.mock( BooksFactory.class );

    @Before
    public void setUpTest() throws Exception {
        injector.injectMembers( this );
        Mockito.when( booksFactory.books( ) ).thenReturn( Arrays.asList( BOOK_1,
                BOOK_2,
                BOOK_3 ) );
       library = injector.getInstance( LibraryFactory.class ).library( booksFactory, 4 );
    }
    @NotNull
    @Rule
    public final SystemOutRule systemOutRule=new SystemOutRule().enableLog();

    @Test(expected = SizeLimitExceededException.class)
    public void applicationThrowsExceptionWhenNotEnoughCapacity() throws Exception {
        library = injector.getInstance( LibraryFactory.class ).library( booksFactory, 1);

    }

    @Test
    public void librarySetsAllBooksInOrder()  {
        Assert.assertEquals( library.getBooksInCells( ).get( 0 ), BOOK_1 );
        Assert.assertEquals( library.getBooksInCells( ).get( 1 ), BOOK_2 );
        Assert.assertEquals( library.getBooksInCells( ).get( 2 ), BOOK_3 );
        Assert.assertEquals( library.getIsCellTaken( ).get( 3 ), false );
    }


    @Test
    public void recieveInfoWhenBookTaken()  {
       String expectedOutput = library.getBook( 0 ) + "\r\n";
        library.takeBook( 0 );
        Assert.assertEquals( systemOutRule.getLog(), expectedOutput );
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenTryingToTakeFromEmptyCell()  {
        library.takeBook( 1 );
        library.takeBook( 1 );

    }
    @Test
    public void returnsBookThatWasInCell()  {
        Assert.assertEquals( library.getBooksInCells().get( 2 ),BOOK_3 );
        Assert.assertEquals( library.getBooksInCells().get( 0 ),BOOK_1 );
        Assert.assertEquals( library.getBooksInCells().get( 1 ),BOOK_2 );

    }
    @Test
    public void bookIsPlacedInFirstEmptyCell() throws Exception {
        Assert.assertTrue( library.getIsCellTaken().get( 0 ) );
        library.takeBook(0);
        Assert.assertFalse(  library.getIsCellTaken().get( 0 ) );
        library.addBook( BOOK_3);
        Assert.assertTrue(  library.getIsCellTaken().get( 0 ) );

    }
    @Test(expected =NoSuchFieldException.class)
    public void exceptionIsThrownWhenNoPlaceForANewBook() throws NoSuchFieldException {
        library.addBook( BOOK_3 );
        library.addBook( BOOK_2 );
    }

    @Test
    public void bookInfoIsGivenByListAllBooksMethod(){
        systemOutRule.clearLog();
        library.listAllBooks();
        Gson gson=new Gson();
        Assert.assertEquals( systemOutRule.getLog(),"List of all books in library\r\n"+
                        gson.toJson(BOOK_1)+"\r\n"+
                        gson.toJson(BOOK_2)+"\r\n"+
                        gson.toJson(BOOK_3)+"\r\n"
                );
    }






    }

