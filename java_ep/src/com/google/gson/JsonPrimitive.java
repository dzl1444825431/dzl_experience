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

package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;

/**
 * A class representing a Json primitive value. A primitive value
 * is either a String, a Java primitive, or a Java primitive
 * wrapper type.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 */
public final class JsonPrimitive extends JsonElement {

  private static final Class<?>[] PRIMITIVE_TYPES = { int.class, long.class, short.class,
      float.class, double.class, byte.class, boolean.class, char.class, Integer.class, Long.class,
      Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class };

  private Object value;

  /**
   * Create a primitive containing a boolean value.
   *
   * @param bool the value to create the primitive with.
   */
  public JsonPrimitive(Boolean bool) {
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(Boolean bool) { start constructor ");
    setValue(bool);
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(Boolean bool) { end constructor ");
  }

  /**
   * Create a primitive containing a {@link Number}.
   *
   * @param number the value to create the primitive with.
   */
  public JsonPrimitive(Number number) {
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(Number number) { start constructor ");
    setValue(number);
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(Number number) { end constructor ");
  }

  /**
   * Create a primitive containing a String value.
   *
   * @param string the value to create the primitive with.
   */
  public JsonPrimitive(String string) {
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(String string) { start constructor ");
    setValue(string);
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(String string) { end constructor ");
  }

  /**
   * Create a primitive containing a character. The character is turned into a one character String
   * since Json only supports String.
   *
   * @param c the value to create the primitive with.
   */
  public JsonPrimitive(Character c) {
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(Character c) { start constructor ");
    setValue(c);
		System.out.println("resp1onse JsonPrimitive: public JsonPrimitive(Character c) { end constructor ");
  }

  /**
   * Create a primitive using the specified Object. It must be an instance of {@link Number}, a
   * Java primitive type, or a String.
   *
   * @param primitive the value to create the primitive with.
   */
  JsonPrimitive(Object primitive) {
    setValue(primitive);
  }

  @Override
  JsonPrimitive deepCopy() {
    return this;
  }

  void setValue(Object primitive) {
		System.out.println("resp1onse JsonPrimitive: void setValue(Object primitive) { start void ");
    if (primitive instanceof Character) {
      // convert characters to strings since in JSON, characters are represented as a single
      // character string
      char c = ((Character) primitive).charValue();
      this.value = String.valueOf(c);
    } else {
      $Gson$Preconditions.checkArgument(primitive instanceof Number
              || isPrimitiveOrString(primitive));
      this.value = primitive;
    }
		System.out.println("resp1onse JsonPrimitive: void setValue(Object primitive) { end void ");
  }

  /**
   * Check whether this primitive contains a boolean value.
   *
   * @return true if this primitive contains a boolean value, false otherwise.
   */
  public boolean isBoolean() {
		System.out.println("resp1onse JsonPrimitive: public boolean isBoolean() { start return ");
		System.out.println("resp1onse JsonPrimitive: public boolean isBoolean() { end return ");
    return value instanceof Boolean;
  }

  /**
   * convenience method to get this element as a {@link Boolean}.
   *
   * @return get this element as a {@link Boolean}.
   */
  @Override
  Boolean getAsBooleanWrapper() {
    return (Boolean) value;
  }

  /**
   * convenience method to get this element as a boolean value.
   *
   * @return get this element as a primitive boolean value.
   */
  @Override
  public boolean getAsBoolean() {
		System.out.println("resp1onse JsonPrimitive: public boolean getAsBoolean() { start return ");
    if (isBoolean()) {
      return getAsBooleanWrapper().booleanValue();
    } else {
      // Check to see if the value as a String is "true" in any case.
      return Boolean.parseBoolean(getAsString());
    }
  }

  /**
   * Check whether this primitive contains a Number.
   *
   * @return true if this primitive contains a Number, false otherwise.
   */
  public boolean isNumber() {
		System.out.println("resp1onse JsonPrimitive: public boolean isNumber() { start return ");
		System.out.println("resp1onse JsonPrimitive: public boolean isNumber() { end return ");
    return value instanceof Number;
  }

  /**
   * convenience method to get this element as a Number.
   *
   * @return get this element as a Number.
   * @throws NumberFormatException if the value contained is not a valid Number.
   */
  @Override
  public Number getAsNumber() {
		System.out.println("resp1onse JsonPrimitive: public Number getAsNumber() { start return ");
		System.out.println("resp1onse JsonPrimitive: public Number getAsNumber() { end return ");
    return value instanceof String ? new LazilyParsedNumber((String) value) : (Number) value;
  }

  /**
   * Check whether this primitive contains a String value.
   *
   * @return true if this primitive contains a String value, false otherwise.
   */
  public boolean isString() {
		System.out.println("resp1onse JsonPrimitive: public boolean isString() { start return ");
		System.out.println("resp1onse JsonPrimitive: public boolean isString() { end return ");
    return value instanceof String;
  }

  /**
   * convenience method to get this element as a String.
   *
   * @return get this element as a String.
   */
  @Override
  public String getAsString() {
		System.out.println("resp1onse JsonPrimitive: public String getAsString() { start return ");
    if (isNumber()) {
      return getAsNumber().toString();
    } else if (isBoolean()) {
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }

  /**
   * convenience method to get this element as a primitive double.
   *
   * @return get this element as a primitive double.
   * @throws NumberFormatException if the value contained is not a valid double.
   */
  @Override
  public double getAsDouble() {
		System.out.println("resp1onse JsonPrimitive: public double getAsDouble() { start return ");
		System.out.println("resp1onse JsonPrimitive: public double getAsDouble() { end return ");
    return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
  }

  /**
   * convenience method to get this element as a {@link BigDecimal}.
   *
   * @return get this element as a {@link BigDecimal}.
   * @throws NumberFormatException if the value contained is not a valid {@link BigDecimal}.
   */
  @Override
  public BigDecimal getAsBigDecimal() {
		System.out.println("resp1onse JsonPrimitive: public BigDecimal getAsBigDecimal() { start return ");
		System.out.println("resp1onse JsonPrimitive: public BigDecimal getAsBigDecimal() { end return ");
    return value instanceof BigDecimal ? (BigDecimal) value : new BigDecimal(value.toString());
  }

  /**
   * convenience method to get this element as a {@link BigInteger}.
   *
   * @return get this element as a {@link BigInteger}.
   * @throws NumberFormatException if the value contained is not a valid {@link BigInteger}.
   */
  @Override
  public BigInteger getAsBigInteger() {
		System.out.println("resp1onse JsonPrimitive: public BigInteger getAsBigInteger() { start return ");
		System.out.println("resp1onse JsonPrimitive: public BigInteger getAsBigInteger() { end return ");
    return value instanceof BigInteger ?
        (BigInteger) value : new BigInteger(value.toString());
  }

  /**
   * convenience method to get this element as a float.
   *
   * @return get this element as a float.
   * @throws NumberFormatException if the value contained is not a valid float.
   */
  @Override
  public float getAsFloat() {
		System.out.println("resp1onse JsonPrimitive: public float getAsFloat() { start return ");
		System.out.println("resp1onse JsonPrimitive: public float getAsFloat() { end return ");
    return isNumber() ? getAsNumber().floatValue() : Float.parseFloat(getAsString());
  }

  /**
   * convenience method to get this element as a primitive long.
   *
   * @return get this element as a primitive long.
   * @throws NumberFormatException if the value contained is not a valid long.
   */
  @Override
  public long getAsLong() {
		System.out.println("resp1onse JsonPrimitive: public long getAsLong() { start return ");
		System.out.println("resp1onse JsonPrimitive: public long getAsLong() { end return ");
    return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
  }

  /**
   * convenience method to get this element as a primitive short.
   *
   * @return get this element as a primitive short.
   * @throws NumberFormatException if the value contained is not a valid short value.
   */
  @Override
  public short getAsShort() {
		System.out.println("resp1onse JsonPrimitive: public short getAsShort() { start return ");
		System.out.println("resp1onse JsonPrimitive: public short getAsShort() { end return ");
    return isNumber() ? getAsNumber().shortValue() : Short.parseShort(getAsString());
  }

 /**
  * convenience method to get this element as a primitive integer.
  *
  * @return get this element as a primitive integer.
  * @throws NumberFormatException if the value contained is not a valid integer.
  */
  @Override
  public int getAsInt() {
		System.out.println("resp1onse JsonPrimitive: public int getAsInt() { start return ");
		System.out.println("resp1onse JsonPrimitive: public int getAsInt() { end return ");
    return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
  }

  @Override
  public byte getAsByte() {
		System.out.println("resp1onse JsonPrimitive: public byte getAsByte() { start return ");
		System.out.println("resp1onse JsonPrimitive: public byte getAsByte() { end return ");
    return isNumber() ? getAsNumber().byteValue() : Byte.parseByte(getAsString());
  }

  @Override
  public char getAsCharacter() {
		System.out.println("resp1onse JsonPrimitive: public char getAsCharacter() { start return ");
		System.out.println("resp1onse JsonPrimitive: public char getAsCharacter() { end return ");
    return getAsString().charAt(0);
  }

  private static boolean isPrimitiveOrString(Object target) {
		System.out.println("resp1onse JsonPrimitive: private static boolean isPrimitiveOrString(Object target) { start return ");
    if (target instanceof String) {
      return true;
    }

    Class<?> classOfPrimitive = target.getClass();
    for (Class<?> standardPrimitive : PRIMITIVE_TYPES) {
      if (standardPrimitive.isAssignableFrom(classOfPrimitive)) {
        return true;
      }
    }
		System.out.println("resp1onse JsonPrimitive: private static boolean isPrimitiveOrString(Object target) { end return  =1");
    return false;
  }

  @Override
  public int hashCode() {
		System.out.println("resp1onse JsonPrimitive: public int hashCode() { start return ");
    if (value == null) {
      return 31;
    }
    // Using recommended hashing algorithm from Effective Java for longs and doubles
    if (isIntegral(this)) {
      long value = getAsNumber().longValue();
		System.out.println("resp1onse JsonPrimitive: public int hashCode() { end return if ");
      return (int) (value ^ (value >>> 32));
    }
    if (value instanceof Number) {
      long value = Double.doubleToLongBits(getAsNumber().doubleValue());
		System.out.println("resp1onse JsonPrimitive: public int hashCode() { end return if ");
      return (int) (value ^ (value >>> 32));
    }
		System.out.println("resp1onse JsonPrimitive: public int hashCode() { end return  =1");
    return value.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
		System.out.println("resp1onse JsonPrimitive: public boolean equals(Object obj) { start return ");
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    JsonPrimitive other = (JsonPrimitive)obj;
    if (value == null) {
      return other.value == null;
    }
    if (isIntegral(this) && isIntegral(other)) {
      return getAsNumber().longValue() == other.getAsNumber().longValue();
    }
    if (value instanceof Number && other.value instanceof Number) {
      double a = getAsNumber().doubleValue();
      // Java standard types other than double return true for two NaN. So, need
      // special handling for double.
      double b = other.getAsNumber().doubleValue();
		System.out.println("resp1onse JsonPrimitive: public boolean equals(Object obj) { end return if ");
      return a == b || (Double.isNaN(a) && Double.isNaN(b));
    }
		System.out.println("resp1onse JsonPrimitive: public boolean equals(Object obj) { end return  =1");
    return value.equals(other.value);
  }

  /**
   * Returns true if the specified number is an integral type
   * (Long, Integer, Short, Byte, BigInteger)
   */
  private static boolean isIntegral(JsonPrimitive primitive) {
		System.out.println("resp1onse JsonPrimitive: private static boolean isIntegral(JsonPrimitive primitive) { start return ");
    if (primitive.value instanceof Number) {
      Number number = (Number) primitive.value;
		System.out.println("resp1onse JsonPrimitive: private static boolean isIntegral(JsonPrimitive primitive) { end return if ");
      return number instanceof BigInteger || number instanceof Long || number instanceof Integer
          || number instanceof Short || number instanceof Byte;
    }
		System.out.println("resp1onse JsonPrimitive: private static boolean isIntegral(JsonPrimitive primitive) { end return  =1");
    return false;
  }
}
