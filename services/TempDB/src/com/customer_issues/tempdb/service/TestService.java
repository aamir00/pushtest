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

import com.customer_issues.tempdb.Test;

/**
 * Service object for domain model class {@link Test}.
 */
public interface TestService {

    /**
     * Creates a new Test. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Test if any.
     *
     * @param test Details of the Test to be created; value cannot be null.
     * @return The newly created Test.
     */
    Test create(@Valid Test test);


	/**
     * Returns Test by given id if exists.
     *
     * @param testId The id of the Test to get; value cannot be null.
     * @return Test associated with the given testId.
	 * @throws EntityNotFoundException If no Test is found.
     */
    Test getById(Integer testId);

    /**
     * Find and return the Test by given id if exists, returns null otherwise.
     *
     * @param testId The id of the Test to get; value cannot be null.
     * @return Test associated with the given testId.
     */
    Test findById(Integer testId);

	/**
     * Find and return the list of Tests by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param testIds The id's of the Test to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Tests associated with the given testIds.
     */
    List<Test> findByMultipleIds(List<Integer> testIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Test. It replaces all fields of the existing Test with the given test.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Test if any.
     *
     * @param test The details of the Test to be updated; value cannot be null.
     * @return The updated Test.
     * @throws EntityNotFoundException if no Test is found with given input.
     */
    Test update(@Valid Test test);


    /**
     * Partially updates the details of an existing Test. It updates only the
     * fields of the existing Test which are passed in the testPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Test if any.
     *
     * @param testId The id of the Test to be deleted; value cannot be null.
     * @param testPatch The partial data of Test which is supposed to be updated; value cannot be null.
     * @return The updated Test.
     * @throws EntityNotFoundException if no Test is found with given input.
     */
    Test partialUpdate(Integer testId, Map<String, Object> testPatch);

    /**
     * Deletes an existing Test with the given id.
     *
     * @param testId The id of the Test to be deleted; value cannot be null.
     * @return The deleted Test.
     * @throws EntityNotFoundException if no Test found with the given id.
     */
    Test delete(Integer testId);

    /**
     * Deletes an existing Test with the given object.
     *
     * @param test The instance of the Test to be deleted; value cannot be null.
     */
    void delete(Test test);

    /**
     * Find all Tests matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Tests.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Test> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Tests matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Tests.
     *
     * @see Pageable
     * @see Page
     */
    Page<Test> findAll(String query, Pageable pageable);

    /**
     * Exports all Tests matching the given input query to the given exportType format.
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
     * Exports all Tests matching the given input query to the given exportType format.
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
     * Retrieve the count of the Tests in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Test.
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