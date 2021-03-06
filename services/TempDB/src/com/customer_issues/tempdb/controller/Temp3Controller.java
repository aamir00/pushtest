/*Copyright (c) 2019-2020 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.customer_issues.tempdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.security.xss.XssDisable;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.customer_issues.tempdb.Temp2;
import com.customer_issues.tempdb.Temp3;
import com.customer_issues.tempdb.service.Temp3Service;


/**
 * Controller object for domain model class Temp3.
 * @see Temp3
 */
@RestController("TempDB.Temp3Controller")
@Api(value = "Temp3Controller", description = "Exposes APIs to work with Temp3 resource.")
@RequestMapping("/TempDB/Temp3")
public class Temp3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Temp3Controller.class);

    @Autowired
	@Qualifier("TempDB.Temp3Service")
	private Temp3Service temp3Service;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Temp3 instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Temp3 createTemp3(@RequestBody Temp3 temp3) {
		LOGGER.debug("Create Temp3 with information: {}" , temp3);

		temp3 = temp3Service.create(temp3);
		LOGGER.debug("Created Temp3 with information: {}" , temp3);

	    return temp3;
	}

    @ApiOperation(value = "Returns the Temp3 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Temp3 getTemp3(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Temp3 with id: {}" , id);

        Temp3 foundTemp3 = temp3Service.getById(id);
        LOGGER.debug("Temp3 details with id: {}" , foundTemp3);

        return foundTemp3;
    }

    @ApiOperation(value = "Updates the Temp3 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Temp3 editTemp3(@PathVariable("id") Integer id, @RequestBody Temp3 temp3) {
        LOGGER.debug("Editing Temp3 with id: {}" , temp3.getId());

        temp3.setId(id);
        temp3 = temp3Service.update(temp3);
        LOGGER.debug("Temp3 details with id: {}" , temp3);

        return temp3;
    }
    
    @ApiOperation(value = "Partially updates the Temp3 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PATCH)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Temp3 patchTemp3(@PathVariable("id") Integer id, @RequestBody @MapTo(Temp3.class) Map<String, Object> temp3Patch) {
        LOGGER.debug("Partially updating Temp3 with id: {}" , id);

        Temp3 temp3 = temp3Service.partialUpdate(id, temp3Patch);
        LOGGER.debug("Temp3 details after partial update: {}" , temp3);

        return temp3;
    }

    @ApiOperation(value = "Deletes the Temp3 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteTemp3(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Temp3 with id: {}" , id);

        Temp3 deletedTemp3 = temp3Service.delete(id);

        return deletedTemp3 != null;
    }

    /**
     * @deprecated Use {@link #findTemp3s(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Temp3 instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<Temp3> searchTemp3sByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Temp3s list by query filter:{}", (Object) queryFilters);
        return temp3Service.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Temp3 instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Temp3> findTemp3s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Temp3s list by filter:", query);
        return temp3Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Temp3 instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Page<Temp3> filterTemp3s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Temp3s list by filter", query);
        return temp3Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public Downloadable exportTemp3s(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return temp3Service.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportTemp3sAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Temp3.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> temp3Service.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Temp3 instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Long countTemp3s( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Temp3s");
		return temp3Service.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	@XssDisable
	public Page<Map<String, Object>> getTemp3AggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return temp3Service.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/temp2s", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the temp2s instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Temp2> findAssociatedTemp2s(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated temp2s");
        return temp3Service.findAssociatedTemp2s(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Temp3Service instance
	 */
	protected void setTemp3Service(Temp3Service service) {
		this.temp3Service = service;
	}

}