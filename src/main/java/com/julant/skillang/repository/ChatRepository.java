package com.julant.skillang.repository;

import com.julant.skillang.model.Chat;
import com.julant.skillang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c join c.users u where u.id=:userId")
    public List<Chat> findByUserId(@Param("userId") Long userId);

    @Query("select c from Chat c where c.isGroup=false and :user member of c.users and :reqUser member of c.users")
    public Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);

}
