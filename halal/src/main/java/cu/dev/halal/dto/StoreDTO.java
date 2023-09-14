package cu.dev.halal.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreDTO {
    private Long id;
    private String name;

    private String address;

    private Double coordinateX;
    private Double coordinateY;

    private String operatingTime;

    private String storePhoneNumber;

    private String menu;
}
