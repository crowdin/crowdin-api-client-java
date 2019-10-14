package crowdin.api.client.core.http;

public interface JsonTransformer {

    <T> T parse(String json, Class<T> clazz);

    <T> String convert(T obj);
}
