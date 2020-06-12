/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.customer_issues.tempdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.customer_issues.tempdb.Temp;

/**
 * Service object for domain model class {@link Temp}.
 */
public interface TempService {

    /**
     * Creates a new Temp. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Temp if any.
     *
     * @param temp Details of the Temp to be created; value cannot be null.
     * @return The newly created Temp.
     */
    Temp create(@Valid Temp temp);


	/**
     * Returns Temp by given id if exists.
     *
     * @param tempId The id of the Temp to get; value cannot be null.
     * @return Temp associated with the given tempId.
	 * @throws EntityNotFoundException If no Temp is found.
     */
    Temp getById(Integer tempId);

    /**
     * Find and return the Temp by given id if exists, returns null otherwise.
     *
     * @param tempId The id of the Temp to get; value cannot be null.
     * @return Temp associated with the given tempId.
     */
    Temp findById(Integer tempId);

	/**
     * Find and return the list of Temps by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param tempIds The id's of the Temp to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Temps associated with the given tempIds.
     */
    List<Temp> findByMultipleIds(List<Integer> tempIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Temp. It replaces all fields of the existing Temp with the given temp.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Temp if any.
     *
     * @param temp The details of the Temp to be updated; value cannot be null.
     * @return The updated Temp.
     * @throws EntityNotFoundException if no Temp is found with given input.
     */
    Temp update(@Valid Temp temp);


    /**
     * Partially updates the details of an existing Temp. It updates only the
     * fields of the existing Temp which are passed in the tempPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Temp if any.
     *
     * @param tempId The id of the Temp to be deleted; value cannot be null.
     * @param tempPatch The partial data of Temp which is supposed to be updated; value cannot be null.
     * @return The updated Temp.
     * @throws EntityNotFoundException if no Temp is found with given input.
     */
    Temp partialUpdate(Integer tempId, Map<String, Object> tempPatch);

    /**
     * Deletes an existing Temp with the given id.
     *
     * @param tempId The id of the Temp to be deleted; value cannot be null.
     * @return The deleted Temp.
     * @throws EntityNotFoundException if no Temp found with the given id.
     */
    Temp delete(Integer tempId);

    /**
     * Deletes an existing Temp with the given object.
     *
     * @param temp The instance of the Temp to be deleted; value cannot be null.
     */
    void delete(Temp temp);

    /**
     * Find all Temps matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Temps.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Temp> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Temps matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Temps.
     *
     * @see Pageable
     * @see Page
     */
    Page<Temp> findAll(String query, Pageable pageable);

    /**
     * Exports all Temps matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all Temps matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the Temps in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Temp.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);


}