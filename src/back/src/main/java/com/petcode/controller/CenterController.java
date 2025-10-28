package com.petcode.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcode.entity.Center;
import com.petcode.service.CenterService;

@RestController
@RequestMapping("/api/center")
public class CenterController {
  private final CenterService service;

  public CenterController(CenterService service) {
    this.service = service;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createCenter(@RequestBody Center newCenter) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(service.createCenter(newCenter));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/{code}")
  public ResponseEntity<?> getCenter(@PathVariable String code) {
    try {
      return ResponseEntity.ok(service.findByCode(code));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @GetMapping("/")
  public ResponseEntity<?> getAllCenters() {
    try {
      return ResponseEntity.ok(service.findAll());
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PutMapping("/{code}")
  public ResponseEntity<?> replaceCenter(@PathVariable String code, @RequestBody Center center) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(service.replaceCenter(code, center));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PatchMapping("/{code}")
  public ResponseEntity<?> updateCenter(@PathVariable String code, @RequestBody Map<String, Object> fields) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(service.updateCenter(code, fields));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/{code}")
  public ResponseEntity<?> deleteCenter(@PathVariable String code) {
    try {
      service.deleteByCode(code);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
