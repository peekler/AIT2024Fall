//
//  ContentView.swift
//  IosTIme
//
//  Created by Peter Ekler on 02/09/2024.
//

import SwiftUI

struct ContentView: View {
    @State private var currentTime: String = ""
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            
            Button("Current time") {
                let formatter = DateFormatter()
                formatter.dateFormat = "HH:mm:ss"
                let timeString = formatter.string(from: Date())
                //currentTime = "Current Time: \(Date().description)"
                currentTime = "Current: \(timeString)"
            }.buttonStyle(.borderedProminent)
            
            //.buttonStyle(GrowingButton())
            Text("Hello, world!\(currentTime)")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
