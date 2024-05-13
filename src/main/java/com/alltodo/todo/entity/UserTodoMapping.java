package com.alltodo.todo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_todo_mapping")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTodoMapping {

}
