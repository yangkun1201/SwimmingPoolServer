package com.tzc.wsc.SwimmingPoolManagementSystemServer.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "commodity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int price;
    @Column(columnDefinition = "pic_url")
    private String picUrl;
}
