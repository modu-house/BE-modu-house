package com.example.moduhouse.board.repository;

import com.example.moduhouse.board.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByBoardIdAndUserId(Long boarId, Long userId);

    void deleteByBoardIdAndUserId(Long boardId, Long userId);
}