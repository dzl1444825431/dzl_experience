/**
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.gson.internal.$Gson$Preconditions.checkArgument;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Static methods for working with types.
 *
 * @author Bob Lee
 * @author Jesse Wilson
 */
public final class $Gson$Types {
  static final Type[] EMPTY_TYPE_ARRAY = new Type[] {};

  private $Gson$Types() {
		System.out.println("resp1onse $Gson$Types: private $Gson$Types() { start constructor ");
		System.out.println("resp1onse $Gson$Types: private $Gson$Types() { end constructor ");
    throw new UnsupportedOperationException();
  }

  /**
   * Returns a new parameterized type, applying {@code typeArguments} to
   * {@code rawType} and enclosed by {@code ownerType}.
   *
   * @return a {@link java.io.Serializable serializable} parameterized type.
   */
  public static ParameterizedType newParameterizedTypeWithOwner(
      Type ownerType, Type rawType, Type... typeArguments) {
    return new ParameterizedTypeImpl(ownerType, rawType, typeArguments);
  }

  /**
   * Returns an array type whose elements are all instances of
   * {@code componentType}.
   *
   * @return a {@link java.io.Serializable serializable} generic array type.
   */
  public static GenericArrayType arrayOf(Type componentType) {
		System.out.println("resp1onse $Gson$Types: public static GenericArrayType arrayOf(Type componentType) { start return ");
		System.out.println("resp1onse $Gson$Types: public static GenericArrayType arrayOf(Type componentType) { end return ");
    return new GenericArrayTypeImpl(componentType);
  }

  /**
   * Returns a type that represents an unknown type that extends {@code bound}.
   * For example, if {@code bound} is {@code CharSequence.class}, this returns
   * {@code ? extends CharSequence}. If {@code bound} is {@code Object.class},
   * this returns {@code ?}, which is shorthand for {@code ? extends Object}.
   */
  public static WildcardType subtypeOf(Type bound) {
		System.out.println("resp1onse $Gson$Types: public static WildcardType subtypeOf(Type bound) { start return ");
		System.out.println("resp1onse $Gson$Types: public static WildcardType subtypeOf(Type bound) { end return ");
    return new WildcardTypeImpl(new Type[] { bound }, EMPTY_TYPE_ARRAY);
  }

  /**
   * Returns a type that represents an unknown supertype of {@code bound}. For
   * example, if {@code bound} is {@code String.class}, this returns {@code ?
   * super String}.
   */
  public static WildcardType supertypeOf(Type bound) {
		System.out.println("resp1onse $Gson$Types: public static WildcardType supertypeOf(Type bound) { start return ");
		System.out.println("resp1onse $Gson$Types: public static WildcardType supertypeOf(Type bound) { end return ");
    return new WildcardTypeImpl(new Type[] { Object.class }, new Type[] { bound });
  }

  /**
   * Returns a type that is functionally equal but not necessarily equal
   * according to {@link Object#equals(Object) Object.equals()}. The returned
   * type is {@link java.io.Serializable}.
   */
  public static Type canonicalize(Type type) {
		System.out.println("resp1onse $Gson$Types: public static Type canonicalize(Type type) { start return ");
    if (type instanceof Class) {
      Class<?> c = (Class<?>) type;
		System.out.println("resp1onse $Gson$Types: public static Type canonicalize(Type type) { end return if ");
      return c.isArray() ? new GenericArrayTypeImpl(canonicalize(c.getComponentType())) : c;

    } else if (type instanceof ParameterizedType) {
      ParameterizedType p = (ParameterizedType) type;
		System.out.println("resp1onse $Gson$Types: public static Type canonicalize(Type type) { end return if ");
      return new ParameterizedTypeImpl(p.getOwnerType(),
          p.getRawType(), p.getActualTypeArguments());

    } else if (type instanceof GenericArrayType) {
      GenericArrayType g = (GenericArrayType) type;
		System.out.println("resp1onse $Gson$Types: public static Type canonicalize(Type type) { end return if ");
      return new GenericArrayTypeImpl(g.getGenericComponentType());

    } else if (type instanceof WildcardType) {
      WildcardType w = (WildcardType) type;
		System.out.println("resp1onse $Gson$Types: public static Type canonicalize(Type type) { end return if ");
      return new WildcardTypeImpl(w.getUpperBounds(), w.getLowerBounds());

    } else {
      // type is either serializable as-is or unsupported
      return type;
    }
  }

