package com.alexandertutoriales.ecommerce.service.repository;

import com.alexandertutoriales.ecommerce.service.entity.Platillo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlatilloRepository extends CrudRepository<Platillo, Integer> {
    @Query("SELECT P FROM Platillo P WHERE P.recomendado IS true AND P.stock != 0")
    Iterable<Platillo> listarPlatillosRecomendados();

    @Query("SELECT P FROM Platillo P WHERE P.categoria.id=:idC AND P.stock != 0")
    Iterable<Platillo> listarPlatillosPorCategoria(int idC);

    @Modifying
    @Query("UPDATE Platillo P SET P.stock=P.stock-:cant WHERE P.id=:id")
    void descontarStock(int cant, int id);

    @Modifying
    @Query("UPDATE Platillo P SET P.stock=P.stock+:cant WHERE P.id=:id")
    void aumentarStock(int cant, int id);

    @Query("SELECT P FROM Platillo P WHERE P.id=:idstock")
    Platillo obtenerStockRepo(int idstock);
}
