package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.ChatsListado;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ChatsListado} entity.
 */
public interface ChatsListadoSearchRepository extends ElasticsearchRepository<ChatsListado, Long> {
}