  public static Class<?> getRawType(Type type) {
		System.out.println("resp1onse $Gson$Types: public static Class<?> getRawType(Type type) { start return ");
    if (type instanceof Class<?>) {
      // type is a normal class.
      return (Class<?>) type;

    } else if (type instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) type;

      // I'm not exactly sure why getRawType() returns Type instead of Class.
      // Neal isn't either but suspects some pathological case related
      // to nested classes exists.
      Type rawType = parameterizedType.getRawType();
      checkArgument(rawType instanceof Class);
		System.out.println("resp1onse $Gson$Types: public static Class<?> getRawType(Type type) { end return if ");
      return (Class<?>) rawType;

    } else if (type instanceof GenericArrayType) {
      Type componentType = ((GenericArrayType)type).getGenericComponentType();
		System.out.println("resp1onse $Gson$Types: public static Class<?> getRawType(Type type) { end return if ");
      return Array.newInstance(getRawType(componentType), 0).getClass();

    } else if (type instanceof TypeVariable) {
      // we could use the variable's bounds, but that won't work if there are multiple.
      // having a raw type that's more general than necessary is okay
      return Object.class;

    } else if (type instanceof WildcardType) {
      return getRawType(((WildcardType) type).getUpperBounds()[0]);

    } else {
      String className = type == null ? "null" : type.getClass().getName();
		System.out.println("resp1onse $Gson$Types: public static Class<?> getRawType(Type type) { end return if ");
      throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
          + "GenericArrayType, but <" + type + "> is of type " + className);
    }
  }

  static boolean equal(Object a, Object b) {
		System.out.println("resp1onse $Gson$Types: static boolean equal(Object a, Object b) { start return ");
		System.out.println("resp1onse $Gson$Types: static boolean equal(Object a, Object b) { end return ");
    return a == b || (a != null && a.equals(b));
  }

  /**
   * Returns true if {@code a} and {@code b} are equal.
   */
  public static boolean equals(Type a, Type b) {
		System.out.println("resp1onse $Gson$Types: public static boolean equals(Type a, Type b) { start return ");
    if (a == b) {
      // also handles (a == null && b == null)
      return true;

    } else if (a instanceof Class) {
      // Class already specifies equals().
      return a.equals(b);

    } else if (a instanceof ParameterizedType) {
      if (!(b instanceof ParameterizedType)) {
        return false;
      }

      // TODO: save a .clone() call
      ParameterizedType pa = (ParameterizedType) a;
      ParameterizedType pb = (ParameterizedType) b;
		System.out.println("resp1onse $Gson$Types: public static boolean equals(Type a, Type b) { end return if ");
      return equal(pa.getOwnerType(), pb.getOwnerType())
          && pa.getRawType().equals(pb.getRawType())
          && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments());

    } else if (a instanceof GenericArrayType) {
      if (!(b instanceof GenericArrayType)) {
        return false;
      }

      GenericArrayType ga = (GenericArrayType) a;
      GenericArrayType gb = (GenericArrayType) b;
		System.out.println("resp1onse $Gson$Types: public static boolean equals(Type a, Type b) { end return if ");
      return equals(ga.getGenericComponentType(), gb.getGenericComponentType());

    } else if (a instanceof WildcardType) {
      if (!(b instanceof WildcardType)) {
        return false;
      }

      WildcardType wa = (WildcardType) a;
      WildcardType wb = (WildcardType) b;
		System.out.println("resp1onse $Gson$Types: public static boolean equals(Type a, Type b) { end return if ");
      return Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds())
          && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds());

    } else if (a instanceof TypeVariable) {
      if (!(b instanceof TypeVariable)) {
        return false;
      }
      TypeVariable<?> va = (TypeVariable<?>) a;
      TypeVariable<?> vb = (TypeVariable<?>) b;
		System.out.println("resp1onse $Gson$Types: public static boolean equals(Type a, Type b) { end return if ");
      return va.getGenericDeclaration() == vb.getGenericDeclaration()
          && va.getName().equals(vb.getName());

    } else {
      // This isn't a type we support. Could be a generic array type, wildcard type, etc.
      return false;
    }
  }

  private static int hashCodeOrZero(Object o) {
		System.out.println("resp1onse $Gson$Types: private static int hashCodeOrZero(Object o) { start return ");
		System.out.println("resp1onse $Gson$Types: private static int hashCodeOrZero(Object o) { end return ");
    return o != null ? o.hashCode() : 0;
  }

  public static String typeToString(Type type) {
		System.out.println("resp1onse $Gson$Types: public static String typeToString(Type type) { start return ");
		System.out.println("resp1onse $Gson$Types: public static String typeToString(Type type) { end return ");
    return type instanceof Class ? ((Class<?>) type).getName() : type.toString();
  }

  /**
   * Returns the generic supertype for {@code supertype}. For example, given a class {@code
   * IntegerSet}, the result for when supertype is {@code Set.class} is {@code Set<Integer>} and the
   * result when the supertype is {@code Collection.class} is {@code Collection<Integer>}.
   */
  static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> toResolve) {
		System.out.println("resp1onse $Gson$Types: static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> toResolve) { start return ");
    if (toResolve == rawType) {
      return context;
    }

    // we skip searching through interfaces if unknown is an interface
    if (toResolve.isInterface()) {
      Class<?>[] interfaces = rawType.getInterfaces();
      for (int i = 0, length = interfaces.length; i < length; i++) {
        if (interfaces[i] == toResolve) {
          return rawType.getGenericInterfaces()[i];
        } else if (toResolve.isAssignableFrom(interfaces[i])) {
          return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], toResolve);
        }
      }
    }

    // check our supertypes
    if (!rawType.isInterface()) {
      while (rawType != Object.class) {
        Class<?> rawSupertype = rawType.getSuperclass();
        if (rawSupertype == toResolve) {
          return rawType.getGenericSuperclass();
        } else if (toResolve.isAssignableFrom(rawSupertype)) {
          return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, toResolve);
        }
        rawType = rawSupertype;
      }
    }

    // we can't resolve this further
		System.out.println("resp1onse $Gson$Types: static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> toResolve) { end return  =1");
    return toResolve;
  }

  /**
   * Returns the generic form of {@code supertype}. For example, if this is {@code
   * ArrayList<String>}, this returns {@code Iterable<String>} given the input {@code
   * Iterable.class}.
   *
   * @param supertype a superclass of, or interface implemented by, this.
   */
  static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
		System.out.println("resp1onse $Gson$Types: static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) { start return ");
    checkArgument(supertype.isAssignableFrom(contextRawType));
		System.out.println("resp1onse $Gson$Types: static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) { end return  =1");
    return resolve(context, contextRawType,
        $Gson$Types.getGenericSupertype(context, contextRawType, supertype));
  }

  /**
   * Returns the component type of this array type.
   * @throws ClassCastException if this type is not an array.
   */
  public static Type getArrayComponentType(Type array) {
		System.out.println("resp1onse $Gson$Types: public static Type getArrayComponentType(Type array) { start return ");
		System.out.println("resp1onse $Gson$Types: public static Type getArrayComponentType(Type array) { end return ");
    return array instanceof GenericArrayType
        ? ((GenericArrayType) array).getGenericComponentType()
        : ((Class<?>) array).getComponentType();
  }

  /**
   * Returns the element type of this collection type.
   * @throws IllegalArgumentException if this type is not a collection.
   */
  public static Type getCollectionElementType(Type context, Class<?> contextRawType) {
		System.out.println("resp1onse $Gson$Types: public static Type getCollectionElementType(Type context, Class<?> contextRawType) { start return ");
    Type collectionType = getSupertype(context, contextRawType, Collection.class);

    if (collectionType instanceof WildcardType) {
      collectionType = ((WildcardType)collectionType).getUpperBounds()[0];
    }
    if (collectionType instanceof ParameterizedType) {
      return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
    }
		System.out.println("resp1onse $Gson$Types: public static Type getCollectionElementType(Type context, Class<?> contextRawType) { end return  =1");
    return Object.class;
  }

  /**
   * Returns a two element array containing this map's key and value types in
   * positions 0 and 1 respectively.
   */
  public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) {
    /*
     * Work around a problem with the declaration of java.util.Properties. That
     * class should extend Hashtable<String, String>, but it's declared to
     * extend Hashtable<Object, Object>.
     */
		System.out.println("resp1onse $Gson$Types: public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) { start return ");
    if (context == Properties.class) {
      return new Type[] { String.class, String.class }; // TODO: test subclasses of Properties!
    }

    Type mapType = getSupertype(context, contextRawType, Map.class);
    // TODO: strip wildcards?
    if (mapType instanceof ParameterizedType) {
      ParameterizedType mapParameterizedType = (ParameterizedType) mapType;
		System.out.println("resp1onse $Gson$Types: public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) { end return if ");
      return mapParameterizedType.getActualTypeArguments();
    }
		System.out.println("resp1onse $Gson$Types: public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) { end return  =1");
    return new Type[] { Object.class, Object.class };
  }

  public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) {
    // this implementation is made a little more complicated in an attempt to avoid object-creation
		System.out.println("resp1onse $Gson$Types: public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) { start return ");
    while (true) {
      if (toResolve instanceof TypeVariable) {
        TypeVariable<?> typeVariable = (TypeVariable<?>) toResolve;
        toResolve = resolveTypeVariable(context, contextRawType, typeVariable);
        if (toResolve == typeVariable) {
          return toResolve;
        }

      } else if (toResolve instanceof Class && ((Class<?>) toResolve).isArray()) {
        Class<?> original = (Class<?>) toResolve;
        Type componentType = original.getComponentType();
        Type newComponentType = resolve(context, contextRawType, componentType);
		System.out.println("resp1onse $Gson$Types: public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) { end return if ");
        return componentType == newComponentType
            ? original
            : arrayOf(newComponentType);

      } else if (toResolve instanceof GenericArrayType) {
        GenericArrayType original = (GenericArrayType) toResolve;
        Type componentType = original.getGenericComponentType();
        Type newComponentType = resolve(context, contextRawType, componentType);
		System.out.println("resp1onse $Gson$Types: public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) { end return if ");
        return componentType == newComponentType
            ? original
            : arrayOf(newComponentType);

      } else if (toResolve instanceof ParameterizedType) {
        ParameterizedType original = (ParameterizedType) toResolve;
        Type ownerType = original.getOwnerType();
        Type newOwnerType = resolve(context, contextRawType, ownerType);
        boolean changed = newOwnerType != ownerType;

        Type[] args = original.getActualTypeArguments();
        for (int t = 0, length = args.length; t < length; t++) {
          Type resolvedTypeArgument = resolve(context, contextRawType, args[t]);
          if (resolvedTypeArgument != args[t]) {
            if (!changed) {
              args = args.clone();
              changed = true;
            }
            args[t] = resolvedTypeArgument;
          }
        }

		System.out.println("resp1onse $Gson$Types: public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) { end return if ");
        return changed
            ? newParameterizedTypeWithOwner(newOwnerType, original.getRawType(), args)
            : original;

      } else if (toResolve instanceof WildcardType) {
        WildcardType original = (WildcardType) toResolve;
        Type[] originalLowerBound = original.getLowerBounds();
        Type[] originalUpperBound = original.getUpperBounds();

        if (originalLowerBound.length == 1) {
          Type lowerBound = resolve(context, contextRawType, originalLowerBound[0]);
          if (lowerBound != originalLowerBound[0]) {
            return supertypeOf(lowerBound);
          }
        } else if (originalUpperBound.length == 1) {
          Type upperBound = resolve(context, contextRawType, originalUpperBound[0]);
          if (upperBound != originalUpperBound[0]) {
            return subtypeOf(upperBound);
          }
        }
		System.out.println("resp1onse $Gson$Types: public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) { end return if ");
        return original;

      } else {
        return toResolve;
      }
    }
  }

  static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) {
		System.out.println("resp1onse $Gson$Types: static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) { start return ");
    Class<?> declaredByRaw = declaringClassOf(unknown);

    // we can't reduce this further
    if (declaredByRaw == null) {
      return unknown;
    }

    Type declaredBy = getGenericSupertype(context, contextRawType, declaredByRaw);
    if (declaredBy instanceof ParameterizedType) {
      int index = indexOf(declaredByRaw.getTypeParameters(), unknown);
		System.out.println("resp1onse $Gson$Types: static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) { end return if ");
      return ((ParameterizedType) declaredBy).getActualTypeArguments()[index];
    }

		System.out.println("resp1onse $Gson$Types: static Type resolveTypeVariable(Type context, Class<?> contextRawType, TypeVariable<?> unknown) { end return  =1");
    return unknown;
  }

  private static int indexOf(Object[] array, Object toFind) {
		System.out.println("resp1onse $Gson$Types: private static int indexOf(Object[] array, Object toFind) { start return ");
    for (int i = 0; i < array.length; i++) {
      if (toFind.equals(array[i])) {
        return i;
      }
    }
		System.out.println("resp1onse $Gson$Types: private static int indexOf(Object[] array, Object toFind) { end return  =1");
    throw new NoSuchElementException();
  }

  /**
   * Returns the declaring class of {@code typeVariable}, or {@code null} if it was not declared by
   * a class.
   */
  private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
		System.out.println("resp1onse $Gson$Types: private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) { start return ");
    GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
		System.out.println("resp1onse $Gson$Types: private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) { end return  =1");
    return genericDeclaration instanceof Class
        ? (Class<?>) genericDeclaration
        : null;
  }

  private static void checkNotPrimitive(Type type) {
		System.out.println("resp1onse $Gson$Types: private static void checkNotPrimitive(Type type) { start void ");
    checkArgument(!(type instanceof Class<?>) || !((Class<?>) type).isPrimitive());
		System.out.println("resp1onse $Gson$Types: private static void checkNotPrimitive(Type type) { end void ");
  }

  private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(Type ownerType, Type rawType, Type... typeArguments) {
      // require an owner type if the raw type needs it
		System.out.println("resp1onse $Gson$Types: public ParameterizedTypeImpl(Type ownerType, Type rawType, Type... typeArguments) { start constructor ");
      if (rawType instanceof Class<?>) {
        Class<?> rawTypeAsClass = (Class<?>) rawType;
        boolean isStaticOrTopLevelClass = Modifier.isStatic(rawTypeAsClass.getModifiers())
            || rawTypeAsClass.getEnclosingClass() == null;
        checkArgument(ownerType != null || isStaticOrTopLevelClass);
      }

      this.ownerType = ownerType == null ? null : canonicalize(ownerType);
      this.rawType = canonicalize(rawType);
      this.typeArguments = typeArguments.clone();
      for (int t = 0; t < this.typeArguments.length; t++) {
        checkNotNull(this.typeArguments[t]);
        checkNotPrimitive(this.typeArguments[t]);
        this.typeArguments[t] = canonicalize(this.typeArguments[t]);
      }
		System.out.println("resp1onse $Gson$Types: public ParameterizedTypeImpl(Type ownerType, Type rawType, Type... typeArguments) { end constructor ");
    }

    public Type[] getActualTypeArguments() {
		System.out.println("resp1onse $Gson$Types: public Type[] getActualTypeArguments() { start return ");
		System.out.println("resp1onse $Gson$Types: public Type[] getActualTypeArguments() { end return ");
      return typeArguments.clone();
    }

    public Type getRawType() {
		System.out.println("resp1onse $Gson$Types: public Type getRawType() { start return ");
		System.out.println("resp1onse $Gson$Types: public Type getRawType() { end return ");
      return rawType;
    }

    public Type getOwnerType() {
		System.out.println("resp1onse $Gson$Types: public Type getOwnerType() { start return ");
		System.out.println("resp1onse $Gson$Types: public Type getOwnerType() { end return ");
      return ownerType;
    }

    @Override public boolean equals(Object other) {
		System.out.println("resp1onse $Gson$Types: @Override public boolean equals(Object other) { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public boolean equals(Object other) { end return ");
      return other instanceof ParameterizedType
          && $Gson$Types.equals(this, (ParameterizedType) other);
    }

    @Override public int hashCode() {
		System.out.println("resp1onse $Gson$Types: @Override public int hashCode() { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public int hashCode() { end return ");
      return Arrays.hashCode(typeArguments)
          ^ rawType.hashCode()
          ^ hashCodeOrZero(ownerType);
    }

    @Override public String toString() {
		System.out.println("resp1onse $Gson$Types: @Override public String toString() { start return ");
      StringBuilder stringBuilder = new StringBuilder(30 * (typeArguments.length + 1));
      stringBuilder.append(typeToString(rawType));

      if (typeArguments.length == 0) {
        return stringBuilder.toString();
      }

      stringBuilder.append("<").append(typeToString(typeArguments[0]));
      for (int i = 1; i < typeArguments.length; i++) {
        stringBuilder.append(", ").append(typeToString(typeArguments[i]));
      }
		System.out.println("resp1onse $Gson$Types: @Override public String toString() { end return  =1");
      return stringBuilder.append(">").toString();
    }

    private static final long serialVersionUID = 0;
  }

  private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
    private final Type componentType;

    public GenericArrayTypeImpl(Type componentType) {
      this.componentType = canonicalize(componentType);
		System.out.println("resp1onse $Gson$Types: public GenericArrayTypeImpl(Type componentType) { start constructor ");
		System.out.println("resp1onse $Gson$Types: public GenericArrayTypeImpl(Type componentType) { end constructor ");
    }

    public Type getGenericComponentType() {
		System.out.println("resp1onse $Gson$Types: public Type getGenericComponentType() { start return ");
		System.out.println("resp1onse $Gson$Types: public Type getGenericComponentType() { end return ");
      return componentType;
    }

    @Override public boolean equals(Object o) {
		System.out.println("resp1onse $Gson$Types: @Override public boolean equals(Object o) { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public boolean equals(Object o) { end return ");
      return o instanceof GenericArrayType
          && $Gson$Types.equals(this, (GenericArrayType) o);
    }

    @Override public int hashCode() {
		System.out.println("resp1onse $Gson$Types: @Override public int hashCode() { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public int hashCode() { end return ");
      return componentType.hashCode();
    }

    @Override public String toString() {
		System.out.println("resp1onse $Gson$Types: @Override public String toString() { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public String toString() { end return ");
      return typeToString(componentType) + "[]";
    }

    private static final long serialVersionUID = 0;
  }

  /**
   * The WildcardType interface supports multiple upper bounds and multiple
   * lower bounds. We only support what the Java 6 language needs - at most one
   * bound. If a lower bound is set, the upper bound must be Object.class.
   */
  private static final class WildcardTypeImpl implements WildcardType, Serializable {
    private final Type upperBound;
    private final Type lowerBound;

    public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) {
		System.out.println("resp1onse $Gson$Types: public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) { start constructor ");
      checkArgument(lowerBounds.length <= 1);
      checkArgument(upperBounds.length == 1);

      if (lowerBounds.length == 1) {
        checkNotNull(lowerBounds[0]);
        checkNotPrimitive(lowerBounds[0]);
        checkArgument(upperBounds[0] == Object.class);
        this.lowerBound = canonicalize(lowerBounds[0]);
        this.upperBound = Object.class;

      } else {
        checkNotNull(upperBounds[0]);
        checkNotPrimitive(upperBounds[0]);
        this.lowerBound = null;
        this.upperBound = canonicalize(upperBounds[0]);
      }
		System.out.println("resp1onse $Gson$Types: public WildcardTypeImpl(Type[] upperBounds, Type[] lowerBounds) { end constructor ");
    }

    public Type[] getUpperBounds() {
		System.out.println("resp1onse $Gson$Types: public Type[] getUpperBounds() { start return ");
		System.out.println("resp1onse $Gson$Types: public Type[] getUpperBounds() { end return ");
      return new Type[] { upperBound };
    }

    public Type[] getLowerBounds() {
		System.out.println("resp1onse $Gson$Types: public Type[] getLowerBounds() { start return ");
		System.out.println("resp1onse $Gson$Types: public Type[] getLowerBounds() { end return ");
      return lowerBound != null ? new Type[] { lowerBound } : EMPTY_TYPE_ARRAY;
    }

    @Override public boolean equals(Object other) {
		System.out.println("resp1onse $Gson$Types: @Override public boolean equals(Object other) { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public boolean equals(Object other) { end return ");
      return other instanceof WildcardType
          && $Gson$Types.equals(this, (WildcardType) other);
    }

    @Override public int hashCode() {
      // this equals Arrays.hashCode(getLowerBounds()) ^ Arrays.hashCode(getUpperBounds());
		System.out.println("resp1onse $Gson$Types: @Override public int hashCode() { start return ");
		System.out.println("resp1onse $Gson$Types: @Override public int hashCode() { end return ");
      return (lowerBound != null ? 31 + lowerBound.hashCode() : 1)
          ^ (31 + upperBound.hashCode());
    }

    @Override public String toString() {
		System.out.println("resp1onse $Gson$Types: @Override public String toString() { start return ");
      if (lowerBound != null) {
        return "? super " + typeToString(lowerBound);
      } else if (upperBound == Object.class) {
        return "?";
      } else {
        return "? extends " + typeToString(upperBound);
      }
    }

    private static final long serialVersionUID = 0;
  }
}
