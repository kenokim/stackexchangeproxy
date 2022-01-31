package keno.stackexchangeproxy.respository;

import keno.stackexchangeproxy.stackData.CommentStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentStackRepository extends JpaRepository<CommentStack, Long> {
}
