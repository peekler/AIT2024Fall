//
//  BMIVIew.swift
//  AITIOSTodo
//
//  Created by Peter Ekler on 2024. 12. 02..
//


import SwiftUI

struct BMIVIew: View {
    @State private var BMIindex: Double = 0.0
    @State private var height: Double = 0.0
    @State private var weight: Double = 0.0
    @State private var showError = false

    var body: some View {
        VStack(
                alignment: .leading, spacing: 10
            ) {
                    Text("Height in meters (e.g. 1,8):")
                    TextField("Enter your height (m)", value: $height, format: .number)
                                    .textFieldStyle(.roundedBorder)
                                    .padding()
                    Text("Weight in kg:")
                    TextField("Enter your weight (kg)", value: $weight, format: .number)
                        .textFieldStyle(.roundedBorder)
                                    .padding()

                    Button("Show ideal weight") {
                        if (height <= 0 || weight <= 0) {
                            showError = true
                        } else {
                            showError = false
                            BMIindex = weight / (height*height)
                        }
                    }
                    .buttonStyle(.borderedProminent)

                    Text("BMI index: \(BMIindex)")

                    if (showError) {
                        Text("Error in the inputs")
                            .foregroundStyle(.red)
                    } else {
                        Text("""
                        <18.5: underweight
                        18.5 – 24.9: normal
                        25<: overweight
                        """)
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
                .padding()
    }
}

struct BMIVIew_Previews: PreviewProvider {
    static var previews: some View {
        BMIVIew()
    }
}

