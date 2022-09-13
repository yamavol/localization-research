package lctest

internal fun print_nothrow(block: () -> String) {
    try {
        print(block())
    }
    catch (_: Exception) {
        print("")
    }
}