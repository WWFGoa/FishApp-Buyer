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

/** This is an auto generated class representing the Species type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Species")
@Index(name = "undefined", fields = {"id","name"})
public final class Species implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField NAME = field("name");
  public static final QueryField MIN_PRICE = field("minPrice");
  public static final QueryField MAX_PRICE = field("maxPrice");
  public static final QueryField MIN_WEIGHT = field("minWeight");
  public static final QueryField MAX_WEIGHT = field("maxWeight");
  public static final QueryField ACTIVE = field("active");
  public static final QueryField IMAGE = field("image");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Int", isRequired = true) Integer minPrice;
  private final @ModelField(targetType="Int", isRequired = true) Integer maxPrice;
  private final @ModelField(targetType="Int", isRequired = true) Integer minWeight;
  private final @ModelField(targetType="Int", isRequired = true) Integer maxWeight;
  private final @ModelField(targetType="Boolean", isRequired = true) Boolean active;
  private final @ModelField(targetType="Int", isRequired = true) Integer image;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Integer getMinPrice() {
      return minPrice;
  }
  
  public Integer getMaxPrice() {
      return maxPrice;
  }
  
  public Integer getMinWeight() {
      return minWeight;
  }
  
  public Integer getMaxWeight() {
      return maxWeight;
  }
  
  public Boolean getActive() {
      return active;
  }
  
  public Integer getImage() {
      return image;
  }
  
  private Species(String id, String name, Integer minPrice, Integer maxPrice, Integer minWeight, Integer maxWeight, Boolean active, Integer image) {
    this.id = id;
    this.name = name;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.minWeight = minWeight;
    this.maxWeight = maxWeight;
    this.active = active;
    this.image = image;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Species species = (Species) obj;
      return ObjectsCompat.equals(getId(), species.getId()) &&
              ObjectsCompat.equals(getName(), species.getName()) &&
              ObjectsCompat.equals(getMinPrice(), species.getMinPrice()) &&
              ObjectsCompat.equals(getMaxPrice(), species.getMaxPrice()) &&
              ObjectsCompat.equals(getMinWeight(), species.getMinWeight()) &&
              ObjectsCompat.equals(getMaxWeight(), species.getMaxWeight()) &&
              ObjectsCompat.equals(getActive(), species.getActive()) &&
              ObjectsCompat.equals(getImage(), species.getImage());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getMinPrice())
      .append(getMaxPrice())
      .append(getMinWeight())
      .append(getMaxWeight())
      .append(getActive())
      .append(getImage())
      .toString()
      .hashCode();
  }
  
  public static NameStep builder() {
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
  public static Species justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Species(
      id,
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
      name,
      minPrice,
      maxPrice,
      minWeight,
      maxWeight,
      active,
      image);
  }
  public interface NameStep {
    MinPriceStep name(String name);
  }
  

  public interface MinPriceStep {
    MaxPriceStep minPrice(Integer minPrice);
  }
  

  public interface MaxPriceStep {
    MinWeightStep maxPrice(Integer maxPrice);
  }
  

  public interface MinWeightStep {
    MaxWeightStep minWeight(Integer minWeight);
  }
  

  public interface MaxWeightStep {
    ActiveStep maxWeight(Integer maxWeight);
  }
  

  public interface ActiveStep {
    ImageStep active(Boolean active);
  }
  

  public interface ImageStep {
    BuildStep image(Integer image);
  }
  

  public interface BuildStep {
    Species build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements NameStep, MinPriceStep, MaxPriceStep, MinWeightStep, MaxWeightStep, ActiveStep, ImageStep, BuildStep {
    private String id;
    private String name;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minWeight;
    private Integer maxWeight;
    private Boolean active;
    private Integer image;
    @Override
     public Species build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Species(
          id,
          name,
          minPrice,
          maxPrice,
          minWeight,
          maxWeight,
          active,
          image);
    }
    
    @Override
     public MinPriceStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public MaxPriceStep minPrice(Integer minPrice) {
        Objects.requireNonNull(minPrice);
        this.minPrice = minPrice;
        return this;
    }
    
    @Override
     public MinWeightStep maxPrice(Integer maxPrice) {
        Objects.requireNonNull(maxPrice);
        this.maxPrice = maxPrice;
        return this;
    }
    
    @Override
     public MaxWeightStep minWeight(Integer minWeight) {
        Objects.requireNonNull(minWeight);
        this.minWeight = minWeight;
        return this;
    }
    
    @Override
     public ActiveStep maxWeight(Integer maxWeight) {
        Objects.requireNonNull(maxWeight);
        this.maxWeight = maxWeight;
        return this;
    }
    
    @Override
     public ImageStep active(Boolean active) {
        Objects.requireNonNull(active);
        this.active = active;
        return this;
    }
    
    @Override
     public BuildStep image(Integer image) {
        Objects.requireNonNull(image);
        this.image = image;
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
    private CopyOfBuilder(String id, String name, Integer minPrice, Integer maxPrice, Integer minWeight, Integer maxWeight, Boolean active, Integer image) {
      super.id(id);
      super.name(name)
        .minPrice(minPrice)
        .maxPrice(maxPrice)
        .minWeight(minWeight)
        .maxWeight(maxWeight)
        .active(active)
        .image(image);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder minPrice(Integer minPrice) {
      return (CopyOfBuilder) super.minPrice(minPrice);
    }
    
    @Override
     public CopyOfBuilder maxPrice(Integer maxPrice) {
      return (CopyOfBuilder) super.maxPrice(maxPrice);
    }
    
    @Override
     public CopyOfBuilder minWeight(Integer minWeight) {
      return (CopyOfBuilder) super.minWeight(minWeight);
    }
    
    @Override
     public CopyOfBuilder maxWeight(Integer maxWeight) {
      return (CopyOfBuilder) super.maxWeight(maxWeight);
    }
    
    @Override
     public CopyOfBuilder active(Boolean active) {
      return (CopyOfBuilder) super.active(active);
    }
    
    @Override
     public CopyOfBuilder image(Integer image) {
      return (CopyOfBuilder) super.image(image);
    }
  }
  
}
