package repository;

import repository.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findDistinctById(Long id);

}
