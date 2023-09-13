package com.example.locationCar.repositories.specifications.vehicle;

import com.example.locationCar.models.VehicleModel;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecifications {

  public static Specification<VehicleModel> colorEquals(String color) {
    return (root, query, criteriaBuilder) ->
        color == null ? null : criteriaBuilder.equal(root.get("color"), color);
  }

  public static Specification<VehicleModel> ratingEquals(Double rating) {
    return (root, query, criteriaBuilder) ->
        rating == null ? null : criteriaBuilder.equal(root.get("rating"), rating);
  }

  public static Specification<VehicleModel> dailyValueLessThanOrEqual(Double value) {
    return (root, query, criteriaBuilder) ->
        value == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("dailyValue"), value);
  }

  public static Specification<VehicleModel> dailyValueGreaterThanOrEqual(Double value) {
    return (root, query, criteriaBuilder) ->
        value == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("dailyValue"), value);
  }

  public Specification<VehicleModel> buildSpec(
      String cor, Double avaliacaoMedia, Double precoMaximo, Double precoMinimo) {
    Specification<VehicleModel> spec =
        Specification.where(VehicleSpecifications.colorEquals(cor))
            .and(VehicleSpecifications.ratingEquals(avaliacaoMedia))
            .and(VehicleSpecifications.dailyValueLessThanOrEqual(precoMaximo))
            .and(VehicleSpecifications.dailyValueGreaterThanOrEqual(precoMinimo));
    return spec;
  }
}
