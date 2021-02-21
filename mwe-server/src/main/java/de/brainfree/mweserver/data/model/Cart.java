package de.brainfree.mweserver.data.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Wahrscheinlich wäre es auch besser, wenn man nicht nur das Produkt speichert, sondern auch nicht die Anzahl pro Produkt ;)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Setter(AccessLevel.NONE)
    @OneToMany
    @JoinColumn(name = "cart_id")
    private Set<CartItem> items = new HashSet<>();

    public Cart addItem(CartItem item) {
        if (this.items == null) {
            this.items = new HashSet<>();
        }
        this.items.add(item);
        return this;
    }

    public Cart removeItem(CartItem item) {
        this.items.remove(item);
        return this;
    }

}
