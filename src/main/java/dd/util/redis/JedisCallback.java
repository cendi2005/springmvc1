package dd.util.redis;

public interface JedisCallback<T, E> {
    T doJedisCallbak(E e);
}
