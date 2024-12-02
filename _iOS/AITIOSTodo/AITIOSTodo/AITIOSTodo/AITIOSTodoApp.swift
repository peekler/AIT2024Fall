//
//  AITIOSTodoApp.swift
//  AITIOSTodo
//
//  Created by Peter Ekler on 2024. 12. 02..
//

import SwiftUI

@main
struct AITIOSTodoApp: App {
    var body: some Scene {
        WindowGroup {
            //ContentView()
            //BMIVIew()
            
            NavigationView {
                            TodoListView(viewModel: TodoListView.ViewModel(todos: []))
                        }
        }
    }
}
