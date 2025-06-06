package com.example.board.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberDTO {
    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수 요소 입니다.")
    private String email;

    @NotBlank(message = "이름은 필수 요소 입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 요소 입니다.")
    private String password;

    private boolean fromSocial;

}
