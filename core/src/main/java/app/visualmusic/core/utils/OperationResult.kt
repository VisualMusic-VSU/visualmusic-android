package app.visualmusic.core.utils

sealed class OperationResult<out T> {
    class Success<T>(val data: T) : OperationResult<T>()
    class Error(val message: String) : OperationResult<Nothing>()
    data object Loading : OperationResult<Nothing>()

    inline fun handle(
        onSuccess: (data: T) -> Unit = {},
        onError: (message: String) -> Unit = {},
        onLoading: () -> Unit = {}
    ) {
        when (this) {
            is Success -> onSuccess(data)
            is Error -> onError(message)
            is Loading -> onLoading()
        }
    }
}