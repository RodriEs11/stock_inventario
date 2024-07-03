package com.unla.grupo1.controllers.api.v1;


import com.unla.grupo1.dtos.ProductoDTO;
import com.unla.grupo1.entities.Producto;
import com.unla.grupo1.services.implementation.ProductoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductosRestApiController {


    @Qualifier("productoService")
    private ProductoService productoService;

    ProductosRestApiController(ProductoService productoService) {
        this.productoService = productoService;

    }

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok(productoService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ProductoDTO> add(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.insertOrUpdate(productoDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        productoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductoDTO> update(@RequestBody ProductoDTO productoDTO, @PathVariable("id") int id) {
        return ResponseEntity.ok(productoService.insertOrUpdate(productoDTO));
    }


}
