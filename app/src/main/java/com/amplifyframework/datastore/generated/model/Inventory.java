package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Inventory type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Inventories")
public final class Inventory implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField SPECIES = field("species");
  public static final QueryField QUANTITY = field("quantity");
  public static final QueryField AVAILABLE_QUANTITY = field("availableQuantity");
  public static final QueryField PRICE = field("price");
  public static final QueryField CATCH_LOCATION = field("catchLocation");
  public static final QueryField SELL_LOCATION = field("sellLocation");
  public static final QueryField CATCH_TIME = field("catchTime");
  public static final QueryField SELL_TIME = field("sellTime");
  public static final QueryField SIZE = field("size");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int", isRequired = true) Integer species;
  private final @ModelField(targetType="Int", isRequired = true) Integer quantity;
  private final @ModelField(targetType="Int", isRequired = true) Integer availableQuantity;
  private final @ModelField(targetType="Int", isRequired = true) Integer price;
  private final @ModelField(targetType="String", isRequired = true) String catchLocation;
  private final @ModelField(targetType="String", isRequired = true) String sellLocation;
  private final @ModelField(targetType="String", isRequired = true) String catchTime;
  private final @ModelField(targetType="String", isRequired = true) String sellTime;
  private final @ModelField(targetType="CatchSize", isRequired = true) CatchSize size;
  public String getId() {
      return id;
  }
  
  public Integer getSpecies() {
      return species;
  }
  
  public Integer getQuantity() {
      return quantity;
  }
  
  public Integer getAvailableQuantity() {
      return availableQuantity;
  }
  
  public Integer getPrice() {
      return price;
  }
  
  public String getCatchLocation() {
      return catchLocation;
  }
  
  public String getSellLocation() {
      return sellLocation;
  }
  
  public String getCatchTime() {
      return catchTime;
  }
  
  public String getSellTime() {
      return sellTime;
  }
  
  public CatchSize getSize() {
      return size;
  }
  
  private Inventory(String id, Integer species, Integer quantity, Integer availableQuantity, Integer price, String catchLocation, String sellLocation, String catchTime, String sellTime, CatchSize size) {
    this.id = id;
    this.species = species;
    this.quantity = quantity;
    this.availableQuantity = availableQuantity;
    this.price = price;
    this.catchLocation = catchLocation;
    this.sellLocation = sellLocation;
    this.catchTime = catchTime;
    this.sellTime = sellTime;
    this.size = size;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Inventory inventory = (Inventory) obj;
      return ObjectsCompat.equals(getId(), inventory.getId()) &&
              ObjectsCompat.equals(getSpecies(), inventory.getSpecies()) &&
              ObjectsCompat.equals(getQuantity(), inventory.getQuantity()) &&
              ObjectsCompat.equals(getAvailableQuantity(), inventory.getAvailableQuantity()) &&
              ObjectsCompat.equals(getPrice(), inventory.getPrice()) &&
              ObjectsCompat.equals(getCatchLocation(), inventory.getCatchLocation()) &&
              ObjectsCompat.equals(getSellLocation(), inventory.getSellLocation()) &&
              ObjectsCompat.equals(getCatchTime(), inventory.getCatchTime()) &&
              ObjectsCompat.equals(getSellTime(), inventory.getSellTime()) &&
              ObjectsCompat.equals(getSize(), inventory.getSize());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getSpecies())
      .append(getQuantity())
      .append(getAvailableQuantity())
      .append(getPrice())
      .append(getCatchLocation())
      .append(getSellLocation())
      .append(getCatchTime())
      .append(getSellTime())
      .append(getSize())
      .toString()
      .hashCode();
  }
  
  public static SpeciesStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   **/
  public static Inventory justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Inventory(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      species,
      quantity,
      availableQuantity,
      price,
      catchLocation,
      sellLocation,
      catchTime,
      sellTime,
      size);
  }
  public interface SpeciesStep {
    QuantityStep species(Integer species);
  }
  

  public interface QuantityStep {
    AvailableQuantityStep quantity(Integer quantity);
  }
  

  public interface AvailableQuantityStep {
    PriceStep availableQuantity(Integer availableQuantity);
  }
  

  public interface PriceStep {
    CatchLocationStep price(Integer price);
  }
  

  public interface CatchLocationStep {
    SellLocationStep catchLocation(String catchLocation);
  }
  

  public interface SellLocationStep {
    CatchTimeStep sellLocation(String sellLocation);
  }
  

  public interface CatchTimeStep {
    SellTimeStep catchTime(String catchTime);
  }
  

  public interface SellTimeStep {
    SizeStep sellTime(String sellTime);
  }
  

  public interface SizeStep {
    BuildStep size(CatchSize size);
  }
  

  public interface BuildStep {
    Inventory build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements SpeciesStep, QuantityStep, AvailableQuantityStep, PriceStep, CatchLocationStep, SellLocationStep, CatchTimeStep, SellTimeStep, SizeStep, BuildStep {
    private String id;
    private Integer species;
    private Integer quantity;
    private Integer availableQuantity;
    private Integer price;
    private String catchLocation;
    private String sellLocation;
    private String catchTime;
    private String sellTime;
    private CatchSize size;
    @Override
     public Inventory build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Inventory(
          id,
          species,
          quantity,
          availableQuantity,
          price,
          catchLocation,
          sellLocation,
          catchTime,
          sellTime,
          size);
    }
    
    @Override
     public QuantityStep species(Integer species) {
        Objects.requireNonNull(species);
        this.species = species;
        return this;
    }
    
    @Override
     public AvailableQuantityStep quantity(Integer quantity) {
        Objects.requireNonNull(quantity);
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public PriceStep availableQuantity(Integer availableQuantity) {
        Objects.requireNonNull(availableQuantity);
        this.availableQuantity = availableQuantity;
        return this;
    }
    
    @Override
     public CatchLocationStep price(Integer price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public SellLocationStep catchLocation(String catchLocation) {
        Objects.requireNonNull(catchLocation);
        this.catchLocation = catchLocation;
        return this;
    }
    
    @Override
     public CatchTimeStep sellLocation(String sellLocation) {
        Objects.requireNonNull(sellLocation);
        this.sellLocation = sellLocation;
        return this;
    }
    
    @Override
     public SellTimeStep catchTime(String catchTime) {
        Objects.requireNonNull(catchTime);
        this.catchTime = catchTime;
        return this;
    }
    
    @Override
     public SizeStep sellTime(String sellTime) {
        Objects.requireNonNull(sellTime);
        this.sellTime = sellTime;
        return this;
    }
    
    @Override
     public BuildStep size(CatchSize size) {
        Objects.requireNonNull(size);
        this.size = size;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     **/
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Integer species, Integer quantity, Integer availableQuantity, Integer price, String catchLocation, String sellLocation, String catchTime, String sellTime, CatchSize size) {
      super.id(id);
      super.species(species)
        .quantity(quantity)
        .availableQuantity(availableQuantity)
        .price(price)
        .catchLocation(catchLocation)
        .sellLocation(sellLocation)
        .catchTime(catchTime)
        .sellTime(sellTime)
        .size(size);
    }
    
    @Override
     public CopyOfBuilder species(Integer species) {
      return (CopyOfBuilder) super.species(species);
    }
    
    @Override
     public CopyOfBuilder quantity(Integer quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder availableQuantity(Integer availableQuantity) {
      return (CopyOfBuilder) super.availableQuantity(availableQuantity);
    }
    
    @Override
     public CopyOfBuilder price(Integer price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder catchLocation(String catchLocation) {
      return (CopyOfBuilder) super.catchLocation(catchLocation);
    }
    
    @Override
     public CopyOfBuilder sellLocation(String sellLocation) {
      return (CopyOfBuilder) super.sellLocation(sellLocation);
    }
    
    @Override
     public CopyOfBuilder catchTime(String catchTime) {
      return (CopyOfBuilder) super.catchTime(catchTime);
    }
    
    @Override
     public CopyOfBuilder sellTime(String sellTime) {
      return (CopyOfBuilder) super.sellTime(sellTime);
    }
    
    @Override
     public CopyOfBuilder size(CatchSize size) {
      return (CopyOfBuilder) super.size(size);
    }
  }
  
}
