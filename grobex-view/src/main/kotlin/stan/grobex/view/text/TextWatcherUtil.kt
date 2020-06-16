package stan.grobex.view.text

import android.text.Editable
import android.text.TextWatcher

internal object TextWatcherDefault {
    val empty: (Any?) -> Unit = {}
    val afterTextChanged: (Editable?) -> Unit = empty
    val beforeTextChanged: (CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> }
    val onTextChanged: (CharSequence?, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> }
}

fun textWatcher(
    afterChanged: (Editable?) -> Unit = TextWatcherDefault.afterTextChanged,
    beforeChanged: (CharSequence?, start: Int, count: Int, after: Int) -> Unit = TextWatcherDefault.beforeTextChanged,
    onChanged: (CharSequence?, start: Int, before: Int, count: Int) -> Unit
): TextWatcher {
    return object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterChanged(editable)
        }

        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            beforeChanged(charSequence, start, count, after)
        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            onChanged(charSequence, start, before, count)
        }
    }
}

fun textWatcher(
    afterChanged: (Editable?) -> Unit = TextWatcherDefault.afterTextChanged,
    beforeChanged: (CharSequence?) -> Unit = TextWatcherDefault.empty,
    onChanged: (CharSequence?) -> Unit
): TextWatcher {
    return textWatcher(
        afterChanged = afterChanged,
        beforeChanged = { value: CharSequence?, _, _, _ -> beforeChanged(value) },
        onChanged = { value: CharSequence?, _, _, _ -> onChanged(value) }
    )
}

fun textWatcher(
    afterChanged: (Editable?) -> Unit = TextWatcherDefault.afterTextChanged,
    beforeChanged: (CharSequence?, start: Int, count: Int, after: Int) -> Unit
): TextWatcher {
    return textWatcher(
        afterChanged = afterChanged,
        beforeChanged = beforeChanged,
        onChanged = TextWatcherDefault.onTextChanged
    )
}

fun textWatcher(
    afterChanged: (Editable?) -> Unit
): TextWatcher {
    return textWatcher(
        afterChanged = afterChanged,
        beforeChanged = TextWatcherDefault.beforeTextChanged
    )
}

fun textWatcher(
    onChanged: () -> Unit
): TextWatcher {
    return textWatcher(
        onChanged = { _ -> onChanged() }
    )
}
