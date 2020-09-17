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

fun afterTextChanged(
    block: (Editable?) -> Unit
): TextWatcher {
    return textWatcher(
        afterChanged = block,
        beforeChanged = TextWatcherDefault.beforeTextChanged,
        onChanged = TextWatcherDefault.onTextChanged
    )
}

fun beforeTextChanged(
    block: (CharSequence?) -> Unit
): TextWatcher {
    return textWatcher(
        beforeChanged = { value: CharSequence?, _, _, _ -> block(value) },
        onChanged = TextWatcherDefault.onTextChanged
    )
}

fun onTextChanged(
    block: (CharSequence?) -> Unit
): TextWatcher {
    return textWatcher(
        onChanged = { value: CharSequence?, _, _, _ -> block(value) }
    )
}
