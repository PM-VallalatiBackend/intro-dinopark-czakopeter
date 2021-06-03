package hu.progmasters.dinopark.repository;

import hu.progmasters.dinopark.domain.Visitor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class VisitorRepositoryInMemory implements VisitorRepository {

    private final Map<Integer, Visitor> visitors = new HashMap<>();
    private int nextIndex = 1;

    @Override
    public Visitor save(Visitor visitor) {
        visitor.setId(nextIndex);
        visitors.put(visitor.getId(), visitor);
        nextIndex++;
        return visitor;
    }

    @Override
    public List<Visitor> findAll() {
        return visitors.values().stream()
                .sorted(Comparator.comparing(Visitor::getName))
                .collect(Collectors.toList());
    }
}
