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
public class PaymentConfirmation extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -9060906678221793268L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PaymentConfirmation\",\"namespace\":\"types\",\"fields\":[{\"name\":\"TransactionID\",\"type\":\"string\"},{\"name\":\"CreatedTime\",\"type\":\"string\"},{\"name\":\"OTP\",\"type\":\"int\"}]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PaymentConfirmation> ENCODER =
          new BinaryMessageEncoder<PaymentConfirmation>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PaymentConfirmation> DECODER =
          new BinaryMessageDecoder<PaymentConfirmation>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PaymentConfirmation> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PaymentConfirmation> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PaymentConfirmation> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PaymentConfirmation>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PaymentConfirmation to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PaymentConfirmation from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PaymentConfirmation instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PaymentConfirmation fromByteBuffer(
          java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence TransactionID;
  private java.lang.CharSequence CreatedTime;
  private int OTP;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentConfirmation() {
  }

  /**
   * All-args constructor.
   * @param TransactionID The new value for TransactionID
   * @param CreatedTime The new value for CreatedTime
   * @param OTP The new value for OTP
   */
  public PaymentConfirmation(java.lang.CharSequence TransactionID, java.lang.CharSequence CreatedTime, java.lang.Integer OTP) {
    this.TransactionID = TransactionID;
    this.CreatedTime = CreatedTime;
    this.OTP = OTP;
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
        return TransactionID;
      case 1:
        return CreatedTime;
      case 2:
        return OTP;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0:
        TransactionID = (java.lang.CharSequence) value$;
        break;
      case 1:
        CreatedTime = (java.lang.CharSequence) value$;
        break;
      case 2:
        OTP = (java.lang.Integer) value$;
        break;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'TransactionID' field.
   * @return The value of the 'TransactionID' field.
   */
  public java.lang.CharSequence getTransactionID() {
    return TransactionID;
  }


  /**
   * Sets the value of the 'TransactionID' field.
   * @param value the value to set.
   */
  public void setTransactionID(java.lang.CharSequence value) {
    this.TransactionID = value;
  }

  /**
   * Gets the value of the 'CreatedTime' field.
   * @return The value of the 'CreatedTime' field.
   */
  public java.lang.CharSequence getCreatedTime() {
    return CreatedTime;
  }


  /**
   * Sets the value of the 'CreatedTime' field.
   * @param value the value to set.
   */
  public void setCreatedTime(java.lang.CharSequence value) {
    this.CreatedTime = value;
  }

  /**
   * Gets the value of the 'OTP' field.
   * @return The value of the 'OTP' field.
   */
  public int getOTP() {
    return OTP;
  }


  /**
   * Sets the value of the 'OTP' field.
   * @param value the value to set.
   */
  public void setOTP(int value) {
    this.OTP = value;
  }

  /**
   * Creates a new PaymentConfirmation RecordBuilder.
   * @return A new PaymentConfirmation RecordBuilder
   */
  public static types.PaymentConfirmation.Builder newBuilder() {
    return new types.PaymentConfirmation.Builder();
  }

  /**
   * Creates a new PaymentConfirmation RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentConfirmation RecordBuilder
   */
  public static types.PaymentConfirmation.Builder newBuilder(types.PaymentConfirmation.Builder other) {
    if (other == null) {
      return new types.PaymentConfirmation.Builder();
    } else {
      return new types.PaymentConfirmation.Builder(other);
    }
  }

  /**
   * Creates a new PaymentConfirmation RecordBuilder by copying an existing PaymentConfirmation instance.
   * @param other The existing instance to copy.
   * @return A new PaymentConfirmation RecordBuilder
   */
  public static types.PaymentConfirmation.Builder newBuilder(types.PaymentConfirmation other) {
    if (other == null) {
      return new types.PaymentConfirmation.Builder();
    } else {
      return new types.PaymentConfirmation.Builder(other);
    }
  }

  /**
   * RecordBuilder for PaymentConfirmation instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PaymentConfirmation>
          implements org.apache.avro.data.RecordBuilder<PaymentConfirmation> {

    private java.lang.CharSequence TransactionID;
    private java.lang.CharSequence CreatedTime;
    private int OTP;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(types.PaymentConfirmation.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.TransactionID)) {
        this.TransactionID = data().deepCopy(fields()[0].schema(), other.TransactionID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.CreatedTime)) {
        this.CreatedTime = data().deepCopy(fields()[1].schema(), other.CreatedTime);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.OTP)) {
        this.OTP = data().deepCopy(fields()[2].schema(), other.OTP);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentConfirmation instance
     * @param other The existing instance to copy.
     */
    private Builder(types.PaymentConfirmation other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.TransactionID)) {
        this.TransactionID = data().deepCopy(fields()[0].schema(), other.TransactionID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.CreatedTime)) {
        this.CreatedTime = data().deepCopy(fields()[1].schema(), other.CreatedTime);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.OTP)) {
        this.OTP = data().deepCopy(fields()[2].schema(), other.OTP);
        fieldSetFlags()[2] = true;
      }
    }

    /**
     * Gets the value of the 'TransactionID' field.
     * @return The value.
     */
    public java.lang.CharSequence getTransactionID() {
      return TransactionID;
    }


    /**
     * Sets the value of the 'TransactionID' field.
     * @param value The value of 'TransactionID'.
     * @return This builder.
     */
    public types.PaymentConfirmation.Builder setTransactionID(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.TransactionID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'TransactionID' field has been set.
     * @return True if the 'TransactionID' field has been set, false otherwise.
     */
    public boolean hasTransactionID() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'TransactionID' field.
     * @return This builder.
     */
    public types.PaymentConfirmation.Builder clearTransactionID() {
      TransactionID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
     * Gets the value of the 'CreatedTime' field.
     * @return The value.
     */
    public java.lang.CharSequence getCreatedTime() {
      return CreatedTime;
    }


    /**
     * Sets the value of the 'CreatedTime' field.
     * @param value The value of 'CreatedTime'.
     * @return This builder.
     */
    public types.PaymentConfirmation.Builder setCreatedTime(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.CreatedTime = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
     * Checks whether the 'CreatedTime' field has been set.
     * @return True if the 'CreatedTime' field has been set, false otherwise.
     */
    public boolean hasCreatedTime() {
      return fieldSetFlags()[1];
    }


    /**
     * Clears the value of the 'CreatedTime' field.
     * @return This builder.
     */
    public types.PaymentConfirmation.Builder clearCreatedTime() {
      CreatedTime = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
     * Gets the value of the 'OTP' field.
     * @return The value.
     */
    public int getOTP() {
      return OTP;
    }


    /**
     * Sets the value of the 'OTP' field.
     * @param value The value of 'OTP'.
     * @return This builder.
     */
    public types.PaymentConfirmation.Builder setOTP(int value) {
      validate(fields()[2], value);
      this.OTP = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
     * Checks whether the 'OTP' field has been set.
     * @return True if the 'OTP' field has been set, false otherwise.
     */
    public boolean hasOTP() {
      return fieldSetFlags()[2];
    }


    /**
     * Clears the value of the 'OTP' field.
     * @return This builder.
     */
    public types.PaymentConfirmation.Builder clearOTP() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentConfirmation build() {
      try {
        PaymentConfirmation record = new PaymentConfirmation();
        record.TransactionID = fieldSetFlags()[0] ? this.TransactionID : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.CreatedTime = fieldSetFlags()[1] ? this.CreatedTime : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.OTP = fieldSetFlags()[2] ? this.OTP : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PaymentConfirmation>
          WRITER$ = (org.apache.avro.io.DatumWriter<PaymentConfirmation>) MODEL$.createDatumWriter(SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out)
          throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentConfirmation>
          READER$ = (org.apache.avro.io.DatumReader<PaymentConfirmation>) MODEL$.createDatumReader(SCHEMA$);

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
    out.writeString(this.TransactionID);

    out.writeString(this.CreatedTime);

    out.writeInt(this.OTP);

  }

  @Override
  public void customDecode(org.apache.avro.io.ResolvingDecoder in)
          throws java.io.IOException {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.TransactionID = in.readString(this.TransactionID instanceof Utf8 ? (Utf8) this.TransactionID : null);

      this.CreatedTime = in.readString(this.CreatedTime instanceof Utf8 ? (Utf8) this.CreatedTime : null);

      this.OTP = in.readInt();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
          case 0:
            this.TransactionID = in.readString(this.TransactionID instanceof Utf8 ? (Utf8) this.TransactionID : null);
            break;

          case 1:
            this.CreatedTime = in.readString(this.CreatedTime instanceof Utf8 ? (Utf8) this.CreatedTime : null);
            break;

          case 2:
            this.OTP = in.readInt();
            break;

          default:
            throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










