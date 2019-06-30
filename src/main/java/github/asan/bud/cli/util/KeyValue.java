package github.asan.bud.cli.util;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/30 12:20 PM
 * @history: 1.2019/6/30 created by jianfeng.zheng
 */
public class KeyValue<K, V> {
    private K k;
    private V v;

    public KeyValue() {

    }

    public KeyValue(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public void key(K k) {
        this.k = k;
    }

    public void value(V v) {
        this.v = v;
    }

    public K key() {
        return this.k;
    }

    public V value() {
        return this.v;
    }
}
