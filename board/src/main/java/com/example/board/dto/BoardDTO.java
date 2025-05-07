package com.example.board.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardDTO {

    private Long bno;

    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // member
    @Email(message = "이메일 형식을 확인해 주세요")
    @NotBlank(message = "작성자를 입력해 주세요")
    private String email;
    private String name;
    // 댓글개수
    private Long replyCount;
}
