/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.mindorks.java.graph.utils;

import java.util.Arrays;

public class QuickUnionUF implements UF {

    // stores the parent id of each node
    private int[] id;
    // stores the size of the component
    private int[] sz;

    public QuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; //path compression: grandparent to be stored as the parent.
            i = id[i];
        }
        return i;
    }

    /**
     * attach p's root to q's root
     */
    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return; // already in the same component
        if (sz[i] < sz[j]) {
            id[i] = j; //merging smaller tree to a larger tree
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public int find(int p) {
        return root(p);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public String toString() {
        return "QuickUnionUF{" +
                "id=" + Arrays.toString(id) +
                ", sz=" + Arrays.toString(sz) +
                '}';
    }
}
