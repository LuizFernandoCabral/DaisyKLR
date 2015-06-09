package connector.db;

/**
 * Classe para poder capturar variáveis em lambda functions
 * @author ricardo
 *
 * @param <T>
 */
public class Holder<T> {
    private T value;

    public Holder(T value) {
        setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}