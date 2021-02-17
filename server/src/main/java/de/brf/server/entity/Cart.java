package de.brf.server.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Builder
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(

            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "shopping_cart_products",
            joinColumns = @JoinColumn(
                    name = "shopping_cart_id",
                    foreignKey = @ForeignKey(name = "shopping_card_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(name = "product_id_fk")
            )
    )
    private Set<Product> products;

}
