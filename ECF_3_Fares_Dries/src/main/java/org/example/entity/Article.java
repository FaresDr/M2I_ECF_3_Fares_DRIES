package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private Categorie categorie;

    private String taille;

    private double prix;

    private int stock;

    @ManyToMany
    @JoinTable(name ="Vente_Article" ,joinColumns = @JoinColumn(name = "id")
            ,inverseJoinColumns = @JoinColumn(name = "vente_id"))
    private List<Vente> vente;




    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", categorie=" + categorie +
                ", taille='" + taille + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                '}';
    }
}
