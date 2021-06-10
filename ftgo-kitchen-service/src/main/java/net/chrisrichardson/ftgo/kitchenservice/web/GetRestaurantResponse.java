package net.chrisrichardson.ftgo.kitchenservice.web;

public class GetRestaurantResponse  {
  private long restaurantId;
  private long currentEfficiency;

  public long getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(long restaurantId) {
    this.restaurantId = restaurantId;
  }

  public long getCurrentEfficiency() {
    return currentEfficiency;
  }

  public void setCurrentEfficiency(long currentEfficiency) {
    this.currentEfficiency = currentEfficiency;
  }

  public GetRestaurantResponse() {

  }

  public GetRestaurantResponse(long restaurantId, long currentEfficiency) {
    this.restaurantId = restaurantId;
    this.currentEfficiency = currentEfficiency;
  }
}
