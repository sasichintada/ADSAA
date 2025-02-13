import java.util.Scanner;
import java.util.Random;

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
        for (int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.C[j] = y.C[j + t];
            }
        }
        y.n = t - 1;
        for (int j = n; j > i; j--) {
            C[j + 1] = C[j];
        }
        C[i + 1] = z;
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[t - 1];
        n++;
    }

    void traverse() {
        for (int i = 0; i < n; i++) {
            if (!leaf) {
                C[i].traverse();
            }
            System.out.print(keys[i] + " ");
        }
        if (!leaf) {
            C[n].traverse();
        }
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
        System.out.println("Delete function not implemented in this version.");
    }
}

class BTree {
    BTreeNode root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    BTreeNode search(int k) {
        return root == null ? null : root.search(k);
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
                int i = 0;
                if (s.keys[0] < k) {
                    i++;
                }
                s.C[i].insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

    void delete(int k) {
        root.delete(k);
    }

    void insertRandomElements(int count) {
        Random rand = new Random();
        int[] uniqueKeys = new int[count];
        int keyCount = 0;

        while (keyCount < count) {
            int randomKey = rand.nextInt(1000);
            
            boolean exists = false;
            for (int i = 0; i < keyCount; i++) {
                if (uniqueKeys[i] == randomKey) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                uniqueKeys[keyCount] = randomKey;
                insert(randomKey);
                keyCount++;
            }
        }

        System.out.println("Inserted 100 random unique keys: ");
        for (int i = 0; i < uniqueKeys.length; i++) {
            System.out.print(uniqueKeys[i] + " ");
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BTree t = new BTree(5); 
        System.out.println("Inserting 100 random unique keys...");
        t.insertRandomElements(100);
        System.out.println("100 random keys inserted.\n");

        while (true) {
            System.out.println("\nB-Tree Operations");
            System.out.println("1. Search for a key");
            System.out.println("2. Traverse the tree");
            System.out.println("3. Delete (not implemented)");
            System.out.println("4. Exit");
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
                    System.out.println("Traversal of the tree: ");
                    t.traverse();
                    System.out.println(); 
                    break;

                case 3:
                    System.out.print("Enter key to delete: ");
                    int deleteKey = sc.nextInt();
                    t.delete(deleteKey);
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

