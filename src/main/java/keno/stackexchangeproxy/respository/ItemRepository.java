package keno.stackexchangeproxy.respository;

import keno.stackexchangeproxy.stackData.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
