package com.alltodo.todo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_todos")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTodo {
    @EmbeddedId
    private UserTodoId userTodoId;

    // 우선 순위를 만들어줘야 할듯?
    // 매핑 테이블을 없애고 권한 관리용 테이블을 따로 만드는게 나을듯?
}
