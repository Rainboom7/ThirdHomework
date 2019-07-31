import com.google.gson.Gson;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import javax.naming.SizeLimitExceededException;
import java.util.Collection;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
@Data
final class Library {
    @NotNull
    Gson gson = new Gson();
    @NotNull
    private HashMap<Integer,book>booksInCells;
    @NotNull
    private HashMap<Integer,Boolean>isCellTaken;

    @Override
    public boolean equals(@NotNull Object o) {
        if ( this == o ) return true;
        if ( getClass( ) != o.getClass( ) ) return false;
        Library library = (Library) o;
        return gson.equals( library.gson ) &&
                booksInCells.equals( library.booksInCells ) &&
                isCellTaken.equals( library.isCellTaken );
    }

    @Override
    public int hashCode() {
        return Objects.hash( gson, booksInCells, isCellTaken );
    }

    Library(@NotNull BooksFactory booksFactory, @NotNull Integer capacity ) throws Exception {
        Collection<book> books= booksFactory.books();
        HashMap<Integer,book>booksInCells=new HashMap<>( );
        HashMap<Integer,Boolean>isCellTaken=new HashMap<>(  );
        if(capacity<books.size()){
            throw new SizeLimitExceededException(  );
        }
        int cellNumber =0;
        for (book book:books){
            booksInCells.put(cellNumber,book );
            isCellTaken.put(cellNumber,true);
            cellNumber++;
        }
        for (;cellNumber<capacity;cellNumber++) {
            isCellTaken.put(cellNumber,false);
        }
        this.booksInCells=booksInCells;
        this.isCellTaken=isCellTaken;
    }

    void takeBook(@NotNull Integer cellNumber){
        if(isCellTaken.get( cellNumber )){
            System.out.println( getBook( cellNumber ) );
            isCellTaken.put( cellNumber,false );
            booksInCells.put( cellNumber,null );
        }
        else throw new NoSuchElementException(  );

    }

    @NotNull
     String getBook(@NotNull Integer cellNumber) {
        return "Book: "+ gson.toJson(booksInCells.get( cellNumber ))+ " from cell number: "+cellNumber;
    }

    void addBook(@NotNull book book) throws NoSuchFieldException {
        Integer cellNumber=0;
       while (isCellTaken.get( cellNumber ) ){
           cellNumber++;
           if(cellNumber==isCellTaken.size())
               throw new NoSuchFieldException(  );
       }
       booksInCells.put( cellNumber,book );
       isCellTaken.put( cellNumber,true );
    }
    void listAllBooks(){
        System.out.println("List of all books in library" );
        for(int cellNumber=0;cellNumber<booksInCells.size();cellNumber++){
            if(isCellTaken.get( cellNumber ))
                System.out.println(gson.toJson(booksInCells.get(cellNumber)) );
        }

    }


}
