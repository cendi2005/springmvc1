package dd.util.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Component
public class JedisClientSingle implements JedisClient {
    @Resource
    private JedisPool jedisPool;

    private <T> T excute(JedisCallback<T, Jedis> jedisJedisCallback) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedisJedisCallback.doJedisCallbak(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    @Override
    public String get(String key) {
        return this.excute((jedis) -> {return jedis.get(key); });
    }

    @Override
    public String set(String key, String value) {
        return this.excute((jedis) -> { return jedis.set(key, value); });
    }

    @Override
    public String set(String key, String value, Integer seconds) {
        return this.excute((jedis) -> {return jedis.setex(key, seconds, value); });
    }

    @Override
    public String hget(String hkey, String key) {
        return this.excute((jedis) -> {return jedis.hget(hkey, key);});
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        return this.excute(new JedisCallback<Long, Jedis>() {
            @Override
            public Long doJedisCallbak(Jedis jedis) {
                return jedis.hset(hkey, key, value);
            }
        });
    }

    @Override
    public String setnx(String key, String value) {
        return this.excute(new JedisCallback<String, Jedis>() {
            @Override
            public String doJedisCallbak(Jedis jedis) {
                return jedis.setex(key,3600,value);
            }
        });
    }

    @Override
    public Long incr(String key) {
        return this.excute((jedis) -> {return jedis.incr(key); });
    }

    @Override
    public Long decr(String key) {
        return this.excute((jedis) -> {return jedis.decr(key); });
    }

    @Override
    public Long expire(String key, int second) {
        return this.excute((jedis) -> {return jedis.expire(key, second); });
    }

    @Override
    public Long ttl(String key) {
        return this.excute((jedis) -> {return jedis.ttl(key); });
    }

    @Override
    public Long del(String key) {
        return this.excute((jedis) -> {return del(key);});
    }

    @Override
    public Long hdel(String hkey, String key) {
        return this.excute((jedis) -> {return jedis.hdel(hkey, key);});
    }
}
