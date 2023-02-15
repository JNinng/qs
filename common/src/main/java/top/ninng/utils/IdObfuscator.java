package top.ninng.utils;

import org.hashids.Hashids;

/**
 * id 混淆
 *
 * @Author OhmLaw
 * @Date 2022/12/31 15:18
 * @Version 1.0
 */
public class IdObfuscator {

    public String[] keys = new String[]{"my key"};
    public int[] length = new int[]{8};
    public Hashids[] hashids;

    public long[] decode(String t, int index) {
        if (index < keys.length) {
            return hashids[index].decode(t);
        }
        return null;
    }

    public String encode(int id, int index) {
        if (index < keys.length) {
            return hashids[index].encode(id);
        }
        return null;
    }

    public IdObfuscator init(String[] keys, int[] length) {
        this.keys = keys;
        this.length = length;
        this.hashids = new Hashids[keys.length];

        for (int i = 0; i < keys.length; i++) {
            hashids[i] = new Hashids(keys[i], length[i]);
        }

        return this;
    }
}
