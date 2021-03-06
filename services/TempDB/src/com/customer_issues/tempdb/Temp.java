/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.customer_issues.tempdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Temp generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`temp`")
public class Temp implements Serializable {

    private Integer id;
    private Date date1;
    private Time time1;
    private Timestamp timestamp1;
    private LocalDateTime datetime1;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`date1`", nullable = true)
    public Date getDate1() {
        return this.date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    @Column(name = "`time1`", nullable = true)
    public Time getTime1() {
        return this.time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    @Column(name = "`timestamp1`", nullable = true)
    public Timestamp getTimestamp1() {
        return this.timestamp1;
    }

    public void setTimestamp1(Timestamp timestamp1) {
        this.timestamp1 = timestamp1;
    }

    @Column(name = "`datetime1`", nullable = true)
    public LocalDateTime getDatetime1() {
        return this.datetime1;
    }

    public void setDatetime1(LocalDateTime datetime1) {
        this.datetime1 = datetime1;
    }

    @Column(name = "`description`", nullable = true, length = 255)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Temp)) return false;
        final Temp temp = (Temp) o;
        return Objects.equals(getId(), temp.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}