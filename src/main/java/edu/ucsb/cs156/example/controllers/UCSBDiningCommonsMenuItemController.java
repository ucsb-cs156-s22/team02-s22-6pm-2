package edu.ucsb.cs156.example.controllers;

import edu.ucsb.cs156.example.entities.UCSBDiningCommonsMenuItem;
import edu.ucsb.cs156.example.errors.EntityNotFoundException;
import edu.ucsb.cs156.example.repositories.UCSBDiningCommonsMenuItemRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(description = "UCSBDiningCommonsMenuItem")
@RequestMapping("/api/UCSBDiningCommonsMenuItem")
@RestController
@Slf4j
public class UCSBDiningCommonsMenuItemController extends ApiController {

    @Autowired
    UCSBDiningCommonsMenuItemRepository ucsbDiningCommonsMenuItemRepository;

    @ApiOperation(value = "List all menu items")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/all")
    public Iterable<UCSBDiningCommonsMenuItem> allCommons() {
        Iterable<UCSBDiningCommonsMenuItem> commons = ucsbDiningCommonsMenuItemRepository.findAll();
        return commons;
    }

    @ApiOperation(value = "Get a single menu item")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("")
    public UCSBDiningCommonsMenuItem getById(
            @ApiParam("id") @RequestParam long id) {
        UCSBDiningCommonsMenuItem commons = ucsbDiningCommonsMenuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UCSBDiningCommonsMenuItem.class, id));

        return commons;
    }

    @ApiOperation(value = "Create a new commons")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/post")
    public UCSBDiningCommons postCommons(
        @ApiParam("code") @RequestParam String code,
        @ApiParam("name") @RequestParam String name,
        @ApiParam("hasSackMeal") @RequestParam boolean hasSackMeal,
        @ApiParam("hasTakeOutMeal") @RequestParam boolean hasTakeOutMeal,
        @ApiParam("hasDiningCam") @RequestParam boolean hasDiningCam,
        @ApiParam("latitude") @RequestParam double latitude,
        @ApiParam("longitude") @RequestParam double longitude
        )
        {

        UCSBDiningCommons commons = new UCSBDiningCommons();
        commons.setCode(code);
        commons.setName(name);
        commons.setHasSackMeal(hasSackMeal);
        commons.setHasTakeOutMeal(hasTakeOutMeal);
        commons.setHasDiningCam(hasDiningCam);
        commons.setLatitude(latitude);
        commons.setLongitude(longitude);

        UCSBDiningCommons savedCommons = ucsbDiningCommonsRepository.save(commons);

        return savedCommons;
    }    
    
    @ApiOperation(value = "Create a new commons")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/post")
    public UCSBDiningCommonsMenuItem postCommons(
        @ApiParam("diningCommonsCode") @RequestParam String code,
        @ApiParam("name") @RequestParam String name,
        @ApiParam("hasSackMeal") @RequestParam boolean hasSackMeal,
        @ApiParam("hasTakeOutMeal") @RequestParam boolean hasTakeOutMeal,
        @ApiParam("hasDiningCam") @RequestParam boolean hasDiningCam,
        @ApiParam("latitude") @RequestParam double latitude,
        @ApiParam("longitude") @RequestParam double longitude
        )
        {

        UCSBDiningCommons commons = new UCSBDiningCommons();
        commons.setCode(code);
        commons.setName(name);
        commons.setHasSackMeal(hasSackMeal);
        commons.setHasTakeOutMeal(hasTakeOutMeal);
        commons.setHasDiningCam(hasDiningCam);
        commons.setLatitude(latitude);
        commons.setLongitude(longitude);

        UCSBDiningCommons savedCommons = ucsbDiningCommonsRepository.save(commons);

        return savedCommons;
    }

    @ApiOperation(value = "Delete a UCSBDiningCommonsMenuItem")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("")
    public Object deleteCommons(
            @ApiParam("id") @RequestParam long id) {
        UCSBDiningCommonsMenuItem commons = ucsbDiningCommonsMenuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UCSBDiningCommonsMenuItem.class, id));

        ucsbDiningCommonsMenuItemRepository.delete(commons);
        return genericMessage("UCSBDiningCommonsMenuItem with id %s deleted".formatted(id));
    }

    @ApiOperation(value = "Update a single menu item")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("")
    public UCSBDiningCommonsMenuItem updateCommons(
            @ApiParam("id") @RequestParam Long id,
            @RequestBody @Valid UCSBDiningCommonsMenuItem incoming) {

        UCSBDiningCommonsMenuItem commons = ucsbDiningCommonsMenuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UCSBDiningCommonsMenuItem.class, id));


        commons.setName(incoming.getName());  
        commons.setStation(incoming.getStation());
        commons.setDiningCommonsCode(incoming.getDiningCommonsCode());
       

        ucsbDiningCommonsMenuItemRepository.save(commons);

        return commons;
    }
}
