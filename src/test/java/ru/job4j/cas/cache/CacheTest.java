package ru.job4j.cas.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CacheTest {

    @Test
    void whenAdded() {
        Cache cache = new Cache();
        Base user1 = new Base(1, 10);
        Base user2 = new Base(2, 11);
        assertThat(cache.add(user1)).isTrue();
        assertThat(cache.add(user2)).isTrue();
        assertThat(cache.getAll()).hasSize(2);
    }

    @Test
    void whenUpdated() {
        Cache cache = new Cache();
        Base user = new Base(1, 10);
        user.setName("User1");
        cache.add(user);
        assertThat(cache.update(user)).isTrue();
        assertThat(cache.getAll().get(user.getId()).getVersion()).isEqualTo(11);
    }

    @Test
    void whenDeleted() {
        Cache cache = new Cache();
        Base user = new Base(1, 10);
        user.setName("User1");
        cache.add(user);
        cache.delete(user);
        assertThat(cache.update(user)).isFalse();
    }
}