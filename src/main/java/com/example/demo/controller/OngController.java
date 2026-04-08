package com.example.demo.controller;



import com.example.demo.dto.OngRequestDTO;
import com.example.demo.dto.OngResponseDTO;
import com.example.demo.service.OngService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ong")
@RequiredArgsConstructor
public class OngController {

    private final OngService ongService;

    @PostMapping
    public ResponseEntity<OngResponseDTO> create(@RequestBody OngRequestDTO dto) {
        OngResponseDTO created = ongService.createOng(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<OngResponseDTO>> getAll() {
        List<OngResponseDTO> ongs = ongService.getAllOng();
        return ResponseEntity.ok(ongs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OngResponseDTO> getById(@PathVariable String id) {
        OngResponseDTO ong = ongService.getById(id);
        return ResponseEntity.ok(ong);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OngResponseDTO> update(
            @PathVariable String id,
            @RequestBody OngRequestDTO dto
    ) {
        OngResponseDTO updated = ongService.updateOng(id, dto);
        return ResponseEntity.ok(updated);
    }

}
