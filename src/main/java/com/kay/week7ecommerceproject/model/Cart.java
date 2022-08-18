package com.kay.week7ecommerceproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
//    @JoinColumn(name = "user_id")
    private AppUser user;
    @OneToMany
    private List<Product> product;
}
