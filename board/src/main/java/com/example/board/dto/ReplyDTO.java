package com.example.board.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {
    @Id
    private Long rno;

    private String text;

    private String replyerEmail;
    private String replyerName;

    // 게시글 번호 (부모번호)
    private Long bno;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
