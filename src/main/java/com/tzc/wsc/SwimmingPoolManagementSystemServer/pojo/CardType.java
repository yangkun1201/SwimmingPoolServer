package com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "card_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    int type;
    @Column
    String description;
}
