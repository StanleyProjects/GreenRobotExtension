package stan.grobex.sample

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import stan.grobex.common.content.px
import stan.grobex.common.graphics.colorOf
import stan.grobex.common.graphics.drawable.paintDrawable
import stan.grobex.common.view.px
import stan.grobex.view.entity.Gravity
import stan.grobex.view.entity.Orientation
import stan.grobex.view.entity.marginAll
import stan.grobex.view.group.editText
import stan.grobex.view.group.linearLayout
import stan.grobex.view.group.textView
import stan.grobex.view.group.view
import stan.grobex.view.text.onTextChanged
import stan.grobex.view.text.textWatcher

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = linearLayout(
            context = this,
            id = 1,
            orientation = Orientation.VERTICAL
        ) {
            textView(text = "test")
            textView(text = "test2", isAllCaps = true)
            val t = textView()
            val tw0 = onTextChanged {
                t.text = it
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
            view(
                width = 0,
                height = 0,
                weight = 1f
            )
            textView(
                width = ViewGroup.LayoutParams.MATCH_PARENT,
                height = px(dp = 54f),
//                margin = margin(top = px(dp = 8f), left = px(dp = 8f), right = px(dp = 8f), bottom = px(dp = 8f)),
                margin = marginAll(px(dp = 8f)),
                gravity = Gravity.CENTER,
                text = "Тема:",
                textSize = 15f,
                textColor = colorOf("#000000"),
                background = paintDrawable("#2883f2", context.px(dp = 6f)),
                onClick = {
                    // todo
                },
                onLongClick = {
                    true
                }
            )
        }
        setContentView(contentView)
    }
}
