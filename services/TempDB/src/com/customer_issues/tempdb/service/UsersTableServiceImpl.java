/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.customer_issues.tempdb.service;

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

import com.customer_issues.tempdb.UsersTable;


/**
 * ServiceImpl object for domain model class UsersTable.
 *
 * @see UsersTable
 */
@Service("TempDB.UsersTableService")
@Validated
public class UsersTableServiceImpl implements UsersTableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersTableServiceImpl.class);


    @Autowired
    @Qualifier("TempDB.UsersTableDao")
    private WMGenericDao<UsersTable, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<UsersTable, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public UsersTable create(UsersTable usersTable) {
        LOGGER.debug("Creating a new UsersTable with information: {}", usersTable);

        UsersTable usersTableCreated = this.wmGenericDao.create(usersTable);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(usersTableCreated);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public UsersTable getById(Integer userstableId) {
        LOGGER.debug("Finding UsersTable by id: {}", userstableId);
        return this.wmGenericDao.findById(userstableId);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public UsersTable findById(Integer userstableId) {
        LOGGER.debug("Finding UsersTable by id: {}", userstableId);
        try {
            return this.wmGenericDao.findById(userstableId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No UsersTable found with id: {}", userstableId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public List<UsersTable> findByMultipleIds(List<Integer> userstableIds, boolean orderedReturn) {
        LOGGER.debug("Finding UsersTables by ids: {}", userstableIds);

        return this.wmGenericDao.findByMultipleIds(userstableIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "TempDBTransactionManager")
    @Override
    public UsersTable update(UsersTable usersTable) {
        LOGGER.debug("Updating UsersTable with information: {}", usersTable);

        this.wmGenericDao.update(usersTable);
        this.wmGenericDao.refresh(usersTable);

        return usersTable;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public UsersTable partialUpdate(Integer userstableId, Map<String, Object>usersTablePatch) {
        LOGGER.debug("Partially Updating the UsersTable with id: {}", userstableId);

        UsersTable usersTable = getById(userstableId);

        try {
            ObjectReader usersTableReader = this.objectMapper.reader().forType(UsersTable.class).withValueToUpdate(usersTable);
            usersTable = usersTableReader.readValue(this.objectMapper.writeValueAsString(usersTablePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", usersTablePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        usersTable = update(usersTable);

        return usersTable;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public UsersTable delete(Integer userstableId) {
        LOGGER.debug("Deleting UsersTable with id: {}", userstableId);
        UsersTable deleted = this.wmGenericDao.findById(userstableId);
        if (deleted == null) {
            LOGGER.debug("No UsersTable found with id: {}", userstableId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), UsersTable.class.getSimpleName(), userstableId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public void delete(UsersTable usersTable) {
        LOGGER.debug("Deleting UsersTable with {}", usersTable);
        this.wmGenericDao.delete(usersTable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public Page<UsersTable> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UsersTables");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public Page<UsersTable> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UsersTables");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service TempDB for table UsersTable to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service TempDB for table UsersTable to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}