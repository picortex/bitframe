import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record Teacher(@NotNull String name, @NotNull String email) {
    Teacher copy(@NotNull final String name) {
        return new Teacher(name, this.email);
    }
}
