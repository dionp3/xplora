package org.gnulag.xplora.models;

import java.util.ArrayList;
import java.util.List;

class Node<K, V> {
  K key;
  V value;
  Node<K, V> parent;
  Node<K, V> left;
  Node<K, V> right;
  boolean isRed;
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

  public Node<K, V> getRoot() {
    return this.root;
  }

  public K getKeyByValue(V value) {
    return getKeyByValueHelper(root, value);
  }

  public List<K> searchKeysByContainingValue(String targetValue) {
    List<K> matchingKeys = new ArrayList<>();
    searchKeysByContainingValueHelper(root, targetValue, matchingKeys);
    return matchingKeys;
  }

  public V getValueByKey(K key) {
    Node<K, V> node = searchTreeHelper(this.root, key);
    if (node != TNULL) {
      return node.value;
    }
    return null;
  }

  public List<V> searchValuesByContainingKey(String targetKeyString) {
    List<V> matchingValues = new ArrayList<>();
    searchValuesByContainingKeyHelper(root, targetKeyString, matchingValues);
    return matchingValues;
  }

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

  public Node<K, V> searchTree(K k) {
    return searchTreeHelper(this.root, k);
  }

  public void insert(K key, V value) {
    Node<K, V> node = new Node<K, V>();
    node.parent = null;
    node.key = key;
    node.value = value;
    node.left = TNULL;
    node.right = TNULL;
    node.isRed = true;

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

  private K getKeyByValueHelper(Node<K, V> node, V targetValue) {
    if (node == TNULL) {
      return null;
    }

    if (node.value.equals(targetValue)) {
      return node.key;
    }

    K leftResult = getKeyByValueHelper(node.left, targetValue);
    if (leftResult != null) {
      return leftResult;
    }

    K rightResult = getKeyByValueHelper(node.right, targetValue);
    return rightResult;
  }

  private void searchKeysByContainingValueHelper(
      Node<K, V> node, String targetValue, List<K> matchingKeys) {
    if (node == TNULL) {
      return;
    }

    if (node.value.toString().contains(targetValue)) {
      matchingKeys.add(node.key);
    }

    searchKeysByContainingValueHelper(node.left, targetValue, matchingKeys);
    searchKeysByContainingValueHelper(node.right, targetValue, matchingKeys);
  }

  private void searchValuesByContainingKeyHelper(
      Node<K, V> node, String targetKeyString, List<V> matchingValues) {
    if (node == TNULL) {
      return;
    }

    if (node.key.toString().contains(targetKeyString)) {
      matchingValues.add(node.value);
    }

    searchValuesByContainingKeyHelper(node.left, targetKeyString, matchingValues);
    searchValuesByContainingKeyHelper(node.right, targetKeyString, matchingValues);
  }

  // Search the tree
  private Node<K, V> searchTreeHelper(Node<K, V> node, K key) {
    if (node == TNULL || key.equals(node.key)) {
      return node;
    }

    if (key.compareTo(node.key) < 0) {
      return searchTreeHelper(node.left, key);
    }
    return searchTreeHelper(node.right, key);
  }

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
}
