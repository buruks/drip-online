package org.drip.repository;

import org.drip.model.ResetHash;
import org.springframework.data.repository.CrudRepository;


public interface HashRepository extends CrudRepository<ResetHash, Long> {
	ResetHash findByHash(String hash);
}
