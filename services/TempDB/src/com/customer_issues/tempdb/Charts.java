/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.customer_issues.tempdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Charts generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`charts`")
public class Charts implements Serializable {

    private Integer id;
    private Integer val1;
    private String category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`val1`", nullable = true, scale = 0, precision = 10)
    public Integer getVal1() {
        return this.val1;
    }

    public void setVal1(Integer val1) {
        this.val1 = val1;
    }

    @Column(name = "`category`", nullable = true, length = 255)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Charts)) return false;
        final Charts charts = (Charts) o;
        return Objects.equals(getId(), charts.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}