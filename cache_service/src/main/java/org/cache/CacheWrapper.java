package org.cache;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Key based synchornization for this map
 */
public class CacheWrapper {

    HashMap<String, Integer> map;
    ConcurrentHashMap<String, ReentrantLock> lockMap;
    /**
     * Sets the key and value and the time duration
     * @param key
     * @param value
     * @return true if there is an unexpired key already exists
     */
    public boolean put(String key, int value) {
        if(!map.containsKey(key)){
            map.put(key, value);
            return true;
        }
        Lock lock = lockMap.computeIfAbsent(key, (k) -> new ReentrantLock());
        lock.lock();
        map.put(key, value);
        lock.unlock();
        return true;
    }

    /**
     * gets the value based on the key
     * @param key
     * @returng
     */
    public int get(String key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ReentrantLock lock = lockMap.computeIfAbsent(key, (k) -> new ReentrantLock());
        int result = 0;
        if(!lock.isLocked()){
            return map.get(key);
        }

        lock.lock();
        result = map.get(key);
        lock.unlock();
        return result;
    }
}
