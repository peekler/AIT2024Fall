package hu.bme.aut.httpdemo.ui.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object MoneyRatesAPI : Screen("moneyrates")
    object NasaMarsAPI : Screen("nasamars")
}