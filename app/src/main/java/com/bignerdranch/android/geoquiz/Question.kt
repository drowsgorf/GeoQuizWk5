import androidx.annotation.StringRes

//EXERCISE 2 -> ADDED var isAnswered BOOLEAN
data class Question(@StringRes val textResId: Int, val answer: Boolean, var isAnswered: Boolean)