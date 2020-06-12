/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.customer_issues.countries1.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.customer_issues.countries1.Cities;


/**
 * ServiceImpl object for domain model class Cities.
 *
 * @see Cities
 */
@Service("Countries1.CitiesService")
@Validated
public class CitiesServiceImpl implements CitiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitiesServiceImpl.class);


    @Autowired
    @Qualifier("Countries1.CitiesDao")
    private WMGenericDao<Cities, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Cities, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "Countries1TransactionManager")
    @Override
    public Cities create(Cities cities) {
        LOGGER.debug("Creating a new Cities with information: {}", cities);

        Cities citiesCreated = this.wmGenericDao.create(cities);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(citiesCreated);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public Cities getById(Integer citiesId) {
        LOGGER.debug("Finding Cities by id: {}", citiesId);
        return this.wmGenericDao.findById(citiesId);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public Cities findById(Integer citiesId) {
        LOGGER.debug("Finding Cities by id: {}", citiesId);
        try {
            return this.wmGenericDao.findById(citiesId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Cities found with id: {}", citiesId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public List<Cities> findByMultipleIds(List<Integer> citiesIds, boolean orderedReturn) {
        LOGGER.debug("Finding Cities by ids: {}", citiesIds);

        return this.wmGenericDao.findByMultipleIds(citiesIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "Countries1TransactionManager")
    @Override
    public Cities update(Cities cities) {
        LOGGER.debug("Updating Cities with information: {}", cities);

        this.wmGenericDao.update(cities);
        this.wmGenericDao.refresh(cities);

        return cities;
    }

    @Transactional(value = "Countries1TransactionManager")
    @Override
    public Cities partialUpdate(Integer citiesId, Map<String, Object>citiesPatch) {
        LOGGER.debug("Partially Updating the Cities with id: {}", citiesId);

        Cities cities = getById(citiesId);

        try {
            ObjectReader citiesReader = this.objectMapper.reader().forType(Cities.class).withValueToUpdate(cities);
            cities = citiesReader.readValue(this.objectMapper.writeValueAsString(citiesPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", citiesPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        cities = update(cities);

        return cities;
    }

    @Transactional(value = "Countries1TransactionManager")
    @Override
    public Cities delete(Integer citiesId) {
        LOGGER.debug("Deleting Cities with id: {}", citiesId);
        Cities deleted = this.wmGenericDao.findById(citiesId);
        if (deleted == null) {
            LOGGER.debug("No Cities found with id: {}", citiesId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Cities.class.getSimpleName(), citiesId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "Countries1TransactionManager")
    @Override
    public void delete(Cities cities) {
        LOGGER.debug("Deleting Cities with {}", cities);
        this.wmGenericDao.delete(cities);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public Page<Cities> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Cities");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public Page<Cities> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Cities");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service Countries1 for table Cities to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service Countries1 for table Cities to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "Countries1TransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}