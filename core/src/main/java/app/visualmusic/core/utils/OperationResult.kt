package app.visualmusic.core.utils

sealed interface OperationResult<T> {
    class Success<T>(val data: T) : OperationResult<T>
    class Error(val message: String) : OperationResult<Nothing>
    data object Loading : OperationResult<Nothing>
}