package org.modelio.module.javadesigner.custom;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DoubleMap<K, V> implements Map<K,V> {
    /**
     * Main delegated map.
     * Has a 1 -> 1 relationship between key and value.
     */
    private Map<K, V> delegated;

    /**
     * The reverse delegated map.
     * Has a 1 -> * relationship between the value and the key.
     */
    private Map<V, List<K>> reverseDelegated;

    /**
     * Creates a new instance with hashmaps.
     */
    public DoubleMap() {
        this.delegated = new HashMap<>();
        this.reverseDelegated = new HashMap<>();
    }

    @Override
    public void clear() {
        this.delegated.clear();
        this.reverseDelegated.clear();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.delegated.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        // Should be faster this way.
        return this.reverseDelegated.containsKey(value);
    }

    @Override
    public Set<java.util.Map.Entry<K,V>> entrySet() {
        return this.delegated.entrySet();
    }

    @Override
    public V get(final Object key) {
        return this.delegated.get(key);
    }

    @Override
    public boolean isEmpty() {
        return this.delegated.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return this.delegated.keySet();
    }

    @Override
    public V put(final K key, final V value) {
        V oldValue = this.delegated.put(key, value);
        if (oldValue != null) {
          List<K> keyList = this.reverseDelegated.get(oldValue);
          if (keyList != null) {
            keyList.remove(key);
          }
        }
        if (value != null) {
          List<K> keyList = this.reverseDelegated.get(value);
          if (keyList == null) {
            keyList = new LinkedList<>();
            this.reverseDelegated.put(value, keyList);
          }
          if (!keyList.contains(key)) {
            keyList.add(key);
          }
        }
        return oldValue;
    }

    @Override
    public void putAll(final Map<? extends K,? extends V> t) {
        for (K key : t.keySet()) {
          V value = t.get(key);
          put(key, value);
        }
    }

    @Override
    public V remove(final Object key) {
        V value = this.delegated.remove(key);
        if (value != null) {
          this.reverseDelegated.remove(value);
        }
        return value;
    }

    @Override
    public int size() {
        return this.delegated.size();
    }

    @Override
    public Collection<V> values() {
        return this.delegated.values();
    }

    /**
     * Gets all keys for a given value.
     * @param value The queried value.
     * @return A non-modifiable list of keys, maybe <code>null</code>.
     */
    public List<K> keysForValue(final V value) {
        List<K> result = this.reverseDelegated.get(value);
        if (result != null) {
          result = Collections.unmodifiableList(result);
        }
        return result;
    }

}
