/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package types;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class Click extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5195486665033118508L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Click\",\"namespace\":\"types\",\"fields\":[{\"name\":\"UserID\",\"type\":\"string\"},{\"name\":\"CreatedTime\",\"type\":\"string\"},{\"name\":\"CurrentLink\",\"type\":\"string\"},{\"name\":\"NextLink\",\"type\":\"string\"}]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Click> ENCODER =
          new BinaryMessageEncoder<Click>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Click> DECODER =
          new BinaryMessageDecoder<Click>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   *
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Click> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   *
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Click> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   *
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Click> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Click>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Click to a ByteBuffer.
   *
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Click from a ByteBuffer.
   *
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Click instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Click fromByteBuffer(
          java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence UserID;
  private java.lang.CharSequence CreatedTime;
  private java.lang.CharSequence CurrentLink;
  private java.lang.CharSequence NextLink;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Click() {
  }

  /**
   * All-args constructor.
   *
   * @param UserID      The new value for UserID
   * @param CreatedTime The new value for CreatedTime
   * @param CurrentLink The new value for CurrentLink
   * @param NextLink    The new value for NextLink
   */
  public Click(java.lang.CharSequence UserID, java.lang.CharSequence CreatedTime, java.lang.CharSequence CurrentLink, java.lang.CharSequence NextLink) {
    this.UserID = UserID;
    this.CreatedTime = CreatedTime;
    this.CurrentLink = CurrentLink;
    this.NextLink = NextLink;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() {
    return MODEL$;
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }

  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0:
        return UserID;
      case 1:
        return CreatedTime;
      case 2:
        return CurrentLink;
      case 3:
        return NextLink;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0:
        UserID = (java.lang.CharSequence) value$;
        break;
      case 1:
        CreatedTime = (java.lang.CharSequence) value$;
        break;
      case 2:
        CurrentLink = (java.lang.CharSequence) value$;
        break;
      case 3:
        NextLink = (java.lang.CharSequence) value$;
        break;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'UserID' field.
   *
   * @return The value of the 'UserID' field.
   */
  public java.lang.CharSequence getUserID() {
    return UserID;
  }


  /**
   * Sets the value of the 'UserID' field.
   *
   * @param value the value to set.
   */
  public void setUserID(java.lang.CharSequence value) {
    this.UserID = value;
  }

  /**
   * Gets the value of the 'CreatedTime' field.
   *
   * @return The value of the 'CreatedTime' field.
   */
  public java.lang.CharSequence getCreatedTime() {
    return CreatedTime;
  }


  /**
   * Sets the value of the 'CreatedTime' field.
   *
   * @param value the value to set.
   */
  public void setCreatedTime(java.lang.CharSequence value) {
    this.CreatedTime = value;
  }

  /**
   * Gets the value of the 'CurrentLink' field.
   *
   * @return The value of the 'CurrentLink' field.
   */
  public java.lang.CharSequence getCurrentLink() {
    return CurrentLink;
  }


  /**
   * Sets the value of the 'CurrentLink' field.
   *
   * @param value the value to set.
   */
  public void setCurrentLink(java.lang.CharSequence value) {
    this.CurrentLink = value;
  }

  /**
   * Gets the value of the 'NextLink' field.
   *
   * @return The value of the 'NextLink' field.
   */
  public java.lang.CharSequence getNextLink() {
    return NextLink;
  }


  /**
   * Sets the value of the 'NextLink' field.
   *
   * @param value the value to set.
   */
  public void setNextLink(java.lang.CharSequence value) {
    this.NextLink = value;
  }

  /**
   * Creates a new Click RecordBuilder.
   *
   * @return A new Click RecordBuilder
   */
  public static types.Click.Builder newBuilder() {
    return new types.Click.Builder();
  }

  /**
   * Creates a new Click RecordBuilder by copying an existing Builder.
   *
   * @param other The existing builder to copy.
   * @return A new Click RecordBuilder
   */
  public static types.Click.Builder newBuilder(types.Click.Builder other) {
    if (other == null) {
      return new types.Click.Builder();
    } else {
      return new types.Click.Builder(other);
    }
  }

  /**
   * Creates a new Click RecordBuilder by copying an existing Click instance.
   *
   * @param other The existing instance to copy.
   * @return A new Click RecordBuilder
   */
  public static types.Click.Builder newBuilder(types.Click other) {
    if (other == null) {
      return new types.Click.Builder();
    } else {
      return new types.Click.Builder(other);
    }
  }

  /**
   * RecordBuilder for Click instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Click>
          implements org.apache.avro.data.RecordBuilder<Click> {

    private java.lang.CharSequence UserID;
    private java.lang.CharSequence CreatedTime;
    private java.lang.CharSequence CurrentLink;
    private java.lang.CharSequence NextLink;

    /**
     * Creates a new Builder
     */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     *
     * @param other The existing Builder to copy.
     */
    private Builder(types.Click.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.UserID)) {
        this.UserID = data().deepCopy(fields()[0].schema(), other.UserID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.CreatedTime)) {
        this.CreatedTime = data().deepCopy(fields()[1].schema(), other.CreatedTime);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.CurrentLink)) {
        this.CurrentLink = data().deepCopy(fields()[2].schema(), other.CurrentLink);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.NextLink)) {
        this.NextLink = data().deepCopy(fields()[3].schema(), other.NextLink);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing Click instance
     *
     * @param other The existing instance to copy.
     */
    private Builder(types.Click other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.UserID)) {
        this.UserID = data().deepCopy(fields()[0].schema(), other.UserID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.CreatedTime)) {
        this.CreatedTime = data().deepCopy(fields()[1].schema(), other.CreatedTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.CurrentLink)) {
        this.CurrentLink = data().deepCopy(fields()[2].schema(), other.CurrentLink);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.NextLink)) {
        this.NextLink = data().deepCopy(fields()[3].schema(), other.NextLink);
        fieldSetFlags()[3] = true;
      }
    }

    /**
     * Gets the value of the 'UserID' field.
     *
     * @return The value.
     */
    public java.lang.CharSequence getUserID() {
      return UserID;
    }


    /**
     * Sets the value of the 'UserID' field.
     *
     * @param value The value of 'UserID'.
     * @return This builder.
     */
    public types.Click.Builder setUserID(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.UserID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'UserID' field has been set.
     *
     * @return True if the 'UserID' field has been set, false otherwise.
     */
    public boolean hasUserID() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'UserID' field.
     *
     * @return This builder.
     */
    public types.Click.Builder clearUserID() {
      UserID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'CreatedTime' field.
     *
     * @return The value.
     */
    public java.lang.CharSequence getCreatedTime() {
      return CreatedTime;
    }


    /**
     * Sets the value of the 'CreatedTime' field.
     *
     * @param value The value of 'CreatedTime'.
     * @return This builder.
     */
    public types.Click.Builder setCreatedTime(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.CreatedTime = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
     * Checks whether the 'CreatedTime' field has been set.
     *
     * @return True if the 'CreatedTime' field has been set, false otherwise.
     */
    public boolean hasCreatedTime() {
      return fieldSetFlags()[1];
    }


    /**
     * Clears the value of the 'CreatedTime' field.
     *
     * @return This builder.
     */
    public types.Click.Builder clearCreatedTime() {
      CreatedTime = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'CurrentLink' field.
     *
     * @return The value.
     */
    public java.lang.CharSequence getCurrentLink() {
      return CurrentLink;
    }


    /**
     * Sets the value of the 'CurrentLink' field.
     *
     * @param value The value of 'CurrentLink'.
     * @return This builder.
     */
    public types.Click.Builder setCurrentLink(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.CurrentLink = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'CurrentLink' field has been set.
     *
     * @return True if the 'CurrentLink' field has been set, false otherwise.
     */
    public boolean hasCurrentLink() {
      return fieldSetFlags()[2];
    }


    /**
     * Clears the value of the 'CurrentLink' field.
     *
     * @return This builder.
     */
    public types.Click.Builder clearCurrentLink() {
      CurrentLink = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
     * Gets the value of the 'NextLink' field.
     *
     * @return The value.
     */
    public java.lang.CharSequence getNextLink() {
      return NextLink;
    }


    /**
     * Sets the value of the 'NextLink' field.
     *
     * @param value The value of 'NextLink'.
     * @return This builder.
     */
    public types.Click.Builder setNextLink(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.NextLink = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
     * Checks whether the 'NextLink' field has been set.
     *
     * @return True if the 'NextLink' field has been set, false otherwise.
     */
    public boolean hasNextLink() {
      return fieldSetFlags()[3];
    }


    /**
     * Clears the value of the 'NextLink' field.
     *
     * @return This builder.
     */
    public types.Click.Builder clearNextLink() {
      NextLink = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Click build() {
      try {
        Click record = new Click();
        record.UserID = fieldSetFlags()[0] ? this.UserID : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.CreatedTime = fieldSetFlags()[1] ? this.CreatedTime : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.CurrentLink = fieldSetFlags()[2] ? this.CurrentLink : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.NextLink = fieldSetFlags()[3] ? this.NextLink : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Click>
          WRITER$ = (org.apache.avro.io.DatumWriter<Click>) MODEL$.createDatumWriter(SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out)
          throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Click>
          READER$ = (org.apache.avro.io.DatumReader<Click>) MODEL$.createDatumReader(SCHEMA$);

  @Override
  public void readExternal(java.io.ObjectInput in)
          throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override
  protected boolean hasCustomCoders() {
    return true;
  }

  @Override
  public void customEncode(org.apache.avro.io.Encoder out)
          throws java.io.IOException {
    out.writeString(this.UserID);

    out.writeString(this.CreatedTime);

    out.writeString(this.CurrentLink);

    out.writeString(this.NextLink);

  }

  @Override
  public void customDecode(org.apache.avro.io.ResolvingDecoder in)
          throws java.io.IOException {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.UserID = in.readString(this.UserID instanceof Utf8 ? (Utf8) this.UserID : null);

      this.CreatedTime = in.readString(this.CreatedTime instanceof Utf8 ? (Utf8) this.CreatedTime : null);

      this.CurrentLink = in.readString(this.CurrentLink instanceof Utf8 ? (Utf8) this.CurrentLink : null);

      this.NextLink = in.readString(this.NextLink instanceof Utf8 ? (Utf8) this.NextLink : null);

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
          case 0:
            this.UserID = in.readString(this.UserID instanceof Utf8 ? (Utf8) this.UserID : null);
            break;

          case 1:
            this.CreatedTime = in.readString(this.CreatedTime instanceof Utf8 ? (Utf8) this.CreatedTime : null);
            break;

          case 2:
            this.CurrentLink = in.readString(this.CurrentLink instanceof Utf8 ? (Utf8) this.CurrentLink : null);
            break;

          case 3:
            this.NextLink = in.readString(this.NextLink instanceof Utf8 ? (Utf8) this.NextLink : null);
            break;

          default:
            throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










