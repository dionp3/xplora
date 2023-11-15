package org.gnulag.xplora.models;

import java.util.ArrayList;
import java.util.List;
import org.gnulag.xplora.utils.GimmickAction;

class Node<K, V> {
  public K key;
  public V value;
  public Node<K, V> parent;
  public Node<K, V> left;
  public Node<K, V> right;
  public boolean isRed;
  public GimmickAction<K> gimmick;
}

public class RedBlackTreeMap<K extends Comparable<K>, V extends Comparable<V>> {
  private Node<K, V> root;
  private Node<K, V> TNULL;

  public RedBlackTreeMap() {
    TNULL = new Node<K, V>();
    TNULL.isRed = false;
    TNULL.left = null;
    TNULL.right = null;
    root = TNULL;
  }

  // public Node<K, V> getRoot() {
  //   return this.root;
  // }

  // public K getKeyByValue(V value) {
  //   return getKeyByValueHelper(root, value);
  // }

  // public V getValueByKey(K key) {
  //   Node<K, V> node = searchTreeHelper(this.root, key);
  //   if (node != TNULL) {
  //     return node.value;
  //   }
  //   return null;
  // }

  public void printTree() {
    printTreeHelper(root, 0);
  }

  public void preorder() {
    preOrderHelper(this.root);
  }

  public void inorder() {
    inOrderHelper(this.root);
  }

  public void postorder() {
    postOrderHelper(this.root);
  }

  // public Node<K, V> searchTree(K k) {
  //   Node<K, V> node = searchTreeHelper(this.root, k);
  //   return node;
  // }

