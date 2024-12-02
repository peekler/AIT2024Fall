//
//  TodoListView.swift
//  AITIOSTodo
//
//  Created by Peter Ekler on 2024. 12. 02..
//

import SwiftUI

struct TodoListView: View {
    @State private var showingAlert = false
    
    @StateObject var viewModel: ViewModel
    
    var body: some View {
        List {
            ForEach(viewModel.todos) { todo in
                TodoItemView(todo: todo)
            }
            .onDelete(perform: viewModel.delete)
        }
        .listRowInsets(.none)
        .listStyle(.plain)
        .alert("Add Todo", isPresented: $showingAlert) {
            TextField("Enter todo description", text: $viewModel.todoName)
                .autocorrectionDisabled(true)
            Button("Add") {
                viewModel.addTodo()
                showingAlert.toggle()
            }
            Button("Cancel")
            {
                viewModel.todoName = ""
                showingAlert.toggle()
            }
        }
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    showingAlert.toggle()
                } label: {
                    Text("Add")
                }
            }
        }
    }
}

struct TodoListView_Previews: PreviewProvider {
    static var previews: some View {
        TodoListView(viewModel: .init(todos: [TodoItem(id: "1", name: "1", isChecked: false), TodoItem(id: "2", name: "2", isChecked: false), TodoItem(id: "3", name: "3", isChecked: false)]))
    }
}
