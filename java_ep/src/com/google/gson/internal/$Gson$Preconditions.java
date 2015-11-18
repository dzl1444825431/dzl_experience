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

/**
 * A simple utility class used to check method Preconditions.
 *
 * <pre>
 * public long divideBy(long value) {
 *   Preconditions.checkArgument(value != 0);
 *   return this.value / value;
 * }
 * </pre>
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 */
public final class $Gson$Preconditions {
  private $Gson$Preconditions() {
		System.out.println("resp1onse $Gson$Preconditions: private $Gson$Preconditions() { start constructor ");
		System.out.println("resp1onse $Gson$Preconditions: private $Gson$Preconditions() { end constructor ");
    throw new UnsupportedOperationException();
  }

  public static <T> T checkNotNull(T obj) {
		System.out.println("resp1onse $Gson$Preconditions: public static <T> T checkNotNull(T obj) { start return ");
    if (obj == null) {
      throw new NullPointerException();
    }
		System.out.println("resp1onse $Gson$Preconditions: public static <T> T checkNotNull(T obj) { end return  =1");
    return obj;
  }

  public static void checkArgument(boolean condition) {
		System.out.println("resp1onse $Gson$Preconditions: public static void checkArgument(boolean condition) { start void ");
    if (!condition) {
      throw new IllegalArgumentException();
    }
		System.out.println("resp1onse $Gson$Preconditions: public static void checkArgument(boolean condition) { end void ");
  }
}
