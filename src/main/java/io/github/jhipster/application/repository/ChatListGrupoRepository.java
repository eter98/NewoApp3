package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ChatListGrupo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ChatListGrupo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChatListGrupoRepository extends JpaRepository<ChatListGrupo, Long>, JpaSpecificationExecutor<ChatListGrupo> {

    @Query("select chatListGrupo from ChatListGrupo chatListGrupo where chatListGrupo.propietario.login = ?#{principal.username}")
    List<ChatListGrupo> findByPropietarioIsCurrentUser();

    @Query("select chatListGrupo from ChatListGrupo chatListGrupo where chatListGrupo.destinatario.login = ?#{principal.username}")
    List<ChatListGrupo> findByDestinatarioIsCurrentUser();

}
