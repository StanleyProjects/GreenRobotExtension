package stan.grobex.sample

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import stan.grobex.view.Gravity
import stan.grobex.view.Orientation
import stan.grobex.view.editText
import stan.grobex.view.linearLayout
import stan.grobex.view.margin
import stan.grobex.view.text.onTextChanged
import stan.grobex.view.text.textWatcher
import stan.grobex.view.textView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(linearLayout(context = this, orientation = Orientation.VERTICAL) {
            textView(text = "test")
            textView(text = "test2", isAllCaps = true)
            val t = textView()
            val tw0 = onTextChanged {
                t.setText(it)
            }
            val tw1 = textWatcher(afterChanged = {
                println("after: $it")
            }, onChanged = { it, _, _, _ ->
                t.text = it
            })
            editText(
                textWatchers = setOf(tw0),
                text = "123123"
            ) {
                addTextChangedListener(tw1)
                onTextChanged {
                    t.text = it
                }
            }
            textView(
                width = ViewGroup.LayoutParams.MATCH_PARENT,
                margin = margin(top = 30),
                gravity = Gravity.CENTER_HORIZONTAL,
                text = "Тема:",
                textSize = 15f,
                textColor = Color.parseColor("#8e8e8e")
            )
        })
    }
}
