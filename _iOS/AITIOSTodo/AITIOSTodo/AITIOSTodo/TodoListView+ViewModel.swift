//
//  TodoListView+ViewModel.swift
//  AITIOSTodo
//
//  Created by Peter Ekler on 2024. 12. 02..
//


import SwiftUI

extension TodoListView {
    final class ViewModel: ObservableObject {
        
        @Published var todos: [TodoItem]
        @Published var todoName = ""

        init(todos: [TodoItem]) {
            self.todos = todos
        }

        func addTodo() {
            self.todos.append(TodoItem(id: UUID().uuidString, name: todoName, isChecked: false))
            self.todoName = ""
        }

        func delete(at offsets: IndexSet) {
            todos.remove(atOffsets: offsets)
        }
    }
}
