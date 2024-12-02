//
//  TodoItemView.swift
//  AITIOSTodo
//
//  Created by Peter Ekler on 2024. 12. 02..
//

import SwiftUI

struct TodoItemView: View {

    @State var todo: TodoItem
    @State private var isChecked = false
    
    var body: some View {
        HStack {
            Text(todo.name)
            Spacer()
            Toggle("", isOn: $todo.isChecked)
        }
        .padding(.horizontal)
        .padding(.vertical, 5)
        .onChange(of: isChecked) { newValue in
            todo.isChecked = newValue
        }
    }

    init(todo: TodoItem) {
        self.todo = todo
    }
}

#Preview {
    TodoItemView(todo: TodoItem(id: "1",name: "Demo", isChecked: false))
}
