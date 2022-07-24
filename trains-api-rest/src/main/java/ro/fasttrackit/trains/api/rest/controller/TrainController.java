package ro.fasttrackit.trains.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.trains.api.model.entity.Train;
import ro.fasttrackit.trains.api.rest.exception.custom.ResourceNotFoundException;
import ro.fasttrackit.trains.api.rest.model.entity.TrainEntity;
import ro.fasttrackit.trains.api.rest.model.filter.TrainCriteria;
import ro.fasttrackit.trains.api.rest.model.mapper.TrainMapper;
import ro.fasttrackit.trains.api.rest.model.response.CollectionResponse;
import ro.fasttrackit.trains.api.rest.model.response.PageInfo;
import ro.fasttrackit.trains.api.rest.service.TrainService;

@RestController
@RequestMapping("/trains")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;
    private final TrainMapper trainMapper;

    @GetMapping
    public CollectionResponse<Train> getTrains(TrainCriteria filter) {
        Page<TrainEntity> trains = trainService.getTrains(filter);
        return CollectionResponse.<Train>builder()
                .content(trainMapper.toApi(trains.getContent()))
                .pageInfo(PageInfo.builder()
                        .page(trains.getNumber())
                        .size(trains.getSize())
                        .totalPages(trains.getTotalPages())
                        .totalElements(trains.getNumberOfElements())
                        .build())
                .build();
    }

    @PostMapping
    public Train createTrain(@RequestBody Train train) {
        return trainMapper.toApi(trainService.createTrain(trainMapper.toEntity(train)));
    }

    @GetMapping("/{id}")
    public Train getTrain(@PathVariable String id) {
        return trainMapper.toApi(trainService
                .getTrain(id)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Train.class, id)));
    }

    @DeleteMapping("/{id}")
    public Train deleteTrain(@PathVariable String id) {
        TrainEntity train = trainService.getTrain(id)
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Train.class, id));
        trainService.deleteTrain(train.id());
        return trainMapper.toApi(train);
    }

    @PutMapping("/{id}")
    public Train updateTrain(@PathVariable String id, @RequestBody Train train) {
        return trainMapper.toApi(trainService
                .updateTrain(id, trainMapper.toEntity(train))
                .orElseThrow(() -> ResourceNotFoundException.getInstance(Train.class, id)));
    }
}
