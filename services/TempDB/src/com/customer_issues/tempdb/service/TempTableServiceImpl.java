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

import com.customer_issues.tempdb.TempTable;


/**
 * ServiceImpl object for domain model class TempTable.
 *
 * @see TempTable
 */
@Service("TempDB.TempTableService")
@Validated
public class TempTableServiceImpl implements TempTableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TempTableServiceImpl.class);


    @Autowired
    @Qualifier("TempDB.TempTableDao")
    private WMGenericDao<TempTable, String> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<TempTable, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public TempTable create(TempTable tempTable) {
        LOGGER.debug("Creating a new TempTable with information: {}", tempTable);

        TempTable tempTableCreated = this.wmGenericDao.create(tempTable);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(tempTableCreated);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public TempTable getById(String temptableId) {
        LOGGER.debug("Finding TempTable by id: {}", temptableId);
        return this.wmGenericDao.findById(temptableId);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public TempTable findById(String temptableId) {
        LOGGER.debug("Finding TempTable by id: {}", temptableId);
        try {
            return this.wmGenericDao.findById(temptableId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No TempTable found with id: {}", temptableId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public List<TempTable> findByMultipleIds(List<String> temptableIds, boolean orderedReturn) {
        LOGGER.debug("Finding TempTables by ids: {}", temptableIds);

        return this.wmGenericDao.findByMultipleIds(temptableIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "TempDBTransactionManager")
    @Override
    public TempTable update(TempTable tempTable) {
        LOGGER.debug("Updating TempTable with information: {}", tempTable);

        this.wmGenericDao.update(tempTable);
        this.wmGenericDao.refresh(tempTable);

        return tempTable;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public TempTable partialUpdate(String temptableId, Map<String, Object>tempTablePatch) {
        LOGGER.debug("Partially Updating the TempTable with id: {}", temptableId);

        TempTable tempTable = getById(temptableId);

        try {
            ObjectReader tempTableReader = this.objectMapper.reader().forType(TempTable.class).withValueToUpdate(tempTable);
            tempTable = tempTableReader.readValue(this.objectMapper.writeValueAsString(tempTablePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", tempTablePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        tempTable = update(tempTable);

        return tempTable;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public TempTable delete(String temptableId) {
        LOGGER.debug("Deleting TempTable with id: {}", temptableId);
        TempTable deleted = this.wmGenericDao.findById(temptableId);
        if (deleted == null) {
            LOGGER.debug("No TempTable found with id: {}", temptableId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), TempTable.class.getSimpleName(), temptableId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "TempDBTransactionManager")
    @Override
    public void delete(TempTable tempTable) {
        LOGGER.debug("Deleting TempTable with {}", tempTable);
        this.wmGenericDao.delete(tempTable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public Page<TempTable> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all TempTables");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager")
    @Override
    public Page<TempTable> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all TempTables");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service TempDB for table TempTable to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "TempDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service TempDB for table TempTable to {} format", options.getExportType());
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