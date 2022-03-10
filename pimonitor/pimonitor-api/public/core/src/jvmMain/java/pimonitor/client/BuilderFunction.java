package pimonitor.client;

public interface BuilderFunction<T> {
    void execute(T config);
}