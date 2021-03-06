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
public class Impression extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8467734962553556986L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Impression\",\"namespace\":\"types\",\"fields\":[{\"name\":\"CampaignerID\",\"type\":\"string\"}]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Impression> ENCODER =
          new BinaryMessageEncoder<Impression>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Impression> DECODER =
          new BinaryMessageDecoder<Impression>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Impression> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Impression> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Impression> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Impression>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Impression to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Impression from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Impression instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Impression fromByteBuffer(
          java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence CampaignerID;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Impression() {
  }

  /**
   * All-args constructor.
   * @param CampaignerID The new value for CampaignerID
   */
  public Impression(java.lang.CharSequence CampaignerID) {
    this.CampaignerID = CampaignerID;
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
        return CampaignerID;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0:
        CampaignerID = (java.lang.CharSequence) value$;
        break;
      default:
        throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'CampaignerID' field.
   * @return The value of the 'CampaignerID' field.
   */
  public java.lang.CharSequence getCampaignerID() {
    return CampaignerID;
  }


  /**
   * Sets the value of the 'CampaignerID' field.
   * @param value the value to set.
   */
  public void setCampaignerID(java.lang.CharSequence value) {
    this.CampaignerID = value;
  }

  /**
   * Creates a new Impression RecordBuilder.
   * @return A new Impression RecordBuilder
   */
  public static types.Impression.Builder newBuilder() {
    return new types.Impression.Builder();
  }

  /**
   * Creates a new Impression RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Impression RecordBuilder
   */
  public static types.Impression.Builder newBuilder(types.Impression.Builder other) {
    if (other == null) {
      return new types.Impression.Builder();
    } else {
      return new types.Impression.Builder(other);
    }
  }

  /**
   * Creates a new Impression RecordBuilder by copying an existing Impression instance.
   * @param other The existing instance to copy.
   * @return A new Impression RecordBuilder
   */
  public static types.Impression.Builder newBuilder(types.Impression other) {
    if (other == null) {
      return new types.Impression.Builder();
    } else {
      return new types.Impression.Builder(other);
    }
  }

  /**
   * RecordBuilder for Impression instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Impression>
          implements org.apache.avro.data.RecordBuilder<Impression> {

    private java.lang.CharSequence CampaignerID;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(types.Impression.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.CampaignerID)) {
        this.CampaignerID = data().deepCopy(fields()[0].schema(), other.CampaignerID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing Impression instance
     * @param other The existing instance to copy.
     */
    private Builder(types.Impression other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.CampaignerID)) {
        this.CampaignerID = data().deepCopy(fields()[0].schema(), other.CampaignerID);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Gets the value of the 'CampaignerID' field.
     * @return The value.
     */
    public java.lang.CharSequence getCampaignerID() {
      return CampaignerID;
    }


    /**
     * Sets the value of the 'CampaignerID' field.
     * @param value The value of 'CampaignerID'.
     * @return This builder.
     */
    public types.Impression.Builder setCampaignerID(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.CampaignerID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
     * Checks whether the 'CampaignerID' field has been set.
     * @return True if the 'CampaignerID' field has been set, false otherwise.
     */
    public boolean hasCampaignerID() {
      return fieldSetFlags()[0];
    }


    /**
     * Clears the value of the 'CampaignerID' field.
     * @return This builder.
     */
    public types.Impression.Builder clearCampaignerID() {
      CampaignerID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Impression build() {
      try {
        Impression record = new Impression();
        record.CampaignerID = fieldSetFlags()[0] ? this.CampaignerID : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Impression>
          WRITER$ = (org.apache.avro.io.DatumWriter<Impression>) MODEL$.createDatumWriter(SCHEMA$);

  @Override
  public void writeExternal(java.io.ObjectOutput out)
          throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Impression>
          READER$ = (org.apache.avro.io.DatumReader<Impression>) MODEL$.createDatumReader(SCHEMA$);

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
    out.writeString(this.CampaignerID);

  }

  @Override
  public void customDecode(org.apache.avro.io.ResolvingDecoder in)
          throws java.io.IOException {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.CampaignerID = in.readString(this.CampaignerID instanceof Utf8 ? (Utf8) this.CampaignerID : null);

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
          case 0:
            this.CampaignerID = in.readString(this.CampaignerID instanceof Utf8 ? (Utf8) this.CampaignerID : null);
            break;

          default:
            throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










