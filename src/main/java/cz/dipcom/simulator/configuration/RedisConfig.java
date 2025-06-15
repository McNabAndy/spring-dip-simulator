package cz.dipcom.simulator.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;



/**
 * RedisConfig is a configuration class that sets up Redis caching for the application.
 * It enables caching and configures both the RedisTemplate for interacting with Redis
 * and the RedisCacheManager to manage cache settings.
 */
@Configuration
@EnableCaching
public class RedisConfig {


    /**
     * Configures and provides a RedisTemplate to interact with Redis.
     * The RedisTemplate is used for serializing keys as strings and values as JSON objects.
     *
     * @param connectionFactory the RedisConnectionFactory used to create the Redis connection
     * @return a configured RedisTemplate instance
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }


    /**
     * Configures and provides a RedisCacheManager for managing Redis-based caching.
     * This manager uses a custom cache configuration with a time-to-live (TTL) of 3 minutes
     * and disables caching for null values. Values are serialized using JSON.
     *
     * @param connectionFactory the RedisConnectionFactory used to create the Redis connection
     * @return a configured RedisCacheManager instance
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(3))
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer())
                );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }


}
