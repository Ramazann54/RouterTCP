package com.example.routerTCP.model.abstractions.routes

import com.example.routerTCP.model.objects.Route


interface IRoutesService {
    /**
     * Добавить маршрут
     * @param route объект маршрута
     */
    suspend fun addRoute(route: Route)

    /**
     * Удалить маршрут
     * @param serialNumber серийный номер устройства (ИД маршрута)
     */
    suspend fun deleteRoute(serialNumber: String)

    /**
     * Редактировать маршрут
     * @param route объект маршрута
     */
    suspend fun editRoute(route: Route)

    /**
     * Получить маршрут
     * @param serialNumber серийный номер устройства (ИД маршрута)
     */
    suspend fun getRoute(serialNumber: String): Route

    /**
     * Получить обновления маршрутов
     */
    suspend fun getUpdates(): Array<Route>

    /**
     * Загрузить маршруты
     * @param from номер записи с которой начинать загрузку
     * @param count количество загружаемых маршрутов
     */
    suspend fun loadRoutes(from: UInt, count: UInt): Array<Route>
}