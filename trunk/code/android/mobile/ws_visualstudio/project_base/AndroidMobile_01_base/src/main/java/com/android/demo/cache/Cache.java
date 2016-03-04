package com.android.demo.cache;

import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 */
public class Cache {
    private static SparseArray<ArrayList<?>> mCache = new SparseArray<ArrayList<?>>();

    private static Cache sInstance;

    private Cache() {

    }

    public static Cache getInstance() {
        if (null == sInstance) {
            synchronized (Cache.class) {
                if (null == sInstance) {
                    sInstance = new Cache();
                }
            }
        }
        return sInstance;
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addLast(int cacheType, ArrayList data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        ArrayList cache = mCache.get(cacheType);
        if (cache != null && !cache.isEmpty()) {
            cache.addAll(data);
            mCache.remove(cacheType);
            mCache.put(cacheType, cache);
        } else {
            mCache.put(cacheType, data);
        }
    }

    public void addHead(int cacheType, ArrayList data) {
        if (data == null || data.isEmpty())
            return;
        ArrayList cache = mCache.get(cacheType);
        if (cache != null && !cache.isEmpty()) {
            cache.addAll(0, data);
            mCache.remove(cacheType);
            mCache.put(cacheType, cache);
        } else {
            mCache.put(cacheType, data);
        }
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    public void refreshCache(int cacheType, ArrayList data) {
        ArrayList cache = mCache.get(cacheType);
        if (cache != null && !cache.isEmpty()) {
            mCache.remove(cacheType);
            mCache.put(cacheType, data);
        } else {
            mCache.put(cacheType, data);
        }
    }

    public int getCacheCount(int cacheType) {
        return getCache(cacheType) == null ? 0 : getCache(cacheType).size();
    }

    public ArrayList<?> getCache(int cacheType) {
        return mCache.get(cacheType);
    }

    public void clear(int cacheType) {
        if (mCache.get(cacheType) != null)
            mCache.get(cacheType).clear();
    }

    public void remove(int cacheType) {
        mCache.remove(cacheType);
    }


    public void clearAll() {
        if (mCache != null && mCache.size() > 0) {
            for (int i = 0; i < mCache.size(); i++) {
                if (mCache.get(mCache.keyAt(i)) != null)
                    mCache.get(mCache.keyAt(i)).clear();
            }
        }
        mCache.clear();
    }

}
