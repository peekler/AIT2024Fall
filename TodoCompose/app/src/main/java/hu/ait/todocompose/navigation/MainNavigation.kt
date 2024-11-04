package hu.ait.todocompose.navigation


sealed class MainNavigation(val route: String) {
    object TodoListScreen : MainNavigation("todolist")

    object SummaryScreen : MainNavigation(
        "summaryscreen?all={all}&important={important}") {
        fun createRoute(all: Int, important: Int) : String {
            return "summaryscreen?all=$all&important=$important"
        }
    }

}