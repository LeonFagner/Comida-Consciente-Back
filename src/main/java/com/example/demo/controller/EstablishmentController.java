package com.example.demo.controller;



import com.example.demo.dto.EstablishmentRequestDTO;
import com.example.demo.dto.EstablishmentResponseDTO;
import com.example.demo.service.AuthService;
import com.example.demo.service.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/establishment")
@RequiredArgsConstructor
public class EstablishmentController {

    @Autowired
    private AuthService authService;


    @Autowired
    private final EstablishmentService service;

    @PostMapping
    public ResponseEntity<EstablishmentResponseDTO> create(@RequestBody EstablishmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @GetMapping
    public ResponseEntity<List<EstablishmentResponseDTO>> getAllEstablishment(@RequestHeader("Authorization") String token) {


//        Establishment loggedUser = (Establishment) authService.getUserFromToken(token);
////        if (!"ADMIN".equals(loggedUser.getUserType())) {
////            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
////        }

        return ResponseEntity.ok(service.getAllEstablishments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponseDTO> getEstablishmentById(@PathVariable String id) {

        EstablishmentResponseDTO establishment = service.getEstablishmentById(id);
        return ResponseEntity.ok(establishment);
    }

}
