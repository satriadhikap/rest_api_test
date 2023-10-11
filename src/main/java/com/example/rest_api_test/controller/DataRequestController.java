package com.example.rest_api_test.controller;

import com.example.rest_api_test.dto.OrderRequestDto;
import com.example.rest_api_test.dto.OrderResponseDto;
import com.example.rest_api_test.entity.Order;
import com.example.rest_api_test.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DataRequestController {

    @Autowired
    ProcessService processService;

    @PostMapping("/testpost")
    public ResponseEntity<OrderResponseDto> doPostProcess(@RequestBody OrderRequestDto request){
        OrderResponseDto responseProcess = processService.doPost(request);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseProcess);
    }
    @GetMapping("{tokenId}/testget")
    public ResponseEntity<Order> doGetProcess(@PathVariable String tokenId) {
        Order responseProcess = processService.doGet(tokenId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseProcess);
    }

    @DeleteMapping("{tokenId}/testdelete")
    public ResponseEntity<String> doDeleteProcess(@PathVariable String tokenId) {
        String responseProcess = processService.doDelete(tokenId);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM).body(responseProcess);
    }
}
