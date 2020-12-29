package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Inventory type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Inventories", authRules = {
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
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
  public static final QueryField CREATED_AT = field("createdAt");
  public static final QueryField UPDATED_AT = field("updatedAt");
  public static final QueryField CONTACT = field("contact");
  public static final QueryField NAME = field("name");
  public static final QueryField USER_ID = field("userId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int") Integer species;
  private final @ModelField(targetType="Float") Float quantity;
  private final @ModelField(targetType="Float") Float availableQuantity;
  private final @ModelField(targetType="Int") Integer price;
  private final @ModelField(targetType="String") String catchLocation;
  private final @ModelField(targetType="String") String sellLocation;
  private final @ModelField(targetType="String") String catchTime;
  private final @ModelField(targetType="String") String sellTime;
  private final @ModelField(targetType="CatchSize") CatchSize size;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime createdAt;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime updatedAt;
  private final @ModelField(targetType="String") String contact;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String userId;
  public String getId() {
      return id;
  }
  
  public Integer getSpecies() {
      return species;
  }
  
  public Float getQuantity() {
      return quantity;
  }
  
  public Float getAvailableQuantity() {
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
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getContact() {
      return contact;
  }
  
  public String getName() {
      return name;
  }
  
  public String getUserId() {
      return userId;
  }
  
  private Inventory(String id, Integer species, Float quantity, Float availableQuantity, Integer price, String catchLocation, String sellLocation, String catchTime, String sellTime, CatchSize size, Temporal.DateTime createdAt, Temporal.DateTime updatedAt, String contact, String name, String userId) {
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
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.contact = contact;
    this.name = name;
    this.userId = userId;
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
              ObjectsCompat.equals(getSize(), inventory.getSize()) &&
              ObjectsCompat.equals(getCreatedAt(), inventory.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), inventory.getUpdatedAt()) &&
              ObjectsCompat.equals(getContact(), inventory.getContact()) &&
              ObjectsCompat.equals(getName(), inventory.getName()) &&
              ObjectsCompat.equals(getUserId(), inventory.getUserId());
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
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getContact())
      .append(getName())
      .append(getUserId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Inventory {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("species=" + String.valueOf(getSpecies()) + ", ")
      .append("quantity=" + String.valueOf(getQuantity()) + ", ")
      .append("availableQuantity=" + String.valueOf(getAvailableQuantity()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("catchLocation=" + String.valueOf(getCatchLocation()) + ", ")
      .append("sellLocation=" + String.valueOf(getSellLocation()) + ", ")
      .append("catchTime=" + String.valueOf(getCatchTime()) + ", ")
      .append("sellTime=" + String.valueOf(getSellTime()) + ", ")
      .append("size=" + String.valueOf(getSize()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("contact=" + String.valueOf(getContact()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("userId=" + String.valueOf(getUserId()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
   */
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
      size,
      createdAt,
      updatedAt,
      contact,
      name,
      userId);
  }
  public interface BuildStep {
    Inventory build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep species(Integer species);
    BuildStep quantity(Float quantity);
    BuildStep availableQuantity(Float availableQuantity);
    BuildStep price(Integer price);
    BuildStep catchLocation(String catchLocation);
    BuildStep sellLocation(String sellLocation);
    BuildStep catchTime(String catchTime);
    BuildStep sellTime(String sellTime);
    BuildStep size(CatchSize size);
    BuildStep createdAt(Temporal.DateTime createdAt);
    BuildStep updatedAt(Temporal.DateTime updatedAt);
    BuildStep contact(String contact);
    BuildStep name(String name);
    BuildStep userId(String userId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Integer species;
    private Float quantity;
    private Float availableQuantity;
    private Integer price;
    private String catchLocation;
    private String sellLocation;
    private String catchTime;
    private String sellTime;
    private CatchSize size;
    private Temporal.DateTime createdAt;
    private Temporal.DateTime updatedAt;
    private String contact;
    private String name;
    private String userId;
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
          size,
          createdAt,
          updatedAt,
          contact,
          name,
          userId);
    }
    
    @Override
     public BuildStep species(Integer species) {
        this.species = species;
        return this;
    }
    
    @Override
     public BuildStep quantity(Float quantity) {
        this.quantity = quantity;
        return this;
    }
    
    @Override
     public BuildStep availableQuantity(Float availableQuantity) {
        this.availableQuantity = availableQuantity;
        return this;
    }
    
    @Override
     public BuildStep price(Integer price) {
        this.price = price;
        return this;
    }
    
    @Override
     public BuildStep catchLocation(String catchLocation) {
        this.catchLocation = catchLocation;
        return this;
    }
    
    @Override
     public BuildStep sellLocation(String sellLocation) {
        this.sellLocation = sellLocation;
        return this;
    }
    
    @Override
     public BuildStep catchTime(String catchTime) {
        this.catchTime = catchTime;
        return this;
    }
    
    @Override
     public BuildStep sellTime(String sellTime) {
        this.sellTime = sellTime;
        return this;
    }
    
    @Override
     public BuildStep size(CatchSize size) {
        this.size = size;
        return this;
    }
    
    @Override
     public BuildStep createdAt(Temporal.DateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    @Override
     public BuildStep updatedAt(Temporal.DateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
    
    @Override
     public BuildStep contact(String contact) {
        this.contact = contact;
        return this;
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        this.userId = userId;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
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
    private CopyOfBuilder(String id, Integer species, Float quantity, Float availableQuantity, Integer price, String catchLocation, String sellLocation, String catchTime, String sellTime, CatchSize size, Temporal.DateTime createdAt, Temporal.DateTime updatedAt, String contact, String name, String userId) {
      super.id(id);
      super.species(species)
        .quantity(quantity)
        .availableQuantity(availableQuantity)
        .price(price)
        .catchLocation(catchLocation)
        .sellLocation(sellLocation)
        .catchTime(catchTime)
        .sellTime(sellTime)
        .size(size)
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .contact(contact)
        .name(name)
        .userId(userId);
    }
    
    @Override
     public CopyOfBuilder species(Integer species) {
      return (CopyOfBuilder) super.species(species);
    }
    
    @Override
     public CopyOfBuilder quantity(Float quantity) {
      return (CopyOfBuilder) super.quantity(quantity);
    }
    
    @Override
     public CopyOfBuilder availableQuantity(Float availableQuantity) {
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
    
    @Override
     public CopyOfBuilder createdAt(Temporal.DateTime createdAt) {
      return (CopyOfBuilder) super.createdAt(createdAt);
    }
    
    @Override
     public CopyOfBuilder updatedAt(Temporal.DateTime updatedAt) {
      return (CopyOfBuilder) super.updatedAt(updatedAt);
    }
    
    @Override
     public CopyOfBuilder contact(String contact) {
      return (CopyOfBuilder) super.contact(contact);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
  }
  
}
