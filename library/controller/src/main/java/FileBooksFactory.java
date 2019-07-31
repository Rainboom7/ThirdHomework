import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("UnstableApiUsage")
final class FileBookFactory implements BooksFactory {
    @NotNull
    private static final Type listBooksType = new TypeToken<ArrayList<book>>() {
    }.getType();
    @NotNull
    private final String fileName;

    FileBookFactory(@NotNull String fileName) {
        this.fileName = fileName;
    }

    @NotNull
    @Override
    public Collection<book> books() {
        try {
            return new Gson().fromJson(new BufferedReader(new FileReader(fileName)), listBooksType);
        } catch ( FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }}