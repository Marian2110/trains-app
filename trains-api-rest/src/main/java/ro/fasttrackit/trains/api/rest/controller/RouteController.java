package ro.fasttrackit.trains.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.trains.api.model.entity.Route;
import ro.fasttrackit.trains.api.rest.model.mapper.RouteMapper;
import ro.fasttrackit.trains.api.rest.service.RouteService;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final RouteMapper routeMapper;

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeMapper.toApi(routeService.createRoute(routeMapper.toEntity(route)));
    }

}
