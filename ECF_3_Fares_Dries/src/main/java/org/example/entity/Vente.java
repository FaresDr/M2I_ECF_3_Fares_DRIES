package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateVente;

    private Status status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.EAGER)
    @JoinTable(name ="Vente_Article" ,joinColumns = @JoinColumn(name = "vente_id")
            ,inverseJoinColumns = @JoinColumn(name = "id"))
        private List<Article> articles;


    private double totalValue;

    public void add (Article article){
        articles.add(article);
    }

    public void remove (Article article){articles.remove(article);}

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", dateVente=" + dateVente +
                ", status=" + status +
                ", client=" + client +
                ", articles=" + articles +
                ", totalValue=" + totalValue +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
