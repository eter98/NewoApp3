package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.ChatListGrupo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ChatListGrupo} entity.
 */
public interface ChatListGrupoSearchRepository extends ElasticsearchRepository<ChatListGrupo, Long> {
}
