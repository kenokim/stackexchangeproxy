package keno.stackexchangeproxy.respository;

import keno.stackexchangeproxy.stackData.AnswerStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerStackRepository extends JpaRepository<AnswerStack, Long> {
}
