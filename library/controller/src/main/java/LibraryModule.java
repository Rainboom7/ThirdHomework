import com.google.gson.Gson;
import com.google.inject.AbstractModule;

public final class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LibraryFactory.class).toInstance( new LibraryFactory() );
        bind(FileBooksFactoryCreator.class ).toInstance( new FileBooksFactoryCreator() );
        bind( Gson.class ).toInstance( new Gson() );


    }
}
