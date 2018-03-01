/**
 * Created by pooja.sharma on 31/10/17.
 */
@FunctionalInterface
public interface Criteria<E> {
    boolean test(E e);
}
