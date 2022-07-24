package ro.fasttrackit.trains.api.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.fasttrackit.trains.api.rest.model.entity.TrainEntity;
import ro.fasttrackit.trains.api.rest.model.filter.TrainCriteria;
import ro.fasttrackit.trains.api.rest.repository.TrainDAO;
import ro.fasttrackit.trains.api.rest.repository.TrainRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainService {
    private final TrainRepository trainRepository;
    private final TrainDAO trainDAO;

    public Page<TrainEntity> getTrains(TrainCriteria filter) {
        return trainDAO.find(filter);
    }

    public TrainEntity createTrain(TrainEntity toEntity) {
        return trainRepository.save(toEntity.withId(null));
    }

    public Optional<TrainEntity> getTrain(String id) {
        return trainRepository.findById(id);
    }

    public void deleteTrain(String id) {
        trainRepository.deleteById(id);
    }

    public Optional<TrainEntity> updateTrain(String id, TrainEntity train) {
        return trainRepository.findById(id)
                .map(trainEntity -> trainRepository.save(
                        TrainEntity.builder()
                                .id(id)
                                .carts(train.carts())
                                .locationId(train.locationId())
                                .build()));
    }
}
