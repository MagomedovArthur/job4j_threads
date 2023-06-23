package ru.job4j.cas.cache;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class CacheTest {

    @Test
    void test() {
        Cache cache = new Cache();
        Base user = new Base(5, 1);
        user.setName("User1");
        cache.add(user);
        assertThat(cache.update(user)).isTrue();
        assertThat(user.getVersion()).isEqualTo(2);
    }
}