  public void insert(K key, V value) {
    Node<K, V> node = new Node<K, V>();
    node.parent = null;
    node.key = key;
    node.value = value;
    node.left = TNULL;
    node.right = TNULL;
    node.isRed = true;
    node.gimmick = null;

    Node<K, V> y = null;
    Node<K, V> x = this.root;

    while (x != TNULL) {
      y = x;
      if (node.key.compareTo(x.key) < 0) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    node.parent = y;
    if (y == null) {
      root = node;
    } else if (node.key.compareTo(y.key) < 0) {
      y.left = node;
    } else {
      y.right = node;
    }

    if (node.parent == null) {
      node.isRed = false;
      return;
    }

    if (node.parent.parent == null) {
      return;
    }

    fixInsert(node);
  }

  public void insert(K key, V value, GimmickAction<K> gimmick) {
    Node<K, V> node = new Node<K, V>();
    node.parent = null;
    node.key = key;
    node.value = value;
    node.left = TNULL;
    node.right = TNULL;
    node.isRed = true;
    node.gimmick = gimmick;

    Node<K, V> y = null;
    Node<K, V> x = this.root;

    while (x != TNULL) {
      y = x;
      if (node.key.compareTo(x.key) < 0) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    node.parent = y;
    if (y == null) {
      root = node;
    } else if (node.key.compareTo(y.key) < 0) {
      y.left = node;
    } else {
      y.right = node;
    }
  }

  public Node<K, V> minimum(Node<K, V> node) {
    while (node.left != TNULL) {
      node = node.left;
    }
    return node;
  }

  public Node<K, V> maximum(Node<K, V> node) {
    while (node.right != TNULL) {
      node = node.right;
    }
    return node;
  }

  public Node<K, V> successor(Node<K, V> x) {
    if (x.right != TNULL) {
      return minimum(x.right);
    }

    Node<K, V> y = x.parent;
    while (y != TNULL && x == y.right) {
      x = y;
      y = y.parent;
    }
    return y;
  }

  public Node<K, V> predecessor(Node<K, V> x) {
    if (x.left != TNULL) {
      return maximum(x.left);
    }

    Node<K, V> y = x.parent;
    while (y != TNULL && x == y.left) {
      x = y;
      y = y.parent;
    }

    return y;
  }

  public String searchKeyAndValueByKey(String targetKey) {
    Node<K, V> node = searchKeyAndValueByKeyHelper(root, targetKey);
    if (node != TNULL) {
      if (node.gimmick != null) {
        node.value = (V) node.gimmick.gimmick(node.key);
        return node.value.toString();
      }
      return node.value.toString();
    }
    return null;
  }

  public ArrayList<String> searchKeysAndValuesByContainingKey(String targetKeySubstring) {
    ArrayList<String> matchingKeyValues = new ArrayList<>();
    searchKeysAndValuesByContainingKeyHelper(root, targetKeySubstring, matchingKeyValues);
    return matchingKeyValues;
  }

  public ArrayList<String> searchKeysAndValuesByContainingValue(String targetValueSubstring) {
    ArrayList<String> matchingKeyValues = new ArrayList<>();
    searchKeysAndValuesByContainingValueHelper(root, targetValueSubstring, matchingKeyValues);
    return matchingKeyValues;
  }

  private void leftRotate(Node<K, V> x) {
    Node<K, V> y = x.right;
    x.right = y.left;
    if (y.left != TNULL) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      this.root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  private void rightRotate(Node<K, V> x) {
    Node<K, V> y = x.left;
    x.left = y.right;
    if (y.right != TNULL) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      this.root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  // Balance the node after insertion
  private void fixInsert(Node<K, V> k) {
    Node<K, V> u;
    while (k.parent.isRed == true) {
      if (k.parent == k.parent.parent.right) {
        u = k.parent.parent.left;
        if (u.isRed == true) {
          u.isRed = false;
          k.parent.isRed = false;
          k.parent.parent.isRed = true;
          k = k.parent.parent;
        } else {
          if (k == k.parent.left) {
            k = k.parent;
            rightRotate(k);
          }
          k.parent.isRed = false;
          k.parent.parent.isRed = true;
          leftRotate(k.parent.parent);
        }
      } else {
        u = k.parent.parent.right;

        if (u.isRed == true) {
          u.isRed = false;
          k.parent.isRed = false;
          k.parent.parent.isRed = true;
          k = k.parent.parent;
        } else {
          if (k == k.parent.right) {
            k = k.parent;
            leftRotate(k);
          }
          k.parent.isRed = false;
          k.parent.parent.isRed = true;
          rightRotate(k.parent.parent);
        }
      }
      if (k == root) {
        break;
      }
    }
    root.isRed = false;
  }

  // Pre order
  private void preOrderHelper(Node<K, V> node) {
    if (node != TNULL) {
      System.out.print(node.key + " ");
      preOrderHelper(node.left);
      preOrderHelper(node.right);
    }
  }

  // In order
  private void inOrderHelper(Node<K, V> node) {
    if (node != TNULL) {
      inOrderHelper(node.left);
      System.out.print(node.key + " ");
      inOrderHelper(node.right);
    }
  }

  // Post order
  private void postOrderHelper(Node<K, V> node) {
    if (node != TNULL) {
      postOrderHelper(node.left);
      postOrderHelper(node.right);
      System.out.print(node.key + " ");
    }
  }

  // private K getKeyByValueHelper(Node<K, V> node, V targetValue) {
  //   if (node == TNULL) {
  //     return null;
  //   }

  //   if (node.value.equals(targetValue)) {
  //     return node.key;
  //   }

  //   K leftResult = getKeyByValueHelper(node.left, targetValue);
  //   if (leftResult != null) {
  //     return leftResult;
  //   }

  //   K rightResult = getKeyByValueHelper(node.right, targetValue);
  //   return rightResult;
  // }

  private Node<K, V> searchKeyAndValueByKeyHelper(Node<K, V> node, String targetKey) {
    if (node == TNULL) {
      return TNULL;
    }

    if (node.key.equals(targetKey)) {
      return node;
    }

    Node<K, V> leftResult = searchKeyAndValueByKeyHelper(node.left, targetKey);
    if (leftResult != TNULL) {
      return leftResult;
    }

    Node<K, V> rightResult = searchKeyAndValueByKeyHelper(node.right, targetKey);
    return rightResult;
  }

  private void searchKeysAndValuesByContainingKeyHelper(
      Node<K, V> node, String targetKeySubstring, List<String> matchingKeyValues) {
    if (node == TNULL) {
      return;
    }

    if (node.key.toString().contains(targetKeySubstring) && node.gimmick == null) {
      String keyValue = node.key + "\n" + node.value;
      matchingKeyValues.add(keyValue);
    }

    searchKeysAndValuesByContainingKeyHelper(node.left, targetKeySubstring, matchingKeyValues);
    searchKeysAndValuesByContainingKeyHelper(node.right, targetKeySubstring, matchingKeyValues);
  }

  private void searchKeysAndValuesByContainingValueHelper(
      Node<K, V> node, String targetValueSubstring, List<String> matchingKeyValues) {
    if (node == TNULL) {
      return;
    }

    if (node.value != null
        && node.value.toString().contains(targetValueSubstring)
        && node.gimmick == null) {
      String keyValue = node.key + "\n" + node.value;
      matchingKeyValues.add(keyValue);
    }

    searchKeysAndValuesByContainingValueHelper(node.left, targetValueSubstring, matchingKeyValues);
    searchKeysAndValuesByContainingValueHelper(node.right, targetValueSubstring, matchingKeyValues);
  }

  // Search the tree
  // private Node<K, V> searchTreeHelper(Node<K, V> node, K key) {
  //   if (node == TNULL || key.equals(node.key)) {
  //     return node;
  //   }

  //   if (key.compareTo(node.key) < 0) {
  //     return searchTreeHelper(node.left, key);
  //   }
  //   return searchTreeHelper(node.right, key);
  // }

  private void printTreeHelper(Node<K, V> root, int space) {
    if (root != TNULL) {
      space += 10;
      printTreeHelper(root.right, space);

      System.out.println();
      for (int i = 10; i < space; i++) {
        System.out.print(" ");
      }

      // Set text color based on the node's color
      if (root.isRed == true) {
        System.out.print("\u001B[31m"); // Set text color to red for RED nodes
      }

      System.out.print(root.key);

      // Reset text color after printing the value
      System.out.print("\u001B[0m");

      printTreeHelper(root.left, space);
    }
  }

  // public void deleteNode(K key) {
  //   deleteNodeHelper(this.root, key);
  // }

  // // Balance the tree after deletion of a node
  // private void fixDelete(Node<K, V> x) {
  //   Node<K, V> s;
  //   while (x != root && x.isRed == false) {
  //     if (x == x.parent.left) {
  //       s = x.parent.right;
  //       if (s.isRed == true) {
  //         s.isRed = false;
  //         x.parent.isRed = true;
  //         leftRotate(x.parent);
  //         s = x.parent.right;
  //       }

  //       if (s.left.isRed == false && s.right.isRed == false) {
  //         s.isRed = true;
  //         x = x.parent;
  //       } else {
  //         if (s.right.isRed == false) {
  //           s.left.isRed = false;
  //           s.isRed = true;
  //           rightRotate(s);
  //           s = x.parent.right;
  //         }

  //         s.isRed = x.parent.isRed;
  //         x.parent.isRed = false;
  //         s.right.isRed = false;
  //         leftRotate(x.parent);
  //         x = root;
  //       }
  //     } else {
  //       s = x.parent.left;
  //       if (s.isRed == true) {
  //         s.isRed = false;
  //         x.parent.isRed = true;
  //         rightRotate(x.parent);
  //         s = x.parent.left;
  //       }

  //       if (s.right.isRed == false && s.right.isRed == false) {
  //         s.isRed = true;
  //         x = x.parent;
  //       } else {
  //         if (s.left.isRed == false) {
  //           s.right.isRed = false;
  //           s.isRed = true;
  //           leftRotate(s);
  //           s = x.parent.left;
  //         }

  //         s.isRed = x.parent.isRed;
  //         x.parent.isRed = false;
  //         s.left.isRed = false;
  //         rightRotate(x.parent);
  //         x = root;
  //       }
  //     }
  //   }
  //   x.isRed = false;
  // }

  // private void rbTransplant(Node<K, V> u, Node<K, V> v) {
  //   if (u.parent == null) {
  //     root = v;
  //   } else if (u == u.parent.left) {
  //     u.parent.left = v;
  //   } else {
  //     u.parent.right = v;
  //   }
  //   v.parent = u.parent;
  // }

  // private void deleteNodeHelper(Node<K, V> node, K key) {
  //   Node<K, V> z = TNULL;
  //   Node<K, V> x, y;
  //   while (node != TNULL) {
  //     if (node.key.equals(key)) {
  //       z = node;
  //     }

  //     if (node.key.compareTo(key) < 0) {
  //       node = node.right;
  //     } else {
  //       node = node.left;
  //     }
  //   }

  //   if (z == TNULL) {
  //     System.out.println("Couldn't find key in the tree");
  //     return;
  //   }

  //   y = z;
  //   boolean yOriginalColor = y.isRed;
  //   if (z.left == TNULL) {
  //     x = z.right;
  //     rbTransplant(z, z.right);
  //   } else if (z.right == TNULL) {
  //     x = z.left;
  //     rbTransplant(z, z.left);
  //   } else {
  //     y = minimum(z.right);
  //     yOriginalColor = y.isRed;
  //     x = y.right;
  //     if (y.parent == z) {
  //       x.parent = y;
  //     } else {
  //       rbTransplant(y, y.right);
  //       y.right = z.right;
  //       y.right.parent = y;
  //     }

  //     rbTransplant(z, y);
  //     y.left = z.left;
  //     y.left.parent = y;
  //     y.isRed = z.isRed;
  //   }
  //   if (yOriginalColor == false) {
  //     fixDelete(x);
  //   }
  // }
}
