package repository;

import org.springframework.data.repository.CrudRepository;
import repository.entity.Game;

/*
 * Simple repository to store information about the games
 *
 * @author Maxim
 */

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findDistinctById(Long id);

}
