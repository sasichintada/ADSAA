import java.util.Scanner;

class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] C;
    int n;
    boolean leaf;

    public BTreeNode(int t, boolean leaf) {
        this.keys = new int[2 * t - 1];
        this.t = t;
        this.C = new BTreeNode[2 * t];
        this.n = 0;
        this.leaf = leaf;
    }

    void insertNonFull(int k) {
        int i = n - 1;
        if (leaf) {
            if (search(k) != null) {
                System.out.println("Key " + k + " already exists. No insertion.");
                return;
            }
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = k;
            n++;
        } else {
            while (i >= 0 && keys[i] > k) {
                i--;
            }
            if (C[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, C[i + 1]);
                if (keys[i + 1] < k) {
                    i++;
                }
            }
            C[i + 1].insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;
        System.arraycopy(y.keys, t, z.keys, 0, t - 1);
        if (!y.leaf) {
            System.arraycopy(y.C, t, z.C, 0, t);
        }
        y.n = t - 1;
        System.arraycopy(C, i + 1, C, i + 2, n - i);
        C[i + 1] = z;
        System.arraycopy(keys, i, keys, i + 1, n - i);
        keys[i] = y.keys[t - 1];
        n++;
    }

    BTreeNode search(int k) {
        int i = 0;
        while (i < n && k > keys[i]) {
            i++;
        }
        if (i < n && k == keys[i]) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return C[i].search(k);
    }

    void delete(int k) {
        int idx = 0;
        while (idx < n && keys[idx] < k) {
            idx++;
        }

        if (idx < n && keys[idx] == k) {
            if (leaf) {
                for (int i = idx; i < n - 1; i++) {
                    keys[i] = keys[i + 1];
                }
                n--;
                System.out.println("Key " + k + " deleted.");
            } else {
                if (C[idx].n >= t) {
                    keys[idx] = getPredecessor(idx);
                    C[idx].delete(keys[idx]);
                } else if (C[idx + 1].n >= t) {
                    keys[idx] = getSuccessor(idx);
                    C[idx + 1].delete(keys[idx]);
                } else {
                    merge(idx);
                    C[idx].delete(k);
                }
            }
        } else {
            if (leaf) {
                System.out.println("Key " + k + " not found.");
                return;
            }

            boolean flag = (idx == n);
            if (C[idx].n < t) {
                fill(idx);
            }
            if (flag && idx > n) {
                C[idx - 1].delete(k);
            } else {
                C[idx].delete(k);
            }
        }
    }

    int getPredecessor(int idx) {
        BTreeNode cur = C[idx];
        while (!cur.leaf) {
            cur = cur.C[cur.n];
        }
        return cur.keys[cur.n - 1];
    }

    int getSuccessor(int idx) {
        BTreeNode cur = C[idx + 1];
        while (!cur.leaf) {
            cur = cur.C[0];
        }
        return cur.keys[0];
    }

    void fill(int idx) {
        if (idx != 0 && C[idx - 1].n >= t) {
            borrowFromPrev(idx);
        } else if (idx != n && C[idx + 1].n >= t) {
            borrowFromNext(idx);
        } else {
            if (idx != n) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    void borrowFromPrev(int idx) {
        BTreeNode child = C[idx];
        BTreeNode sibling = C[idx - 1];
        for (int i = child.n - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
        }
        if (!child.leaf) {
            for (int i = child.n; i >= 0; i--) {
                child.C[i + 1] = child.C[i];
            }
        }
        child.keys[0] = keys[idx - 1];
        if (!child.leaf) {
            child.C[0] = sibling.C[sibling.n];
        }
        keys[idx - 1] = sibling.keys[sibling.n - 1];
        child.n++;
        sibling.n--;
    }

    void borrowFromNext(int idx) {
        BTreeNode child = C[idx];
        BTreeNode sibling = C[idx + 1];
        child.keys[child.n] = keys[idx];
        if (!child.leaf) {
            child.C[child.n + 1] = sibling.C[0];
        }
        keys[idx] = sibling.keys[0];
        for (int i = 1; i < sibling.n; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
        }
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; i++) {
                sibling.C[i - 1] = sibling.C[i];
            }
        }
        child.n++;
        sibling.n--;
    }

    void merge(int idx) {
        BTreeNode child = C[idx];
        BTreeNode sibling = C[idx + 1];
        child.keys[t - 1] = keys[idx];
        System.arraycopy(sibling.keys, 0, child.keys, t, sibling.n);
        if (!child.leaf) {
            System.arraycopy(sibling.C, 0, child.C, t, sibling.n + 1);
        }
        for (int i = idx + 1; i < n - 1; i++) {
            keys[i - 1] = keys[i];
        }
        for (int i = idx + 2; i <= n; i++) {
            C[i - 1] = C[i];
        }
        child.n += sibling.n + 1;
        n--;
        sibling = null;
    }
}

class BTree {
    BTreeNode root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    void insert(int k) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.C[0] = root;
                s.splitChild(0, root);
                int i = (s.keys[0] < k) ? 1 : 0;
                s.C[i].insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

    void delete(int k) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        root.delete(k);
        if (root.n == 0) {
            root = root.leaf ? null : root.C[0];
        }
    }

    BTreeNode search(int k) {
        return (root == null) ? null : root.search(k);
    }
}

class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BTree t = new BTree(5);
        System.out.println("How many keys to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter keys to insert: ");
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            t.insert(key);
        }
        System.out.println(n + " keys inserted");

        while (true) {
            System.out.println("\nB-Tree Operations");
            System.out.println("1. Search for a key");
            System.out.println("2. Delete a key");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to search: ");
                    int searchKey = sc.nextInt();
                    if (t.search(searchKey) != null) {
                        System.out.println("Key " + searchKey + " found in the tree.");
                    } else {
                        System.out.println("Key " + searchKey + " not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter key to delete: ");
                    int deleteKey = sc.nextInt();
                    t.delete(deleteKey);
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

