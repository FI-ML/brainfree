package de.brainfree.mweserver.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @Column(nullable = false, updatable = false, unique = true)
    private String username;

    // @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> items;

    public Cart updateItems(Set<CartItem> updates) {
        if (this.items == null) {
            this.items = new HashSet<>();
        }
        this.items.clear();
        //updates.forEach(update -> update.setCart(this));
        this.items.addAll(updates);
        return this; // das hier mache ich damit man direkt weiter arbeiten kann mit dem Cart und ihn nicht zwischenspeichern muss
    }

}
