/*
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

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class selects which fields and types to omit. It is configurable,
 * supporting version attributes {@link Since} and {@link Until}, modifiers,
 * synthetic fields, anonymous and local classes, inner classes, and fields with
 * the {@link Expose} annotation.
 *
 * <p>This class is a type adapter factory; types that are excluded will be
 * adapted to null. It may delegate to another type adapter if only one
 * direction is excluded.
 *
 * @author Joel Leitch
 * @author Jesse Wilson
 */
public final class Excluder implements TypeAdapterFactory, Cloneable {
  private static final double IGNORE_VERSIONS = -1.0d;
  public static final Excluder DEFAULT = new Excluder();

  private double version = IGNORE_VERSIONS;
  private int modifiers = Modifier.TRANSIENT | Modifier.STATIC;
  private boolean serializeInnerClasses = true;
  private boolean requireExpose;
  private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
  private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

  @Override protected Excluder clone() {
		System.out.println("resp1onse Excluder: @Override protected Excluder clone() { start return ");
    try {
      return (Excluder) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public Excluder withVersion(double ignoreVersionsAfter) {
		System.out.println("resp1onse Excluder: public Excluder withVersion(double ignoreVersionsAfter) { start return ");
    Excluder result = clone();
    result.version = ignoreVersionsAfter;
		System.out.println("resp1onse Excluder: public Excluder withVersion(double ignoreVersionsAfter) { end return  =1");
    return result;
  }

  public Excluder withModifiers(int... modifiers) {
		System.out.println("resp1onse Excluder: public Excluder withModifiers(int... modifiers) { start return ");
    Excluder result = clone();
    result.modifiers = 0;
    for (int modifier : modifiers) {
      result.modifiers |= modifier;
    }
		System.out.println("resp1onse Excluder: public Excluder withModifiers(int... modifiers) { end return  =1");
    return result;
  }

  public Excluder disableInnerClassSerialization() {
		System.out.println("resp1onse Excluder: public Excluder disableInnerClassSerialization() { start return ");
    Excluder result = clone();
    result.serializeInnerClasses = false;
		System.out.println("resp1onse Excluder: public Excluder disableInnerClassSerialization() { end return  =1");
    return result;
  }

  public Excluder excludeFieldsWithoutExposeAnnotation() {
		System.out.println("resp1onse Excluder: public Excluder excludeFieldsWithoutExposeAnnotation() { start return ");
    Excluder result = clone();
    result.requireExpose = true;
		System.out.println("resp1onse Excluder: public Excluder excludeFieldsWithoutExposeAnnotation() { end return  =1");
    return result;
  }

  public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy,
      boolean serialization, boolean deserialization) {
    Excluder result = clone();
    if (serialization) {
      result.serializationStrategies = new ArrayList<ExclusionStrategy>(serializationStrategies);
      result.serializationStrategies.add(exclusionStrategy);
    }
    if (deserialization) {
      result.deserializationStrategies
          = new ArrayList<ExclusionStrategy>(deserializationStrategies);
      result.deserializationStrategies.add(exclusionStrategy);
    }
    return result;
  }

  public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
		System.out.println("resp1onse Excluder: public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) { start return ");
    Class<?> rawType = type.getRawType();
    final boolean skipSerialize = excludeClass(rawType, true);
    final boolean skipDeserialize = excludeClass(rawType, false);

    if (!skipSerialize && !skipDeserialize) {
      return null;
    }

		System.out.println("resp1onse Excluder: public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) { end return if ");
    return new TypeAdapter<T>() {
      /** The delegate is lazily created because it may not be needed, and creating it may fail. */
      private TypeAdapter<T> delegate;

      @Override public T read(JsonReader in) throws IOException {
        if (skipDeserialize) {
          in.skipValue();
		System.out.println("resp1onse Excluder: public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) { end return if ");
          return null;
        }
		System.out.println("resp1onse Excluder: public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) { end return if ");
        return delegate().read(in);
      }

      @Override public void write(JsonWriter out, T value) throws IOException {
        if (skipSerialize) {
          out.nullValue();
		System.out.println("resp1onse Excluder: public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) { end return if ");
          return;
        }
        delegate().write(out, value);
      }

      private TypeAdapter<T> delegate() {
		System.out.println("resp1onse Excluder: private TypeAdapter<T> delegate() { start internal 3");
        TypeAdapter<T> d = delegate;
		System.out.println("resp1onse Excluder: public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) { end return if ");
		System.out.println("resp1onse Excluder: private TypeAdapter<T> delegate() { end internal 3");
        return d != null
            ? d
            : (delegate = gson.getDelegateAdapter(Excluder.this, type));
      }
    };
  }

