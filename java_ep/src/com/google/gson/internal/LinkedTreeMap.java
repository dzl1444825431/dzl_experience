/*
 * Copyright (C) 2010 The Android Open Source Project
 * Copyright (C) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A map of comparable keys to values. Unlike {@code TreeMap}, this class uses
 * insertion order for iteration order. Comparison order is only used as an
 * optimization for efficient insertion and removal.
 *
 * <p>This implementation was derived from Android 4.1's TreeMap class.
 */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
  @SuppressWarnings({ "unchecked", "rawtypes" }) // to avoid Comparable<Comparable<Comparable<...>>>
  private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() {
    public int compare(Comparable a, Comparable b) {
		System.out.println("resp1onse LinkedTreeMap: public int compare(Comparable a, Comparable b) { start return ");
		System.out.println("resp1onse LinkedTreeMap: public int compare(Comparable a, Comparable b) { end return ");
      return a.compareTo(b);
    }
  };

  Comparator<? super K> comparator;
  Node<K, V> root;
  int size = 0;
  int modCount = 0;

  // Used to preserve iteration order
  final Node<K, V> header = new Node<K, V>();

  /**
   * Create a natural order, empty tree map whose keys must be mutually
   * comparable and non-null.
   */
  @SuppressWarnings("unchecked") // unsafe! this assumes K is comparable
  public LinkedTreeMap() {
    this((Comparator<? super K>) NATURAL_ORDER);
		System.out.println("resp1onse LinkedTreeMap: public LinkedTreeMap() { start constructor ");
		System.out.println("resp1onse LinkedTreeMap: public LinkedTreeMap() { end constructor ");
  }

  /**
   * Create a tree map ordered by {@code comparator}. This map's keys may only
   * be null if {@code comparator} permits.
   *
   * @param comparator the comparator to order elements with, or {@code null} to
   *     use the natural ordering.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" }) // unsafe! if comparator is null, this assumes K is comparable
  public LinkedTreeMap(Comparator<? super K> comparator) {
	  System.out.println("resp1onse LinkedTreeMap: public LinkedTreeMap(Comparator<? super K> comparator) { start constructor ");
    this.comparator = comparator != null
        ? comparator
        : (Comparator) NATURAL_ORDER;
		System.out.println("resp1onse LinkedTreeMap: public LinkedTreeMap(Comparator<? super K> comparator) { end constructor ");
  }

  @Override public int size() {
		System.out.println("resp1onse LinkedTreeMap: @Override public int size() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public int size() { end return ");
    return size;
  }

  @Override public V get(Object key) {
		System.out.println("resp1onse LinkedTreeMap: @Override public V get(Object key) { start return ");
    Node<K, V> node = findByObject(key);
		System.out.println("resp1onse LinkedTreeMap: @Override public V get(Object key) { end return  =1");
    return node != null ? node.value : null;
  }

  @Override public boolean containsKey(Object key) {
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean containsKey(Object key) { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean containsKey(Object key) { end return ");
    return findByObject(key) != null;
  }

  @Override public V put(K key, V value) {
		System.out.println("resp1onse LinkedTreeMap: @Override public V put(K key, V value) { start return ");
    if (key == null) {
      throw new NullPointerException("key == null");
    }
    Node<K, V> created = find(key, true);
    V result = created.value;
    created.value = value;
		System.out.println("resp1onse LinkedTreeMap: @Override public V put(K key, V value) { end return  =1");
    return result;
  }

  @Override public void clear() {
		System.out.println("resp1onse LinkedTreeMap: @Override public void clear() { start void ");
    root = null;
    size = 0;
    modCount++;

    // Clear iteration order
    Node<K, V> header = this.header;
    header.next = header.prev = header;
		System.out.println("resp1onse LinkedTreeMap: @Override public void clear() { end void ");
  }

  @Override public V remove(Object key) {
		System.out.println("resp1onse LinkedTreeMap: @Override public V remove(Object key) { start return ");
    Node<K, V> node = removeInternalByKey(key);
		System.out.println("resp1onse LinkedTreeMap: @Override public V remove(Object key) { end return  =1");
    return node != null ? node.value : null;
  }

  /**
   * Returns the node at or adjacent to the given key, creating it if requested.
   *
   * @throws ClassCastException if {@code key} and the tree's keys aren't
   *     mutually comparable.
   */
  Node<K, V> find(K key, boolean create) {
    Comparator<? super K> comparator = this.comparator;
    Node<K, V> nearest = root;
    int comparison = 0;

    if (nearest != null) {
      // Micro-optimization: avoid polymorphic calls to Comparator.compare().
      @SuppressWarnings("unchecked") // Throws a ClassCastException below if there's trouble.
          Comparable<Object> comparableKey = (comparator == NATURAL_ORDER)
          ? (Comparable<Object>) key
          : null;

      while (true) {
        comparison = (comparableKey != null)
            ? comparableKey.compareTo(nearest.key)
            : comparator.compare(key, nearest.key);

        // We found the requested key.
        if (comparison == 0) {
          return nearest;
        }

        // If it exists, the key is in a subtree. Go deeper.
        Node<K, V> child = (comparison < 0) ? nearest.left : nearest.right;
        if (child == null) {
          break;
        }

        nearest = child;
      }
    }

    // The key doesn't exist in this tree.
    if (!create) {
      return null;
    }

    // Create the node and add it to the tree or the table.
    Node<K, V> header = this.header;
    Node<K, V> created;
    if (nearest == null) {
      // Check that the value is comparable if we didn't do any comparisons.
      if (comparator == NATURAL_ORDER && !(key instanceof Comparable)) {
        throw new ClassCastException(key.getClass().getName() + " is not Comparable");
      }
      created = new Node<K, V>(nearest, key, header, header.prev);
      root = created;
    } else {
      created = new Node<K, V>(nearest, key, header, header.prev);
      if (comparison < 0) { // nearest.key is higher
        nearest.left = created;
      } else { // comparison > 0, nearest.key is lower
        nearest.right = created;
      }
      rebalance(nearest, true);
    }
    size++;
    modCount++;

    return created;
  }

  @SuppressWarnings("unchecked")
  Node<K, V> findByObject(Object key) {
    try {
      return key != null ? find((K) key, false) : null;
    } catch (ClassCastException e) {
      return null;
    }
  }

  /**
   * Returns this map's entry that has the same key and value as {@code
   * entry}, or null if this map has no such entry.
   *
   * <p>This method uses the comparator for key equality rather than {@code
   * equals}. If this map's comparator isn't consistent with equals (such as
   * {@code String.CASE_INSENSITIVE_ORDER}), then {@code remove()} and {@code
   * contains()} will violate the collections API.
   */
  Node<K, V> findByEntry(Entry<?, ?> entry) {
    Node<K, V> mine = findByObject(entry.getKey());
    boolean valuesEqual = mine != null && equal(mine.value, entry.getValue());
    return valuesEqual ? mine : null;
  }

  private boolean equal(Object a, Object b) {
		System.out.println("resp1onse LinkedTreeMap: private boolean equal(Object a, Object b) { start return ");
		System.out.println("resp1onse LinkedTreeMap: private boolean equal(Object a, Object b) { end return ");
    return a == b || (a != null && a.equals(b));
  }

  /**
   * Removes {@code node} from this tree, rearranging the tree's structure as
   * necessary.
   *
   * @param unlink true to also unlink this node from the iteration linked list.
   */
  void removeInternal(Node<K, V> node, boolean unlink) {
		System.out.println("resp1onse LinkedTreeMap: void removeInternal(Node<K, V> node, boolean unlink) { start void ");
    if (unlink) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    Node<K, V> left = node.left;
    Node<K, V> right = node.right;
    Node<K, V> originalParent = node.parent;
    if (left != null && right != null) {

      /*
       * To remove a node with both left and right subtrees, move an
       * adjacent node from one of those subtrees into this node's place.
       *
       * Removing the adjacent node may change this node's subtrees. This
       * node may no longer have two subtrees once the adjacent node is
       * gone!
       */

      Node<K, V> adjacent = (left.height > right.height) ? left.last() : right.first();
      removeInternal(adjacent, false); // takes care of rebalance and size--

      int leftHeight = 0;
      left = node.left;
      if (left != null) {
        leftHeight = left.height;
        adjacent.left = left;
        left.parent = adjacent;
        node.left = null;
      }

      int rightHeight = 0;
      right = node.right;
      if (right != null) {
        rightHeight = right.height;
        adjacent.right = right;
        right.parent = adjacent;
        node.right = null;
      }

      adjacent.height = Math.max(leftHeight, rightHeight) + 1;
      replaceInParent(node, adjacent);
		System.out.println("resp1onse LinkedTreeMap: void removeInternal(Node<K, V> node, boolean unlink) { end return if ");
      return;
    } else if (left != null) {
      replaceInParent(node, left);
      node.left = null;
    } else if (right != null) {
      replaceInParent(node, right);
      node.right = null;
    } else {
      replaceInParent(node, null);
    }

    rebalance(originalParent, false);
    size--;
    modCount++;
		System.out.println("resp1onse LinkedTreeMap: void removeInternal(Node<K, V> node, boolean unlink) { end void ");
  }

  Node<K, V> removeInternalByKey(Object key) {
    Node<K, V> node = findByObject(key);
    if (node != null) {
      removeInternal(node, true);
    }
    return node;
  }

  private void replaceInParent(Node<K, V> node, Node<K, V> replacement) {
		System.out.println("resp1onse LinkedTreeMap: private void replaceInParent(Node<K, V> node, Node<K, V> replacement) { start void ");
    Node<K, V> parent = node.parent;
    node.parent = null;
    if (replacement != null) {
      replacement.parent = parent;
    }

    if (parent != null) {
      if (parent.left == node) {
        parent.left = replacement;
      } else {
        assert (parent.right == node);
        parent.right = replacement;
      }
    } else {
      root = replacement;
    }
		System.out.println("resp1onse LinkedTreeMap: private void replaceInParent(Node<K, V> node, Node<K, V> replacement) { end void ");
  }

  /**
   * Rebalances the tree by making any AVL rotations necessary between the
   * newly-unbalanced node and the tree's root.
   *
   * @param insert true if the node was unbalanced by an insert; false if it
   *     was by a removal.
   */
  private void rebalance(Node<K, V> unbalanced, boolean insert) {
		System.out.println("resp1onse LinkedTreeMap: private void rebalance(Node<K, V> unbalanced, boolean insert) { start void ");
    for (Node<K, V> node = unbalanced; node != null; node = node.parent) {
      Node<K, V> left = node.left;
      Node<K, V> right = node.right;
      int leftHeight = left != null ? left.height : 0;
      int rightHeight = right != null ? right.height : 0;

      int delta = leftHeight - rightHeight;
      if (delta == -2) {
        Node<K, V> rightLeft = right.left;
        Node<K, V> rightRight = right.right;
        int rightRightHeight = rightRight != null ? rightRight.height : 0;
        int rightLeftHeight = rightLeft != null ? rightLeft.height : 0;

        int rightDelta = rightLeftHeight - rightRightHeight;
        if (rightDelta == -1 || (rightDelta == 0 && !insert)) {
          rotateLeft(node); // AVL right right
        } else {
          assert (rightDelta == 1);
          rotateRight(right); // AVL right left
          rotateLeft(node);
        }
        if (insert) {
          break; // no further rotations will be necessary
        }

      } else if (delta == 2) {
        Node<K, V> leftLeft = left.left;
        Node<K, V> leftRight = left.right;
        int leftRightHeight = leftRight != null ? leftRight.height : 0;
        int leftLeftHeight = leftLeft != null ? leftLeft.height : 0;

        int leftDelta = leftLeftHeight - leftRightHeight;
        if (leftDelta == 1 || (leftDelta == 0 && !insert)) {
          rotateRight(node); // AVL left left
        } else {
          assert (leftDelta == -1);
          rotateLeft(left); // AVL left right
          rotateRight(node);
        }
        if (insert) {
          break; // no further rotations will be necessary
        }

      } else if (delta == 0) {
        node.height = leftHeight + 1; // leftHeight == rightHeight
        if (insert) {
          break; // the insert caused balance, so rebalancing is done!
        }

      } else {
        assert (delta == -1 || delta == 1);
        node.height = Math.max(leftHeight, rightHeight) + 1;
        if (!insert) {
          break; // the height hasn't changed, so rebalancing is done!
        }
      }
    }
		System.out.println("resp1onse LinkedTreeMap: private void rebalance(Node<K, V> unbalanced, boolean insert) { end void ");
  }

  /**
   * Rotates the subtree so that its root's right child is the new root.
   */
  private void rotateLeft(Node<K, V> root) {
		System.out.println("resp1onse LinkedTreeMap: private void rotateLeft(Node<K, V> root) { start void ");
    Node<K, V> left = root.left;
    Node<K, V> pivot = root.right;
    Node<K, V> pivotLeft = pivot.left;
    Node<K, V> pivotRight = pivot.right;

    // move the pivot's left child to the root's right
    root.right = pivotLeft;
    if (pivotLeft != null) {
      pivotLeft.parent = root;
    }

    replaceInParent(root, pivot);

    // move the root to the pivot's left
    pivot.left = root;
    root.parent = pivot;

    // fix heights
    root.height = Math.max(left != null ? left.height : 0,
        pivotLeft != null ? pivotLeft.height : 0) + 1;
    pivot.height = Math.max(root.height,
        pivotRight != null ? pivotRight.height : 0) + 1;
		System.out.println("resp1onse LinkedTreeMap: private void rotateLeft(Node<K, V> root) { end void ");
  }

  /**
   * Rotates the subtree so that its root's left child is the new root.
   */
  private void rotateRight(Node<K, V> root) {
		System.out.println("resp1onse LinkedTreeMap: private void rotateRight(Node<K, V> root) { start void ");
    Node<K, V> pivot = root.left;
    Node<K, V> right = root.right;
    Node<K, V> pivotLeft = pivot.left;
    Node<K, V> pivotRight = pivot.right;

    // move the pivot's right child to the root's left
    root.left = pivotRight;
    if (pivotRight != null) {
      pivotRight.parent = root;
    }

    replaceInParent(root, pivot);

    // move the root to the pivot's right
    pivot.right = root;
    root.parent = pivot;

    // fixup heights
    root.height = Math.max(right != null ? right.height : 0,
        pivotRight != null ? pivotRight.height : 0) + 1;
    pivot.height = Math.max(root.height,
        pivotLeft != null ? pivotLeft.height : 0) + 1;
		System.out.println("resp1onse LinkedTreeMap: private void rotateRight(Node<K, V> root) { end void ");
  }

  private EntrySet entrySet;
  private KeySet keySet;

  @Override public Set<Entry<K, V>> entrySet() {
		System.out.println("resp1onse LinkedTreeMap: @Override public Set<Entry<K, V>> entrySet() { start return ");
    EntrySet result = entrySet;
		System.out.println("resp1onse LinkedTreeMap: @Override public Set<Entry<K, V>> entrySet() { end return  =1");
    return result != null ? result : (entrySet = new EntrySet());
  }

  @Override public Set<K> keySet() {
		System.out.println("resp1onse LinkedTreeMap: @Override public Set<K> keySet() { start return ");
    KeySet result = keySet;
		System.out.println("resp1onse LinkedTreeMap: @Override public Set<K> keySet() { end return  =1");
    return result != null ? result : (keySet = new KeySet());
  }

  static final class Node<K, V> implements Entry<K, V> {
    Node<K, V> parent;
    Node<K, V> left;
    Node<K, V> right;
    Node<K, V> next;
    Node<K, V> prev;
    final K key;
    V value;
    int height;

    /** Create the header entry */
    Node() {
      key = null;
      next = prev = this;
    }

    /** Create a regular entry */
    Node(Node<K, V> parent, K key, Node<K, V> next, Node<K, V> prev) {
      this.parent = parent;
      this.key = key;
      this.height = 1;
      this.next = next;
      this.prev = prev;
      prev.next = this;
      next.prev = this;
    }

    public K getKey() {
		System.out.println("resp1onse LinkedTreeMap: public K getKey() { start return ");
		System.out.println("resp1onse LinkedTreeMap: public K getKey() { end return ");
      return key;
    }

    public V getValue() {
		System.out.println("resp1onse LinkedTreeMap: public V getValue() { start return ");
		System.out.println("resp1onse LinkedTreeMap: public V getValue() { end return ");
      return value;
    }

    public V setValue(V value) {
		System.out.println("resp1onse LinkedTreeMap: public V setValue(V value) { start return ");
      V oldValue = this.value;
      this.value = value;
		System.out.println("resp1onse LinkedTreeMap: public V setValue(V value) { end return  =1");
      return oldValue;
    }

    @SuppressWarnings("rawtypes")
    @Override public boolean equals(Object o) {
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean equals(Object o) { start return ");
      if (o instanceof Entry) {
        Entry other = (Entry) o;
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean equals(Object o) { end return if ");
        return (key == null ? other.getKey() == null : key.equals(other.getKey()))
            && (value == null ? other.getValue() == null : value.equals(other.getValue()));
      }
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean equals(Object o) { end return  =1");
      return false;
    }

    @Override public int hashCode() {
		System.out.println("resp1onse LinkedTreeMap: @Override public int hashCode() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public int hashCode() { end return ");
      return (key == null ? 0 : key.hashCode())
          ^ (value == null ? 0 : value.hashCode());
    }

    @Override public String toString() {
		System.out.println("resp1onse LinkedTreeMap: @Override public String toString() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public String toString() { end return ");
      return key + "=" + value;
    }

    /**
     * Returns the first node in this subtree.
     */
    public Node<K, V> first() {
		System.out.println("resp1onse LinkedTreeMap: public Node<K, V> first() { start return ");
      Node<K, V> node = this;
      Node<K, V> child = node.left;
      while (child != null) {
        node = child;
        child = node.left;
      }
		System.out.println("resp1onse LinkedTreeMap: public Node<K, V> first() { end return  =1");
      return node;
    }

    /**
     * Returns the last node in this subtree.
     */
    public Node<K, V> last() {
		System.out.println("resp1onse LinkedTreeMap: public Node<K, V> last() { start return ");
      Node<K, V> node = this;
      Node<K, V> child = node.right;
      while (child != null) {
        node = child;
        child = node.right;
      }
		System.out.println("resp1onse LinkedTreeMap: public Node<K, V> last() { end return  =1");
      return node;
    }
  }

  private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
    Node<K, V> next = header.next;
    Node<K, V> lastReturned = null;
    int expectedModCount = modCount;

    public final boolean hasNext() {
		System.out.println("resp1onse LinkedTreeMap: public final boolean hasNext() { start return ");
		System.out.println("resp1onse LinkedTreeMap: public final boolean hasNext() { end return ");
      return next != header;
    }

    final Node<K, V> nextNode() {
      Node<K, V> e = next;
      if (e == header) {
        throw new NoSuchElementException();
      }
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
      next = e.next;
      return lastReturned = e;
    }

    public final void remove() {
		System.out.println("resp1onse LinkedTreeMap: public final void remove() { start void ");
      if (lastReturned == null) {
        throw new IllegalStateException();
      }
      removeInternal(lastReturned, true);
      lastReturned = null;
      expectedModCount = modCount;
		System.out.println("resp1onse LinkedTreeMap: public final void remove() { end void ");
    }
  }

  class EntrySet extends AbstractSet<Entry<K, V>> {
    @Override public int size() {
		System.out.println("resp1onse LinkedTreeMap: @Override public int size() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public int size() { end return ");
      return size;
    }

    @Override public Iterator<Entry<K, V>> iterator() {
		System.out.println("resp1onse LinkedTreeMap: @Override public Iterator<Entry<K, V>> iterator() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public Iterator<Entry<K, V>> iterator() { end return ");
      return new LinkedTreeMapIterator<Entry<K, V>>() {
        public Entry<K, V> next() {
		System.out.println("resp1onse LinkedTreeMap: public Entry<K, V> next() { start internal 3");
          return nextNode();
        }
      };
    }

    @Override public boolean contains(Object o) {
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean contains(Object o) { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean contains(Object o) { end return ");
      return o instanceof Entry && findByEntry((Entry<?, ?>) o) != null;
    }

    @Override public boolean remove(Object o) {
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean remove(Object o) { start return ");
      if (!(o instanceof Entry)) {
        return false;
      }

      Node<K, V> node = findByEntry((Entry<?, ?>) o);
      if (node == null) {
        return false;
      }
      removeInternal(node, true);
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean remove(Object o) { end return  =1");
      return true;
    }

    @Override public void clear() {
		System.out.println("resp1onse LinkedTreeMap: @Override public void clear() { start void ");
      LinkedTreeMap.this.clear();
		System.out.println("resp1onse LinkedTreeMap: @Override public void clear() { end void ");
    }
  }

  final class KeySet extends AbstractSet<K> {
    @Override public int size() {
		System.out.println("resp1onse LinkedTreeMap: @Override public int size() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public int size() { end return ");
      return size;
    }

    @Override public Iterator<K> iterator() {
		System.out.println("resp1onse LinkedTreeMap: @Override public Iterator<K> iterator() { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public Iterator<K> iterator() { end return ");
      return new LinkedTreeMapIterator<K>() {
        public K next() {
		System.out.println("resp1onse LinkedTreeMap: public K next() { start internal 3");
          return nextNode().key;
        }
      };
    }

    @Override public boolean contains(Object o) {
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean contains(Object o) { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean contains(Object o) { end return ");
      return containsKey(o);
    }

    @Override public boolean remove(Object key) {
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean remove(Object key) { start return ");
		System.out.println("resp1onse LinkedTreeMap: @Override public boolean remove(Object key) { end return ");
      return removeInternalByKey(key) != null;
    }

    @Override public void clear() {
		System.out.println("resp1onse LinkedTreeMap: @Override public void clear() { start void ");
      LinkedTreeMap.this.clear();
		System.out.println("resp1onse LinkedTreeMap: @Override public void clear() { end void ");
    }
  }

  /**
   * If somebody is unlucky enough to have to serialize one of these, serialize
   * it as a LinkedHashMap so that they won't need Gson on the other side to
   * deserialize it. Using serialization defeats our DoS defence, so most apps
   * shouldn't use it.
   */
  private Object writeReplace() throws ObjectStreamException {
    return new LinkedHashMap<K, V>(this);
  }
}
