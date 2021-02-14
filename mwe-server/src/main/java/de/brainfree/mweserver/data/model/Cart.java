package de.brainfree.mweserver.data.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String username;

    @OneToMany(orphanRemoval = true)
    @Setter(AccessLevel.NONE) // man kann dann nicht einfach "setProducts()" aufrufen sondern muss updateProducts() nehmen
    private Set<Product> products;

    public Cart updateProducts(Set<Product> updates) {
        if (this.products == null) {
            this.products = new HashSet<>();
        }
        this.products.clear();
        this.products.addAll(updates);
        return this; // das hier mache ich damit man direkt weiter arbeiten kann mit dem Cart und ihn nicht zwischenspeichern muss
    }

}
