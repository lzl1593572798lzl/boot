package schedule.cache.service;

/**
 * @author lzl
 *
 */
public interface StringCache {

    String get(String name);

    void saveCache(String name);
}