  public boolean excludeField(Field field, boolean serialize) {
		System.out.println("resp1onse Excluder: public boolean excludeField(Field field, boolean serialize) { start return ");
    if ((modifiers & field.getModifiers()) != 0) {
      return true;
    }

    if (version != Excluder.IGNORE_VERSIONS
        && !isValidVersion(field.getAnnotation(Since.class), field.getAnnotation(Until.class))) {
      return true;
    }

    if (field.isSynthetic()) {
      return true;
    }

    if (requireExpose) {
      Expose annotation = field.getAnnotation(Expose.class);
      if (annotation == null || (serialize ? !annotation.serialize() : !annotation.deserialize())) {
        return true;
      }
    }

    if (!serializeInnerClasses && isInnerClass(field.getType())) {
      return true;
    }

    if (isAnonymousOrLocal(field.getType())) {
      return true;
    }

    List<ExclusionStrategy> list = serialize ? serializationStrategies : deserializationStrategies;
    if (!list.isEmpty()) {
      FieldAttributes fieldAttributes = new FieldAttributes(field);
      for (ExclusionStrategy exclusionStrategy : list) {
        if (exclusionStrategy.shouldSkipField(fieldAttributes)) {
          return true;
        }
      }
    }

		System.out.println("resp1onse Excluder: public boolean excludeField(Field field, boolean serialize) { end return  =1");
    return false;
  }

  public boolean excludeClass(Class<?> clazz, boolean serialize) {
		System.out.println("resp1onse Excluder: public boolean excludeClass(Class<?> clazz, boolean serialize) { start return ");
    if (version != Excluder.IGNORE_VERSIONS
        && !isValidVersion(clazz.getAnnotation(Since.class), clazz.getAnnotation(Until.class))) {
      return true;
    }

    if (!serializeInnerClasses && isInnerClass(clazz)) {
      return true;
    }

    if (isAnonymousOrLocal(clazz)) {
      return true;
    }

    List<ExclusionStrategy> list = serialize ? serializationStrategies : deserializationStrategies;
    for (ExclusionStrategy exclusionStrategy : list) {
      if (exclusionStrategy.shouldSkipClass(clazz)) {
        return true;
      }
    }

		System.out.println("resp1onse Excluder: public boolean excludeClass(Class<?> clazz, boolean serialize) { end return  =1");
    return false;
  }

  private boolean isAnonymousOrLocal(Class<?> clazz) {
		System.out.println("resp1onse Excluder: private boolean isAnonymousOrLocal(Class<?> clazz) { start return ");
		System.out.println("resp1onse Excluder: private boolean isAnonymousOrLocal(Class<?> clazz) { end return ");
    return !Enum.class.isAssignableFrom(clazz)
        && (clazz.isAnonymousClass() || clazz.isLocalClass());
  }

  private boolean isInnerClass(Class<?> clazz) {
		System.out.println("resp1onse Excluder: private boolean isInnerClass(Class<?> clazz) { start return ");
		System.out.println("resp1onse Excluder: private boolean isInnerClass(Class<?> clazz) { end return ");
    return clazz.isMemberClass() && !isStatic(clazz);
  }

  private boolean isStatic(Class<?> clazz) {
		System.out.println("resp1onse Excluder: private boolean isStatic(Class<?> clazz) { start return ");
		System.out.println("resp1onse Excluder: private boolean isStatic(Class<?> clazz) { end return ");
    return (clazz.getModifiers() & Modifier.STATIC) != 0;
  }

  private boolean isValidVersion(Since since, Until until) {
		System.out.println("resp1onse Excluder: private boolean isValidVersion(Since since, Until until) { start return ");
		System.out.println("resp1onse Excluder: private boolean isValidVersion(Since since, Until until) { end return ");
    return isValidSince(since) && isValidUntil(until);
  }

  private boolean isValidSince(Since annotation) {
		System.out.println("resp1onse Excluder: private boolean isValidSince(Since annotation) { start return ");
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion > version) {
        return false;
      }
    }
		System.out.println("resp1onse Excluder: private boolean isValidSince(Since annotation) { end return  =1");
    return true;
  }

  private boolean isValidUntil(Until annotation) {
		System.out.println("resp1onse Excluder: private boolean isValidUntil(Until annotation) { start return ");
    if (annotation != null) {
      double annotationVersion = annotation.value();
      if (annotationVersion <= version) {
        return false;
      }
    }
		System.out.println("resp1onse Excluder: private boolean isValidUntil(Until annotation) { end return  =1");
    return true;
  }
}
