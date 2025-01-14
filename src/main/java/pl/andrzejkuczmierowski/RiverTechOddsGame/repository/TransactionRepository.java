package pl.andrzejkuczmierowski.RiverTechOddsGame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.andrzejkuczmierowski.RiverTechOddsGame.entity.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long>, JpaRepository<Transaction,Long> {
    @Transactional
    @Override
    <S extends Transaction> S save(S entity);
}
