package hu.progmasters.dinopark.repository;

import hu.progmasters.dinopark.domain.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository {

    Visitor save(Visitor visitor);

    List<Visitor> findAll();
}
