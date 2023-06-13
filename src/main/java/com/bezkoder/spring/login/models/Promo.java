package com.bezkoder.spring.login.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="titleimagesrc")
    private String promoImageSrc;

    @Column(name="detailed_description")
    private String detailedDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "promo_tags",
            joinColumns = {@JoinColumn(name="promo_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPromoImageSrc() {
        return promoImageSrc;
    }

    public void setPromoImageSrc(String promoImageSrc) {
        this.promoImageSrc = promoImageSrc;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
