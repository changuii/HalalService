package cu.dev.halal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String coordinateX;

    @Column(nullable = false)
    private String coordinateY;

    @Column(nullable = false)
    private String operatingTime;

    @Column(nullable = false)
    private String storePhoneNumber;

    @Column(nullable = false)
    private String menu;

    @Column
    @OneToMany(mappedBy = "store",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<FavoriteEntity> favorites = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ReviewEntity> reviews = new ArrayList<>();



}
