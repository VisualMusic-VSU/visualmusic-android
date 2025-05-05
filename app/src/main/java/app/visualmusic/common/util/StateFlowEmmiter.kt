package app.visualmusic.common.util

import app.visualmusic.core.utils.OperationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> MutableStateFlow<OperationResult<T>>.emitInIO(
    scope: CoroutineScope,
    block: suspend () -> OperationResult<T>
) {
    scope.launch {
        this@emitInIO.emit(OperationResult.Loading)
        val result = withContext(Dispatchers.IO) { block() }
        this@emitInIO.emit(result)
    }
}